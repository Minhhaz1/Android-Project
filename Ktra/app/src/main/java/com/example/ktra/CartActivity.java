package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ktra.Adapter.CartAdapter;
import com.example.ktra.Helper.ChangeNumberItemsListener;
import com.example.ktra.Helper.ManagmentCart;
import com.example.ktra.Model.Foods;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    private RecyclerView recCart;
    private ImageView backBtn;
    private ScrollView scroll;
    private Button buyBtn;
    private TextView emptyTxt,totalAll,totaltax,deli,totalfee;
    private double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        managmentCart = new ManagmentCart(this);
        initView();
        setVariable();
        calculaterCart();
        initList();
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Total Order Amount = "+managmentCart.getTotalFee() ;;
                managmentCart.placeOrder(message);
                managmentCart.clearCart();
                adapter = new CartAdapter(managmentCart.getListCart(), CartActivity.this, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        calculaterCart();
                    }
                });
                recCart.setAdapter(adapter);

            }
        });
    }

    private void initList() {
        if(managmentCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scroll.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scroll.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recCart.setLayoutManager(linearLayoutManager);
        for(Foods item: managmentCart.getListCart()){
            Log.d("list cart",""+item.getId());
        }

        adapter = new CartAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculaterCart();
            }
        });
        recCart.setAdapter(adapter);
    }

    private void calculaterCart() {

        double total = managmentCart.getTotalFee();
        totalAll.setText("$"+ total);
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtn);
        totalAll = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scroll = findViewById(R.id.scroll);
        recCart = findViewById(R.id.recCart);
        buyBtn = findViewById(R.id.BuyBtn);

    }
    private void setVariable() {
        backBtn.setOnClickListener(view -> finish());
    }
}