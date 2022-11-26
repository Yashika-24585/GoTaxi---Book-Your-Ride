package com.example.gotaxi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class coupon1 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coupon1, container, false);

        Button button = (Button) view.findViewById(R.id.usecode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =    fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container , new coupon2());
                fragmentTransaction.addToBackStack(String.valueOf(new coupon1()));
                fragmentTransaction.commit();
            }
        });

        return view;

    }
}