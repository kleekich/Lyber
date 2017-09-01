package com.example.lyber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button btCompare;
    UberApiService uberApiService;
    LyftApiService lyftApiService;

    ResponseHandler responseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCompare = (Button) findViewById(R.id.btCompare);
        responseHandler = new ResponseHandler() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onError(int responseCode, String message) {

            }
        };

        

        btCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                RetrofitClient retrofitClientUber = new RetrofitClient("UBER");

                uberApiService = new UberApiService(retrofitClientUber);
                uberApiService.getUber(responseHandler,true);

                RetrofitClient retrofitClientLyft = new RetrofitClient("LYFT");
                lyftApiService = new LyftApiService(retrofitClientLyft);
                lyftApiService.getLyft(responseHandler, true);
            }
        });


    }




}
