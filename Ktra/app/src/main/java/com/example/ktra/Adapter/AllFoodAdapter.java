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
import com.example.ktra.DetailActivity;
import com.example.ktra.Model.Foods;
import com.example.ktra.R;

import java.util.ArrayList;

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.viewholder> {
    ArrayList<Foods> items;
    Context context;

    public AllFoodAdapter(ArrayList<Foods> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflater = LayoutInflater.from(context).inflate(R.layout.list_food,parent,false);
        return new viewholder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText( "$" + items.get(position).getPrice());
        holder.timeTxt.setText(items.get(position).getTimeValue()+" min");
        holder.starTxt.setText(Double.toString(items.get(position).getStar()));
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object",items.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,priceTxt,starTxt,timeTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            starTxt = itemView.findViewById(R.id.starTxt);
            pic = itemView.findViewById(R.id.img);
        }
    }
}
