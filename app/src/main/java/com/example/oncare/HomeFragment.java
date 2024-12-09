package com.example.oncare;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    TextView txt_name, data_heartrate, data_step, data_ecg, nim;
    int heartrate, step; // 심박수, 걸음수 저장
    String ecg, user_name;// 심전도 데이터, 사용자 이름 데이터 저장
    Button btn_test;
    EmergencyDialog emergencyDialog;
    ConfirmDialog confirmDialog;
    ConstraintLayout tab_step;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emergencyDialog = new EmergencyDialog(getContext());
        confirmDialog = new ConfirmDialog(getContext(), R.string.call_dialog_title, R.string.call_dialog_msg);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        txt_name=v.findViewById(R.id.user_name);
        nim=v.findViewById(R.id.nim);
        data_heartrate=v.findViewById(R.id.data_heartrate);
        data_step=v.findViewById(R.id.data_step);
        data_ecg=v.findViewById(R.id.data_ecg);
        tab_step=v.findViewById(R.id.tab_step);
        txt_name.bringToFront();
        nim.bringToFront();

        btn_test=v.findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener() { // 위험신호 감지 팝업 테스트용
            @Override
            public void onClick(View v) {
                emergencyDialog.show();
                emergencyDialog.btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emergencyDialog.dismiss();
                    }
                });
                emergencyDialog.btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emergencyDialog.dismiss();
                        confirmDialog.show();
                    }
                });
            }
        });

        tab_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_step_detail = new Intent(getActivity(), StepActivity.class);
                startActivity(to_step_detail);
            }
        });


        heartrate=120;
        step=7878;
        ecg="부정맥";
        user_name="김김김김";  // 테스트용

        txt_name.setText(user_name);
        data_heartrate.setText(Integer.toString(heartrate));
        data_step.setText(Integer.toString(step));
        data_ecg.setText(ecg);


        return v;

    }
}