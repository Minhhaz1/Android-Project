package com.example.ktra.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.ktra.ListFoodsActivity2;
import com.example.ktra.Model.Category;
import com.example.ktra.Model.Foods;
import com.example.ktra.R;

import java.util.ArrayList;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.viewholder> {

    ArrayList<Category> items;
    Context context;

    public CateAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CateAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate,parent,false);
        return new viewholder(inflater);
    }


    @Override
    public void onBindViewHolder(@NonNull CateAdapter.viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());

        switch (position){
            case 0:{
                holder.pic.setBackgroundResource(R.drawable.cat_0);
                break;
            }case 1:{
                holder.pic.setBackgroundResource(R.drawable.cat_1);
                break;
            }case 2:{
                holder.pic.setBackgroundResource(R.drawable.cat_2);
                break;
            }case 3:{
                holder.pic.setBackgroundResource(R.drawable.cat_3);
                break;
            }case 4:{
                holder.pic.setBackgroundResource(R.drawable.cat_4);
                break;
            }case 5:{
                holder.pic.setBackgroundResource(R.drawable.cat_5);
                break;
            }case 6:{
                holder.pic.setBackgroundResource(R.drawable.cat_6);
                break;
            }case 7:{
                holder.pic.setBackgroundResource(R.drawable.cat_7);
                break;
            }
        }
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListFoodsActivity2.class);
            intent.putExtra("CategoryId",items.get(position).getId());
            intent.putExtra("CategoryName",items.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);
        }
    }
}
