package com.example.lyber;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements MainFragment.OnCompareListener{
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


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeHolder,new MainFragment());
        ft.commit();


    }


    @Override
    public void getUberResults(UberModel uberModel) {
        this.uberModel = uberModel;

    }
}
