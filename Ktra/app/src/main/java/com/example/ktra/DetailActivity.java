package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ktra.Helper.ManagmentCart;
import com.example.ktra.Model.Foods;

public class DetailActivity extends BaseActivity {
    private ImageView backBtn,img;
    private TextView subBtn,plusBtn,numTxt,priceTxt,totalTxt,titleTxt,motaTxt,rateTxt;
    private RatingBar ratingBar;
    private AppCompatButton addBtn;
    private Foods object;
    private int num=1;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        managmentCart = new ManagmentCart(this);
        backBtn.setOnClickListener(view -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(img);

        priceTxt.setText("$"+object.getPrice());
        titleTxt.setText(object.getTitle());
        motaTxt.setText(object.getDes());
        rateTxt.setText(object.getStar() + "Rating");
        ratingBar.setRating((float) object.getStar());
        totalTxt.setText(num*object.getPrice()+"$");
        numTxt.setText(num+"");

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=num+1;
                numTxt.setText(num+"");
                totalTxt.setText("$"+(num*object.getPrice()));
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num>1){
                    num=num-1;
                    numTxt.setText(num+"");
                    totalTxt.setText("$"+(num*object.getPrice()));
                }
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(num);
                managmentCart.insertFood(object);
            }
        });

    }

    private void getIntentExtra() {
        object = (Foods) getIntent().getSerializableExtra("object");
    }

    private void initView() {

        img=findViewById(R.id.img);
        backBtn = findViewById(R.id.backBtn);
        priceTxt = findViewById(R.id.priceTxt);
        titleTxt = findViewById(R.id.titleTxt);
        motaTxt = findViewById(R.id.motaTxt);
        rateTxt = findViewById(R.id.rateTxt);
        ratingBar = findViewById(R.id.ratingBar);
        totalTxt = findViewById(R.id.totalTxt);
        plusBtn = findViewById(R.id.plusBtn);
        subBtn = findViewById(R.id.subBtn);
        numTxt = findViewById(R.id.numTxt);
        addBtn = findViewById(R.id.addBtn);
    }
}