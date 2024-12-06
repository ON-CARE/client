package com.example.oncare;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;

    // 테스트용 위치
    private final LatLng CBNU = new LatLng(36.62954021524806, 127.45759307328647);
    private final LatLng CHEONGJU= new LatLng(36.64248175466709, 127.48901341306687);
    private final LatLng ICN = new LatLng(37.44951559713306, 126.4501346530293);

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_map, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.map, mapFragment)
                    .commit();
        }
        mapFragment.getMapAsync(this);
        }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setPadding(0, 80, 0, 0);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        // 위치 권한 체크 + 현재 위치 적용
        checkLocationPermission();
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true); // 내 위치 레이어 활성화

            fusedLocationProviderClient.getLastLocation() // 현재 위치로 카메라 이동
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                        }
                    });
            
            map.setOnMyLocationButtonClickListener(this); // 내 위치 버튼 활성화
        }

        // 마커 표시
        map.addMarker(new MarkerOptions().position(CBNU).title("충북대학교").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        map.addMarker(new MarkerOptions().position(CHEONGJU).title("청주시청").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        map.addMarker(new MarkerOptions().position(ICN).title("인천국제공항").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        map.setOnMarkerClickListener(this); // 마커 클릭 활성화
    }


    @Override
    public boolean onMyLocationButtonClick() { // 내 위치 버튼 클릭되었을 때
        return false;
    }

    @Override
    public boolean onMarkerClick(@NonNull com.google.android.gms.maps.model.Marker marker) { // 마커 클릭되었을 때

        Toast.makeText(this.getContext(),
                marker.getTitle() + " 마커가 클릭되었습니다. ",
                Toast.LENGTH_SHORT).show();
        return false;
    }

    private void checkLocationPermission() {
        // 위치 권한이 이미 허용되었는지 확인
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // 권한이 허용되지 않았으므로 요청
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // 권한이 이미 허용됨
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 허용됨
                Toast.makeText(getContext(), "위치 권한 허용됨", Toast.LENGTH_SHORT).show();
            } else {
                // 권한 거부됨
                Toast.makeText(getContext(), "위치 권한 거부됨", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
