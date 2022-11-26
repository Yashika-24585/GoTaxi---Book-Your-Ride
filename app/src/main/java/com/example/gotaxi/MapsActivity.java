package com.example.gotaxi;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gotaxi.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    EditText location, destination;

    LatLng latlng_location;
    LatLng latLng_destination;
    LatLngBounds latLngBounds;

    Button bookride, requestride;
    Polyline polyline;

    Marker marker1, marker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.map, supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);


        location = findViewById(R.id.location);
        destination = findViewById(R.id.text_dest);
        bookride = findViewById(R.id.bookride);



         bookride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latLng_destination!=null || latlng_location!=null){
                Bundle args = new Bundle();
                args.putParcelable("from_position", latlng_location);
                args.putParcelable("to_position", latLng_destination);
                Intent intent = new Intent(MapsActivity.this, booking.class);
                intent.putExtra("bundle", args);
                startActivity(intent);


            }
                else{
                    Toast.makeText(MapsActivity.this , "Enter the location" , Toast.LENGTH_SHORT).show();
                }
        }});



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        init(location, true);
        init(destination, false);





//                if(latlng_location!=null && latLng_destination!=null){
//                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                    builder.include(latlng_location).include(latLng_destination);
//                    latLngBounds = builder.build();
//                    if (polyline!=null)
//                    {
//                        polyline.remove();
//                    }
//
//                    if(marker1!=null || marker2!=null){
//                        marker1.remove();
//                        marker2.remove();
//                    }
//                 polyline = mMap.addPolyline(new PolylineOptions().clickable(true).add(latlng_location,latLng_destination).color(Color.RED));
//                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds , 15);
//                mMap.moveCamera(cameraUpdate);
//                mMap.setOnPolylineClickListener(MapsActivity.this);
//               marker1 =  mMap.addMarker(new MarkerOptions().position(latlng_location).title("PickUP").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//                marker2 = mMap.addMarker(new MarkerOptions().position(latLng_destination).title("Drop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//            }
//                else{
//                    Toast.makeText(MapsActivity.this , "Empty Field " , Toast.LENGTH_SHORT).show();
//                }


    }

    private void init(EditText e, boolean res) {
        Log.d(TAG, "init initializing");
        e.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                    geoLocate(e, res);
                }
                return false;
            }
        });
    }

    private void geoLocate(EditText etext, boolean res) {
        Log.d(TAG, "geolaocate geolocating");
        String searchstring = etext.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);

        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchstring, 1);
        } catch (IOException e) {
            Log.e(TAG, "geolocate : IOException" + e.getMessage());
        }
        if (list.size() > 0) {
            Address addrress = list.get(0);
            Log.d(TAG, "geolocate found a location " + addrress.getLatitude());
            if (res == true) {
                latlng_location = new LatLng(addrress.getLatitude(), addrress.getLongitude());
            } else {
                latLng_destination = new LatLng(addrress.getLatitude(), addrress.getLongitude());
            }

        }
    }

}