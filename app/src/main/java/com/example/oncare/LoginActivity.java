package com.example.oncare;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    EditText edit_email, edit_pwd;
    Button btn_login;
    ImageView btn_sign;
    String email_input, pwd_input; // 사용자가 입력한 이메일과 비밀번호를 받는 문자열
    Balloon toast_fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        edit_email=findViewById(R.id.edit_email);
        edit_pwd=findViewById(R.id.edit_pwd);
        btn_login=findViewById(R.id.btn_login);
        btn_sign=findViewById(R.id.btn_sign);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 로그인 버튼 클릭

                // 사용자가 입력한 값 변수에 저장
                email_input= String.valueOf(edit_email.getText());
                pwd_input= String.valueOf(edit_pwd.getText());

                
                // 툴팁 객체 생성
                toast_fail = new Balloon.Builder(getApplicationContext())
                        .setWidthRatio(0.53f)
                        .setHeight(70)
                        .setIsVisibleArrow(false)
                        .setPadding(9)
                        .setTextSize(15f)
                        .setCornerRadius(30f)
                        .setAlpha(0.8f)
                        .setText("빈 칸 없이 입력해주세요.")
                        .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                        .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.theme_main))
                        .setBalloonAnimation(BalloonAnimation.FADE)
                        .build();

                // 빈 칸 체크
                //if(email_input.isEmpty() || pwd_input.isEmpty()){
                    //toast_fail.showAlignBottom(btn_login, 0, 250);
                    //toast_fail.dismissWithDelay(1300L);
                //}else{
                    // 로그인 성공 시, 홈 화면으로 이동
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                //}
                
            }
        });

        
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 회원가입 버튼 클릭

                Intent to_sign = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(to_sign);
            }
        });


    }
}