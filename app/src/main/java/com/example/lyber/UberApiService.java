package com.example.lyber;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by kangsik_kevin_lee on 8/30/17.
 */

public class UberApiService {

    private UberApi uberApi;

    private Call<UberModel> mCall;

    public UberApiService(RetrofitClient retrofitClient){
        uberApi = retrofitClient.create(UberApi.class);
    }




    public interface UberApi {
        @Headers({"Authorization: Bearer HjZxMp8RqM_19XHna7ncAVehIhP28L3PiY0jxw_Z",
                "Accept-Language: en_US",
                "Content-Type: application/json"
        })
        @GET("estimates/price?start_latitude=37.7752315&start_longitude=-122.418075&end_latitude=37.7752415&end_longitude=-122.518075")
        Call<UberModel> getEstimatePrice();


    }




}




