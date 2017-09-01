package com.example.lyber;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by kangsik_kevin_lee on 8/31/17.
 */

public class LyftApiService {
    ApiConfig apiConfig = new ApiConfig.Builder()
            .setClientId("...")
            .setClientToken("...")
            .build();



    public interface LyftApiService {
        @Headers({"Content-Type: application/json",
                "Authorization: Bearer VCz8t0iW4Xq60kzG2bk9A70irK+bJMQxpVwoEvMrsWtpR0z3GfIoD5Yxw0ih39AQSkRiiYbnyj7yQFXTqsGxsLf3nhHIw3KzTZrIS31iZ+CvFovYwicNmFo="

        })
        @GET("https://api.lyft.com/v1/eta?lat=37.7833&lng=-122.4167")
        Call<LyftModel> getEstimatePrice();



    }

}
