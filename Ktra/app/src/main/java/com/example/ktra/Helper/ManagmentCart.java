package com.example.ktra.Helper;

import android.content.Context;
import android.widget.Toast;

//import com.example.project162_food.Domain.Foods;

import com.example.ktra.Model.Foods;
import com.example.ktra.Notification;
import com.google.firebase.database.core.view.Change;

import java.util.ArrayList;


public class ManagmentCart {
    private Context context;
    private SQLiteHelper sqLiteHelper;

    public ManagmentCart(Context context) {
        this.context = context;
        this.sqLiteHelper=new SQLiteHelper(context);
    }

    public void insertFood(Foods item) {
        ArrayList<Foods> listpop = sqLiteHelper.getAllFoods();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listpop.get(n).setNumberInCart(item.getNumberInCart());
            sqLiteHelper.updateFood(listpop.get(n));
        }else{
            sqLiteHelper.insertFood(item);
        }
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<Foods> getListCart(){
        return sqLiteHelper.getAllFoods();
    }



    public Double getTotalFee(){
        ArrayList<Foods> listItem=sqLiteHelper.getAllFoods();
        double fee=0;
        for (Foods food : listItem) {
            fee += food.getPrice() * food.getNumberInCart();
        }
        return fee;
    }
    public void minusNumberItem(ArrayList<Foods> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener){
        Foods food = listItem.get(position);
        if (food.getNumberInCart() == 1) {
            sqLiteHelper.deleteFood(food.getTitle());
            listItem.remove(position);
        } else {
            food.setNumberInCart(food.getNumberInCart() - 1);
            sqLiteHelper.updateFood(food);
        }
        changeNumberItemsListener.change();
    }
    public  void plusNumberItem(ArrayList<Foods> listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){
        Foods food = listItem.get(position);
        food.setNumberInCart(food.getNumberInCart() + 1);
        sqLiteHelper.updateFood(food);
        changeNumberItemsListener.change();
    }
    public void placeOrder(String message) {

        Notification.showOrderNotification(context, message);
    }
    public void clearCart(){
        sqLiteHelper.clearCart();
    }
}
