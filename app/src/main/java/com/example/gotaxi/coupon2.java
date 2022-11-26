package com.example.gotaxi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class coupon2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coupon2, container, false);

        Button button = (Button) view.findViewById(R.id.booknow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =    fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container , new coupon3());
                fragmentTransaction.addToBackStack(String.valueOf(new coupon2()));
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}