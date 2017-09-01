package com.example.lyber;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    //UI components
    Button btCompare;
    EditText etPickUpAt;
    EditText etWhereTo;

    Geocoder gc;
    Address pickUpAddress;
    Address whereToAddress;
    String start_lat;
    String start_lon;
    String end_lat;
    String end_lon;


    UberApiService uberApiService;
    LyftApiService lyftApiService;
    UberModel uberModel;




    ResponseHandler responseHandlerUber;
    ResponseHandler responseHandlerLyft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCompare = (Button) findViewById(R.id.btCompare);
        etPickUpAt = (EditText) findViewById(R.id.etPickUpAt);
        etWhereTo = (EditText) findViewById(R.id.etWhereTo);

        gc= new Geocoder(this);

        responseHandlerUber = new ResponseHandler() {
            @Override
            public void onSuccess(Object response) {
                //Log.d(TAG, response.toString());
                uberModel = (UberModel) response;

                Log.d(TAG,uberModel.uberPrices.get(0).estimate);
            }

            @Override
            public void onError(int responseCode, String message) {

            }
        };

        responseHandlerLyft = new ResponseHandler() {
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

                String inputPickUpAt = etPickUpAt.getText().toString();
                String inputWhereTo = etWhereTo.getText().toString();

                if(gc.isPresent()){
                    try{
                        pickUpAddress = gc.getFromLocationName(inputPickUpAt,1).get(0);
                        whereToAddress = gc.getFromLocationName(inputWhereTo,1).get(0);

                        start_lat = pickUpAddress.getLatitude()+"";
                        start_lon = pickUpAddress.getLongitude()+"";
                        end_lat = whereToAddress.getLatitude()+"";
                        end_lon = whereToAddress.getLongitude()+"";

                        Log.d(TAG, "start_lat: " + start_lat);
                        Log.d(TAG, "start_lon: " + start_lon);
                        Log.d(TAG, "end_lat: " + end_lat);
                        Log.d(TAG, "end_lon: " + end_lon);



                    }catch(IOException ex) {
                        Toast.makeText(getApplicationContext(), "Address not found. Please try again.",
                                Toast.LENGTH_LONG).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(), "Geocoder not found. Please Try Again. ",
                            Toast.LENGTH_LONG).show();
                }









                RetrofitClient retrofitClientUber = new RetrofitClient("UBER");

                uberApiService = new UberApiService(retrofitClientUber);
                uberApiService.getUber(responseHandlerUber,true);

                RetrofitClient retrofitClientLyft = new RetrofitClient("LYFT");
                lyftApiService = new LyftApiService(retrofitClientLyft);
                lyftApiService.getLyft(responseHandlerLyft, true);
            }
        });


    }




}
