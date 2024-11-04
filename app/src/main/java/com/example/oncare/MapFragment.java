package com.example.oncare;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;


public class MapFragment extends Fragment {

    MapView map_view;
    KakaoMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KakaoMapSdk.init(getContext(), "305dc87fd221c7ec895d67b2ca481cd2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_map, container, false);
        map_view=v.findViewById(R.id.map_view);

        map_view.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {
                Log.d("KakaoMap", "onMapDestroy");
            }

            @Override
            public void onMapError(Exception e) {
                Log.e("KakaoMap", "onMapError: ");
            }
        }, new KakaoMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull KakaoMap kakaoMap) {
                map = kakaoMap;

            }
        });

        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        map_view.resume();
    }

    @Override
    public void onPause(){
        super.onPause();
        map_view.pause();
    }
}