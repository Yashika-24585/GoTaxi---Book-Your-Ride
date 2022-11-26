package com.example.gotaxi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class coupon3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coupon3, container, false);


        Button button = (Button)view.findViewById(R.id.booknow);
        final LoadingDialog  loadingDialog = new LoadingDialog(getActivity());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // after 8 seconds
                        loadingDialog.dismissdialog();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction =    fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container , new driver_request());
                        fragmentTransaction.addToBackStack(String.valueOf(new coupon3()));
                        fragmentTransaction.commit();
                    }
                }, 8000);

            }
        });

        return view;
    }
}