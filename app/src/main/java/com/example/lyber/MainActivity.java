package com.example.lyber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

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
                RetrofitClient retrofitClient = new RetrofitClient();
                uberApiService = new UberApiService(retrofitClient);
                uberApiService.getUber(responseHandler,true);
                lyftApiService = new LyftApiService(retrofitClient);
                lyftApiService.getLyft(responseHandler, true);
            }
        });


    }




}
