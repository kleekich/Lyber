package com.example.lyber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by kangsik_kevin_lee on 8/31/17.
 */

public class LyftApiService {
    private LyftService lyftApi;

    private Call<LyftModel> mCall;

    public LyftApiService(RetrofitClient retrofitClient){
        uberApi = retrofitClient.create(UberApiService.UberApi.class);
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



    public interface LyftService {
        @Headers({"Content-Type: application/json",
                "Authorization: Bearer VCz8t0iW4Xq60kzG2bk9A70irK+bJMQxpVwoEvMrsWtpR0z3GfIoD5Yxw0ih39AQSkRiiYbnyj7yQFXTqsGxsLf3nhHIw3KzTZrIS31iZ+CvFovYwicNmFo="

        })
        @GET("https://api.lyft.com/v1/eta?lat=37.7833&lng=-122.4167")
        Call<LyftModel> getEstimatePrice();



    }

}
