package com.example.lyber;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

/**
 * Created by kangsik_kevin_lee on 8/31/17.
 */

public class UberPriceItem implements Parcelable{

    @Json(name = "display_name")
    String display_name;

    @Json(name = "estimate")
    String estimate;

    public UberPriceItem(String display_name, String estimate) {
        this.display_name = display_name;
        this.estimate = estimate;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getEstimate() {
        return estimate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.display_name);
        dest.writeString(this.estimate);
    }

    protected UberPriceItem(Parcel in) {
        this.display_name = in.readString();
        this.estimate = in.readString();
    }

    public static final Creator<UberPriceItem> CREATOR = new Creator<UberPriceItem>() {
        @Override
        public UberPriceItem createFromParcel(Parcel source) {
            return new UberPriceItem(source);
        }

        @Override
        public UberPriceItem[] newArray(int size) {
            return new UberPriceItem[size];
        }
    };
}
