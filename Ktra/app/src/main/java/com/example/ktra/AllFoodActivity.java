package com.example.ktra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ktra.Adapter.AllFoodAdapter;
import com.example.ktra.Adapter.FoodListAdapter;
import com.example.ktra.Model.Foods;
import com.example.ktra.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllFoodActivity extends BaseActivity {
    private ImageView backBtn;
    private RecyclerView recList;
    private RecyclerView.Adapter adapterALlFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food);
        initView();
        initAll();
        backBtn.setOnClickListener(view -> {
            finish();
        });
    }

    private void initAll() {
        DatabaseReference myRef =  database.getReference("Foods");
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        recList.setLayoutManager(new GridLayoutManager(AllFoodActivity.this,2));
                        adapterALlFood = new AllFoodAdapter(list);
                        recList.setAdapter(adapterALlFood);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtn);
        recList = findViewById(R.id.recAll);
    }
}