package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.ktra.Model.Foods;
import com.google.firebase.database.DatabaseReference;

public class AddActivity extends BaseActivity {
    private EditText editTextTitle, editTextPrice, editTextTime,editTextImage;
    private RatingBar ratingBar;
    private Button buttonAddFood;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextPrice = findViewById(R.id.edit_text_price);
        editTextTime = findViewById(R.id.edit_text_time);
        ratingBar = findViewById(R.id.rating_bar);
        editTextImage = findViewById(R.id.edit_imagepath);
        back = findViewById(R.id.backBtn);
        buttonAddFood = findViewById(R.id.button_add_food);

        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = editTextTitle.getText().toString().trim();
                double price = Double.parseDouble(editTextPrice.getText().toString().trim());
                int star = ratingBar.getProgress();
                String timeString = editTextTime.getText().toString();
                int time = Integer.parseInt(timeString);
                String image = editTextImage.getText().toString().trim();
                Foods food = new Foods(price,star,image,Title,time);
                DatabaseReference myRef =  database.getReference("Foods");
                String key = myRef.push().getKey();
                myRef.child(key).setValue(food);
                Toast.makeText(AddActivity.this,"Them Mon An Thanh Cong",Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}