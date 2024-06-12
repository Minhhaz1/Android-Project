package com.example.ktra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ktra.Adapter.BestFoodsAdapter;
import com.example.ktra.Adapter.CateAdapter;
import com.example.ktra.Model.Category;
import com.example.ktra.Model.Foods;
import com.example.ktra.Model.Location;
import com.example.ktra.Model.Price;
import com.example.ktra.Model.Time;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private Spinner locationSp,timeSp,priceSp;
    private ImageView cartBtn,logoutBtn,searchBtn;
    private EditText searchEdit;
    private TextView tvAll;
    private FloatingActionButton flb;
    private ProgressBar progressBarBest,progressBarCate;
    private RecyclerView recBest,recCate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initLocation();
//        initTime();
//        initPrice();
        initBestFood();
        initCate();
        setVariable();
    }

    private void setVariable() {
        logoutBtn.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = searchEdit.getText().toString();
                if(!text.isEmpty()){
                    Intent intent= new Intent(MainActivity.this,ListFoodsActivity2.class);
                    intent.putExtra("text",text);
                    intent.putExtra("isSearch",true);
                    startActivity(intent);
                }
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AllFoodActivity.class));
            }
        });
    }

    private void initBestFood() {
        DatabaseReference myRef = database.getReference("Foods");
        progressBarBest.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        recBest.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new BestFoodsAdapter(list);
                        recBest.setAdapter(adapter);
                    }
                    progressBarBest.setVisibility(View.GONE);
                    for (Foods food : list) {
                        Log.d("FoodItem", "Title: " + food.getTitle() + ", Price: " + food.getPrice() + ", Time: " + food.getTimeValue() + " min, Star: " + food.getStar());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initCate() {
        DatabaseReference myRef = database.getReference("Category");
        progressBarCate.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if(list.size()>0){
                        recCate.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        RecyclerView.Adapter adapter = new CateAdapter(list);
                        recCate.setAdapter(adapter);
                    }
                    progressBarCate.setVisibility(View.GONE);
                    for (Category food : list) {
                        Log.d("FoodItem", "Title: " + food.getName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        tvAll = findViewById(R.id.tvAll);
        progressBarBest = findViewById(R.id.progressBarBest);
        progressBarCate = findViewById(R.id.progressBarCate);
        recBest = findViewById(R.id.recBest);
        recCate = findViewById(R.id.recCate);
        logoutBtn = findViewById(R.id.logoutBtn);
        searchBtn = findViewById(R.id.searchBtn);
        searchEdit = findViewById(R.id.searchEdit);
        cartBtn = findViewById(R.id.cartBtn);
        flb = findViewById(R.id.fab_add_food);
    }

    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.spinner_items,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initTime() {
        DatabaseReference myRef = database.getReference("Time");
        ArrayList<Time> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Time.class));
                    }
                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.spinner_items,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initPrice() {
        DatabaseReference myRef = database.getReference("Price");
        ArrayList<Price> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Price.class));
                    }
                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.spinner_items,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    priceSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}