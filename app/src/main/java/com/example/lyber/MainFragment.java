package com.example.lyber;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kangsik_kevin_lee on 9/2/17.
 */

public class MainFragment extends Fragment implements ResponseHandler<UberModel> {
    private static final String  TAG = MainFragment.class.getSimpleName();

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

    OnCompareListener mOnCompareListener;


    public interface OnCompareListener {
        public void getUberResults(UberModel uberModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btCompare = (Button) view.findViewById(R.id.btCompare);
        etPickUpAt = (EditText) view.findViewById(R.id.etPickUpAt);
        etWhereTo = (EditText) view.findViewById(R.id.etWhereTo);

        gc= new Geocoder(getContext());



        btCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                action();
            }
        });




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnCompareListener){
            mOnCompareListener = (OnCompareListener) context;
        }else{
            throw new ClassCastException(context.toString()+ " must implement OnCompareListener");
        }
    }

    private void action() {
        String inputPickUpAt = etPickUpAt.getText().toString();
        String inputWhereTo = etWhereTo.getText().toString();
        List<Address> gcResults = null;

        if(gc.isPresent()){
            try{


                List<Address> list = new ArrayList<Address>();

                list.add(gc.getFromLocationName("1600 Amphitheatre Parkway, Mountain View, CA", 1).get(0));
                list.add(gc.getFromLocationName("1600 Amphitheatre Parkway, Mountain View, CA", 1).get(0));

                etPickUpAt.setText(list.get(0).getAddressLine(0) + " " + list.get(0).getAddressLine(1));
                etWhereTo.setText(list.get(1).getAddressLine(0)+" "+list.get(1).getAddressLine(1));


                start_lat = pickUpAddress.getLatitude()+"";
                start_lon = pickUpAddress.getLongitude()+"";
                end_lat = whereToAddress.getLatitude()+"";
                end_lon = whereToAddress.getLongitude()+"";

                Log.d(TAG, "start_lat: " + start_lat);
                Log.d(TAG, "start_lon: " + start_lon);
                Log.d(TAG, "end_lat: " + end_lat);
                Log.d(TAG, "end_lon: " + end_lon);



            }catch(IOException ex) {
                Toast.makeText(getContext(), "Address not found. Please try again.",
                        Toast.LENGTH_LONG).show();
            }



        }else{
            Toast.makeText(getContext(), "Geocoder not found. Please Try Again. ",
                    Toast.LENGTH_LONG).show();
        }









        RetrofitClient retrofitClientUber = new RetrofitClient("UBER");

        uberApiService = new UberApiService(retrofitClientUber);
        uberApiService.getUber(this,true);
//
//        RetrofitClient retrofitClientLyft = new RetrofitClient("LYFT");
//        lyftApiService = new LyftApiService(retrofitClientLyft);
//        lyftApiService.getLyft(responseHandlerLyft, true);
    }

    @Override
    public void onSuccess(UberModel response) {
        mOnCompareListener.getUberResults(response);
        Log.d(TAG,uberModel.uberPrices.get(0).estimate);
    }

    @Override
    public void onError(int responseCode, String message) {

    }
}
