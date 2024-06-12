package com.example.ktra.Adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
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
import com.example.ktra.Helper.ChangeNumberItemsListener;
import com.example.ktra.Helper.ManagmentCart;
import com.example.ktra.Model.Foods;
import com.example.ktra.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<Foods> list;
    private ManagmentCart managmentCart;
    Context context;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Foods> list, Context context,ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart,parent,false);
        return new viewholder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.num.setText(list.get(position).getNumberInCart()+"");
        holder.total.setText("$"+list.get(position).getPrice());
        holder.fee.setText("$"+(list.get(position).getNumberInCart()*list.get(position).getPrice()));
        Glide.with(context)
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);
        Log.d("ImageURL", "Image URL: " + list.get(position).getImagePath());

        holder.plus.setOnClickListener(view -> managmentCart.plusNumberItem(list, position, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            }
        }));
        holder.minus.setOnClickListener((View view) -> {
            managmentCart.minusNumberItem(list, position, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title,fee,plus,minus;
        ImageView pic;
        TextView total,num;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            fee = itemView.findViewById(R.id.feeTxt);
            plus = itemView.findViewById(R.id.plusTxt);
            minus = itemView.findViewById(R.id.minusTxt);
            pic = itemView.findViewById(R.id.pic);
            total = itemView.findViewById(R.id.totalTxt);
            num = itemView.findViewById(R.id.numTxt);
        }
    }
}
