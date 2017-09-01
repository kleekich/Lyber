package com.example.lyber;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by kangsik_kevin_lee on 8/30/17.
 */

public class UberModel {
    @Json(name = "prices")
    public final List<UberPriceItem> uberPrices;

    public UberModel(List<UberPriceItem> uberPrices) {
        this.uberPrices = uberPrices;
    }

}
