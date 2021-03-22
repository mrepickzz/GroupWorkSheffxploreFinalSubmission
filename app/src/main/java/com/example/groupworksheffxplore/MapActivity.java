package com.example.groupworksheffxplore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.List;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


    private static final String TAG = "MapActivity";

    private static final int ERROR_DIAGLOG_REQUEST = 9001;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PREMISSION_REQUEST_CODE = 1234;
    private final LatLng DEFAULT_LOCATION = new LatLng(53.372340, -1.456280);
    private static final float DEFAULT_ZOOM = 17f;
    //variables
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Location lastKnownLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getLocationPermission();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }




        Log.d(TAG, "OnMapReady : Map is now read to use");
        Toast.makeText(this, "Map is ready to be used", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

        LatLng norfolkPark = new LatLng(53.372340, -1.456280);
        mMap.addMarker(new MarkerOptions()
                .position(norfolkPark)
                .title("Norfolk park"));
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(norfolkPark,DEFAULT_ZOOM));

        LatLng westonPark = new LatLng(53.381658, -1.492219);
        mMap.addMarker(new MarkerOptions()
                .position(westonPark)
                .title("Weston Park"));

        LatLng endcliffePark = new LatLng(53.367770, -1.507375);
        mMap.addMarker(new MarkerOptions()
                .position(endcliffePark)
                .title("Endcliffe Park"));

        LatLng crookesValleyPark = new LatLng(53.383393, -1.49272);
        mMap.addMarker(new MarkerOptions()
                .position(crookesValleyPark)
                .title("Crookes Valley Park"));

        LatLng westStreetLive = new LatLng(53.38111017294563, -1.4759220852568413);
        mMap.addMarker(new MarkerOptions()
                .position(westStreetLive)
                .title("West Street Live"));

        LatLng commonRoom = new LatLng(53.379516476942044, -1.4771989447787301);
        mMap.addMarker(new MarkerOptions()
                .position(commonRoom)
                .title("The Common Room"));

        LatLng kuckoo = new LatLng(53.381938867208575, -1.4718841447786157);
        mMap.addMarker(new MarkerOptions()
                .position(kuckoo)
                .title("Kuckoo"));

        LatLng nurseryTavern = new LatLng(53.37177780950421, -1.488964273614901);
        mMap.addMarker(new MarkerOptions()
                .position(nurseryTavern)
                .title("The Nursery Tavern"));

        LatLng howSt = new LatLng(53.37881687986087, -1.4667369871072684);
        mMap.addMarker(new MarkerOptions()
                .position(howSt)
                .title("HowSt"));

        LatLng napoliPizzaCentro = new LatLng(53.37893417947089, -1.4896788024502903);
        mMap.addMarker(new MarkerOptions()
                .position(napoliPizzaCentro)
                .title("Napoli Pizza centro"));

        LatLng sheafIsland = new LatLng(53.37301220431047, -1.481930358271796);
        mMap.addMarker(new MarkerOptions()
                .position(commonRoom)
                .title("The Common Room"));
    }



    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionsGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    private void getDeviceLocation() {
        try {
            if (mLocationPermissionsGranted) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Task  location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Log.d(TAG, "onComplete : foundLocation");
                        lastKnownLocation = (Location) task.getResult();
                        moveCamera(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()),DEFAULT_ZOOM);
                    }else{
                        Log.d(TAG,"onComplete : current location is null");
                        Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        } catch (Exception e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }



    private void moveCamera (LatLng latLng, float zoom){
        Log.d(TAG,"moveCamera : moving camera to : lat" + latLng.latitude + "long : " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,   zoom));
    }

    private  void initMap(){
        Log.d(TAG, "initMap : initialzing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);

    }

    private void getLocationPermission(){
        Log.d(TAG,"getLocationPermissions : getting Location Permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};


        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PREMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PREMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"onRequestPermissionResult : funcion called");
        mLocationPermissionsGranted = false;

        switch (requestCode){
            case LOCATION_PREMISSION_REQUEST_CODE:{
                if(grantResults.length > 0) {
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG,"onRequestPermissionResult : Permissions Failed");
                            return;
                        }
                    }
                    Log.d(TAG,"onRequestPermissionResult : Permissions Granted");
                    mLocationPermissionsGranted = true;
                    //initialise our map
                    initMap();
                }
            }
        }
    }

}
