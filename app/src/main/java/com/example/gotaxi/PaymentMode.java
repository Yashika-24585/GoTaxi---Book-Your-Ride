package com.example.gotaxi;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gotaxi.databinding.ActivityMainBinding;


public class PaymentMode extends Fragment {

//    public static final String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
//    int GOOGLE_PAY_REQUEST_CODE = 123;
//    String amount;
//    String name = "Esha Agrawal";
//    String upiId = "agrawalesha22@okhdfcbank";
//    String transactionNote = "pay ";
//    String status;
//    Uri uri;
//    String amounts ;
//    private ActivityMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_payment_mode, container, false);
//        Button gpay = view.findViewById(R.id.onlinegpay);
//
//       gpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                 amounts = String.valueOf(200);
//                 if(!amounts.isEmpty()){
//                     uri = getUpiPaymentUri(name,upiId,transactionNote,amount);
//                     payWithGPay();
//                 }
//                 else{
//                     Toast.makeText(getActivity(),"Amount is required", Toast.LENGTH_SHORT).show();
//                 }
//
//            }
//        });

        return view;

    }

//    private static boolean isAppInstalled(Context context, String packageName) {
//        try {
//            context.getPackageManager().getApplicationInfo(packageName, 0);
//            return true;
//        } catch (PackageManager.NameNotFoundException e) {
//            return false;
//        }
//    }
//
//    private void payWithGPay() {
//        if (isAppInstalled(getActivity(), GOOGLE_PAY_PACKAGE_NAME)) {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(uri);
//            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
//            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
//        } else {
//            Toast.makeText(getActivity(), "Please Install GPay", Toast.LENGTH_SHORT).show();
//        }
//    }
//    private static Uri getUpiPaymentUri(String name, String upiId, String transactionNote, String amount) {
//        return new Uri.Builder()
//                .scheme("upi")
//                .authority("pay")
//                .appendQueryParameter("pa", upiId)
//                .appendQueryParameter("pn", name)
//                .appendQueryParameter("tn", transactionNote)
//                .appendQueryParameter("am", amount)
//                .appendQueryParameter("cu", "INR")
//                .build();
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            status = data.getStringExtra("Status").toLowerCase();
//        }
//
//        if ((RESULT_OK == resultCode) && status.equals("success")) {
//            Toast.makeText(getActivity(), "Transaction Successful", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getActivity(), "Transaction Failed", Toast.LENGTH_SHORT).show();
//        }
//    }
}