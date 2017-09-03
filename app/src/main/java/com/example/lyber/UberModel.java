package com.example.lyber;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangsik_kevin_lee on 8/30/17.
 */

public class UberModel implements Parcelable{
    @Json(name = "prices")
    public final List<UberPriceItem> uberPrices;

    public UberModel(List<UberPriceItem> uberPrices) {
        this.uberPrices = uberPrices;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.uberPrices);
    }

    protected UberModel(Parcel in) {
        this.uberPrices = new ArrayList<UberPriceItem>();
        in.readList(this.uberPrices, UberPriceItem.class.getClassLoader());
    }

    public static final Creator<UberModel> CREATOR = new Creator<UberModel>() {
        @Override
        public UberModel createFromParcel(Parcel source) {
            return new UberModel(source);
        }

        @Override
        public UberModel[] newArray(int size) {
            return new UberModel[size];
        }
    };
}
