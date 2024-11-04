package com.example.oncare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

public class SignActivity2 extends AppCompatActivity {

    ImageView btn_back, tooltip_questionmark;
    Button btn_start;
    EditText edit_name, edit_birthday, edit_phone, edit_emergency_call;
    RadioGroup radio_sex;
    String name_input, birthday_input, phone_input, emergency_call_input;
    Balloon tooltip_emergency_call, toast_fail;
    char sex_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_back=findViewById(R.id.btn_back);
        tooltip_questionmark=findViewById(R.id.tooltip_emergency_call);
        btn_start=findViewById(R.id.btn_start);
        edit_name=findViewById(R.id.edit_name);
        edit_birthday=findViewById(R.id.edit_bithday);
        edit_phone=findViewById(R.id.edit_phone);
        edit_emergency_call=findViewById(R.id.edit_emergency_call);
        radio_sex=findViewById(R.id.radio_sex);

        // 툴팁 객체 생성
        tooltip_emergency_call = new Balloon.Builder(getApplicationContext())
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.TOP)
                .setIsVisibleArrow(true)
                .setArrowPosition(0.3f)
                .setWidthRatio(0.4f)
                .setHeight(60)
                .setPadding(5)
                .setTextSize(10f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText("위험 신호 발생 시, 신고할 연락처.\n" +
                        "입력하지 않으면 119로 설정됩니다.")
                .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.theme_blue))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();

        toast_fail = new Balloon.Builder(getApplicationContext())
                .setWidthRatio(0.4f)
                .setHeight(60)
                .setIsVisibleArrow(false)
                .setPadding(5)
                .setTextSize(10f)
                .setCornerRadius(30f)
                .setAlpha(0.8f)
                .setText("빈 칸 없이 입력해주세요.")
                .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.theme_blue))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();

        tooltip_questionmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 물음표 아이콘 클릭
                tooltip_emergency_call.showAlignTop(tooltip_questionmark, 71, -10);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 뒤로가기 버튼 클릭
                Intent to_sign1= new Intent(getApplicationContext(), SignActivity.class);
                startActivity(to_sign1);
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 시작하기 버튼 클릭
                name_input= String.valueOf(edit_name.getText());
                birthday_input= String.valueOf(edit_birthday.getText());
                phone_input= String.valueOf(edit_phone.getText());
                emergency_call_input= String.valueOf(edit_emergency_call.getText());

                if(radio_sex.getCheckedRadioButtonId()==0)
                    sex_input='m';
                else sex_input='f';

                // 미입력 영역 체크
                if(name_input.isEmpty() || birthday_input.isEmpty() || phone_input.isEmpty() || emergency_call_input.isEmpty() || radio_sex.getCheckedRadioButtonId()==-1){
                    toast_fail.showAlignTop(btn_start, 0, -50);
                    toast_fail.dismissWithDelay(1300L);
                }
                else{
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                }

            }
        });

    }
}