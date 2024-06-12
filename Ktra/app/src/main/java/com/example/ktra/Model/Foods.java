package com.example.ktra.Model;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;

public class Foods implements Serializable {
    private int CategoryId;
    private String Des;
    private boolean BestFood;
    private int Id;
    private double Price,Star;
    @PropertyName("Title")
    private String Title;
    private String ImagePath;
    private int PriceId,TimeId,TimeValue,NumberInCart;

    public Foods() {
    }

    public Foods( double price, double star, String imagePath, String TTitle ,int timeValue) {
        Price = price;
        Star = star;
        ImagePath = imagePath;
        Title = TTitle;
        TimeValue = timeValue;
    }

    @Override
    public String toString() {
        return Title;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public boolean isBestFood() {
        return BestFood;
    }

    public void setBestFood(boolean bestFood) {
        BestFood = bestFood;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getStar() {
        return Star;
    }

    public void setStar(double star) {
        Star = star;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        PriceId = priceId;
    }

    public int getTimeId() {
        return TimeId;
    }

    public void setTimeId(int timeId) {
        TimeId = timeId;
    }

    public int getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(int timeValue) {
        TimeValue = timeValue;
    }

    public int getNumberInCart() {
        return NumberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        NumberInCart = numberInCart;
    }
}
