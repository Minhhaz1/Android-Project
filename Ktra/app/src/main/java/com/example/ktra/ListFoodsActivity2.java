package com.example.ktra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ktra.Adapter.FoodListAdapter;
import com.example.ktra.Model.Foods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity2 extends BaseActivity {
    private TextView titleTv;

    private TextView titleTxt;
    private ImageView backBtn;
    private RecyclerView recList;
    private ProgressBar progressBarList;
    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_foods2);
        initView();
        getIntentExtra();
        initList();
    }

    private void initView() {
        titleTv = findViewById(R.id.titleTxt);
        backBtn = findViewById(R.id.backBtn);
        progressBarList = findViewById(R.id.progressBarList);
        recList = findViewById(R.id.recFoodList);
    }

    private void initList() {
        DatabaseReference myRef = database.getReference("Foods");
        progressBarList.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query;
        if(isSearch){
            query=myRef.orderByChild("title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else{
            query=myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        recList.setLayoutManager(new GridLayoutManager(ListFoodsActivity2.this,2));
                        adapterListFood = new FoodListAdapter(list);
                        recList.setAdapter(adapterListFood);
                    }
                    progressBarList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentExtra() {
        categoryId = getIntent().getIntExtra("CategoryId",0);
        categoryName = getIntent().getStringExtra("CategoryName");
        searchText = getIntent().getStringExtra("text");
        isSearch = getIntent().getBooleanExtra("isSearch",false);

        titleTv.setText(categoryName);
        backBtn.setOnClickListener(view -> {
            finish();
        });
    }
}