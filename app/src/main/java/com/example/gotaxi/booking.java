package com.example.gotaxi;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.gotaxi.databinding.ActivityBookingBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class booking extends FragmentActivity implements OnMapReadyCallback , GoogleMap.OnPolylineClickListener , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ActivityBookingBinding binding;

    LatLng latlng_location , latLng_destination;
    LatLngBounds latLngBounds ;
    Polyline polyline;

    Marker marker1 , marker2 ;
    Button button , cash;


    View views = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.maps, supportMapFragment).commit();
        supportMapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getParcelableExtra("bundle");
        latlng_location = bundle.getParcelable("from_position");
        latLng_destination= bundle.getParcelable("to_position");

        Bundle args = new Bundle();
        args.putParcelable("from_position", latlng_location);
        args.putParcelable("to_position", latLng_destination);

        button = findViewById(R.id.coupon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(booking.this, RequestRidemap.class);
                intent.putExtra("bundle", args);
                intent.putExtra("fragmenttype" , "coupon");
                startActivity(intent);
            }
        });

        cash = findViewById(R.id.cash);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(booking.this, RequestRidemap.class);
                intent.putExtra("bundle", args);
                intent.putExtra("fragmenttype" , "cash");
                startActivity(intent);
            }
        });




        findViewById(R.id.bookany).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectitem((View)findViewById(R.id.linear1));
            }
        });

        findViewById(R.id.Primesedan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectitem((View)findViewById(R.id.linear2));
            }
        });
        findViewById(R.id.PrimeSUV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectitem((View)findViewById(R.id.linear3));
            }
        });
        findViewById(R.id.outstation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectitem((View)findViewById(R.id.linear4));
            }
        });
        findViewById(R.id.chooseScooty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectitem((View)findViewById(R.id.linear5));
            }
        });
        findViewById(R.id.chooseauto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectitem((View)findViewById(R.id.linear6));
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if(latlng_location!=null && latLng_destination!=null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(latlng_location).include(latLng_destination);
            latLngBounds = builder.build();
            if (polyline != null) {
                polyline.remove();
            }

            if (marker1 != null || marker2 != null) {
                marker1.remove();
                marker2.remove();
            }
            polyline = mMap.addPolyline(new PolylineOptions().clickable(true).add(latlng_location, latLng_destination).color(Color.BLACK));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds, 15);
            mMap.moveCamera(cameraUpdate);
            mMap.setOnPolylineClickListener(booking.this);
            marker1 = mMap.addMarker(new MarkerOptions().position(latlng_location).title("PickUP").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            marker2 = mMap.addMarker(new MarkerOptions().position(latLng_destination).title("Drop").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }

    }






    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }

    public void selectitem(View view){
        if(views!=null){
            views.setBackgroundColor(0);
        }
        views = view;
      view.setBackgroundColor(Color.parseColor("#000000"));


    }


}