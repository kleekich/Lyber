package com.example.lyber;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by kangsik_kevin_lee on 8/30/17.
 */

public class RetrofitClient {

    private static final String BASE_URL_UBER = "https://api.uber.com/v1.2/";
    private static final String BASE_URL_LYFT = "https://api.lyft.com/v1/";
    private static String BASE_URL;

    private final Retrofit mRetrofit;

    public RetrofitClient(String companyName){
        BASE_URL = companyName == "UBER" ?  BASE_URL_UBER : BASE_URL_LYFT;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create());

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
            builder.client(client);
        }

        mRetrofit = builder.build();
    }

    public <T> T create(Class<T> apiRequestMethods){
        return mRetrofit.create(apiRequestMethods);
    }

}
