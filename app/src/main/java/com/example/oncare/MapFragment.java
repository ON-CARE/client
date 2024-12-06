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
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.shape.MapPoints;


public class MapFragment extends Fragment {

    MapView map_view;
    KakaoMap map;
    Label[] markers;

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

                double current_la, current_long; // 현재 위도, 경도 값 저장

                // 위치 정보 불러오기  (테스트용)
                current_la = 36.62402852579321;
                current_long = 127.46142862451627;

                map = kakaoMap;

                LabelLayer layer = map.getLabelManager().getLayer();

                markCurrentLocation(layer, current_la, current_long);
                showMarker(layer);

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

    public void markCurrentLocation(LabelLayer layer, double latitude, double longitude){
        LabelStyles marker_current = map.getLabelManager()
                .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.icon_user_location)));
        LabelOptions options_current_location = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(marker_current);
        Label current_location = layer.addLabel(options_current_location);

    }

    public void showMarker(LabelLayer layer){

        LabelStyles marker_red = map.getLabelManager()
                .addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.icon_marker_red)));
        LabelStyles marker_green = map.getLabelManager().addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.icon_marker_green)));

        // options 객체에서 위도/경도 값, 마커 스타일 지정
        LabelOptions options_red = LabelOptions.from(LatLng.from(36.629559096022376,127.45736957742757 )).setStyles(marker_red);
        LabelOptions options_green = LabelOptions.from(LatLng.from(36.63200894071272,127.4606124901787 )).setStyles(marker_green);


        // 레이어에 레이블 띄우기
        Label pin_red = layer.addLabel(options_red);
        Label pin_green = layer.addLabel(options_green);
    }
}