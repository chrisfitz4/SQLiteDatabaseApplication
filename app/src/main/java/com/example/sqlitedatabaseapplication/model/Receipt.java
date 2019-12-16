package com.example.sqlitedatabaseapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Receipt implements Parcelable {

    private int id;
    private String title;
    private String price;


////////////constructor///////////////////
    public Receipt(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public Receipt(int id, String title, String price){
        this.id = id;
        this.title = title;
        this.price = price;
    }




    /////////////getters and setters//////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



//////////implement plarcelable methods/////////////


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(price);
    }

    protected Receipt(Parcel in) {
        id = in.readInt();
        title = in.readString();
        price = in.readString();
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };
}
