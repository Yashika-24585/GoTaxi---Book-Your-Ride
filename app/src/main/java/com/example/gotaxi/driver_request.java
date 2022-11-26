package com.example.gotaxi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class driver_request extends Fragment {


    TextView request;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String[] array = {"Request Approved", "Taxi is on the way" , "Wait for 2.3 minutes \n Taxi is on the way"};

        View view = inflater.inflate(R.layout.fragment_driver_request, container, false);
        request = view.findViewById(R.id.text);
        button = view.findViewById(R.id.cancelRide);

//        while(i[0] <array.length){
//            request.post(new Runnable() {
//
//                @Override
//                public void run() {
//                    request.setText(array[i[0]]);
//                    i[0]++;
//                    request.postDelayed(this, 5000);
//                }
//            });
//        }


//        request.post(new Runnable() {
//            int i = 0;
//            @Override
//            public void run() {
//                request.setText(array[i]);
//                i++;
//                request.postDelayed(this, 5000);
//            }
//        });

        Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                for(int i=0;i<3;i++){
                    try {
                        Thread.sleep(5000);
                        int finalI = i;
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                request.setText(array[finalI]);
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();


    return  view;

    }
}