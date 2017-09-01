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
        lyftApi = retrofitClient.create(LyftApiService.LyftService.class);
    }

    public void getLyft(final ResponseHandler<LyftModel> callBack,
                        boolean isCancelable) {
        Call<LyftModel> call = lyftApi.getEstimatePrice();
        if (isCancelable) {
            //if data is present return;
            if (mCall != null) {
                return;
            } else {
                mCall = call;
            }
        }

        call.enqueue(new Callback<LyftModel>() {
            @Override
            public void onResponse(Call<LyftModel> call, Response<LyftModel> response) {
                mCall = null;
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onError(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<LyftModel> call, Throwable t) {
                mCall = null;
                callBack.onError(-1, t.getMessage());

            }
        });
    }


    public interface LyftService {
        @Headers({"Content-Type: application/json",
                "Authorization: Bearer VCz8t0iW4Xq60kzG2bk9A70irK+bJMQxpVwoEvMrsWtpR0z3GfIoD5Yxw0ih39AQSkRiiYbnyj7yQFXTqsGxsLf3nhHIw3KzTZrIS31iZ+CvFovYwicNmFo="
        })
        @GET("cost?start_lat=37.7763&start_lng=-122.3918&end_lat=37.7972&end_lng=-122.4533")
        Call<LyftModel> getEstimatePrice();

    }

}
