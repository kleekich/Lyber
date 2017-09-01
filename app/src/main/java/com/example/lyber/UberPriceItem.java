package com.example.lyber;

import com.squareup.moshi.Json;

/**
 * Created by kangsik_kevin_lee on 8/31/17.
 */

public class UberPriceItem {

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

}
