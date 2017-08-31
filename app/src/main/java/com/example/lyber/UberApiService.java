package com.example.lyber;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public void getUber(final ResponseHandler<UberModel> callBack,
                        boolean isCancelable){
        Call<UberModel> call = uberApi.getEstimatePrice();
        if(isCancelable){
            //if data is present return;
            if(mCall != null){
                return;
            } else{
                mCall = call;
            }
        }

        call.enqueue(new Callback<UberModel>() {
            @Override
            public void onResponse(Call<UberModel> call, Response<UberModel> response) {
                mCall = null;
                if( response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onError(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UberModel> call, Throwable t) {
                mCall= null;
                callBack.onError(-1, t.getMessage());

            }
        });
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




