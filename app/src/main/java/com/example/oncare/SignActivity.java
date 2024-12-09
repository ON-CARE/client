package com.example.oncare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;


public class SignActivity extends AppCompatActivity {

    ImageView btn_back, tooltip_warn;
    Button btn_next;
    EditText edit_email, edit_pwd, edit_pwd_again;
    String email_input, pwd_input, pwd_again_input; // 사용자가 입력한 이메일, 비밀번호, 비밀번호 확인 데이터를 저장함
    Balloon tooltip_pwd, tooltip_pwd_fail, toast_fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_back=findViewById(R.id.btn_back);
        tooltip_warn=findViewById(R.id.tooltip_warn);
        btn_next=findViewById(R.id.btn_next);
        edit_email=findViewById(R.id.edit_email);
        edit_pwd=findViewById(R.id.edit_pwd);
        edit_pwd_again=findViewById(R.id.edit_pwd_again);

        
        // 툴팁 객체 생성
        tooltip_pwd = new Balloon.Builder(getApplicationContext())
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.TOP)
                .setIsVisibleArrow(true)
                .setArrowPosition(0.37f)
                .setWidthRatio(0.53f)
                .setHeight(70)
                .setPadding(9)
                .setTextSize(17f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText("8~16자\n영문 대소문자, 숫자 포함")
                .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.theme_main))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();

        tooltip_pwd_fail = new Balloon.Builder(getApplicationContext())
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.BOTTOM)
                .setIsVisibleArrow(true)
                .setArrowPosition(0.2f)
                .setWidthRatio(0.53f)
                .setHeight(70)
                .setPadding(9)
                .setTextSize(17f)
                .setCornerRadius(4f)
                .setAlpha(0.9f)
                .setText("비밀번호가 일치하지 않습니다.\n다시 확인해주세요.")
                .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();

        toast_fail = new Balloon.Builder(getApplicationContext())
                .setWidthRatio(0.5f)
                .setHeight(65)
                .setIsVisibleArrow(false)
                .setPadding(9)
                .setTextSize(14f)
                .setCornerRadius(30f)
                .setAlpha(0.8f)
                .setText("빈 칸 없이 입력해주세요.")
                .setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.theme_main))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();

        tooltip_warn.setOnClickListener(new View.OnClickListener() { // 느낌표 아이콘 클릭
            @Override
            public void onClick(View v) {
                tooltip_pwd.showAlignTop(tooltip_warn, 71, -10); // 툴팁 표시
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 뒤로가기 버튼 클릭
                Intent to_login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(to_login); // 로그인 화면으로 되돌아감
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) { // 다음으로 버튼 클릭 시 동작
                // 입력된 문자열을 변수에 저장
                email_input= String.valueOf(edit_email.getText());
                pwd_input= String.valueOf(edit_pwd.getText());
                pwd_again_input= String.valueOf(edit_pwd_again.getText());

                // 미입력된 영역 체크
                if(email_input.isEmpty() || pwd_input.isEmpty() || pwd_again_input.isEmpty()){
                    toast_fail.showAlignTop(btn_next, 0, -50);
                    toast_fail.dismissWithDelay(1300L);
                }
                else{
                    // 비밀번호, 비밀번호 확인  일치하는지 체크
                    if(pwd_input.equals(pwd_again_input)){
                        Intent next =  new Intent(getApplicationContext(), SignActivity2.class);
                        startActivity(next);
                    }
                    else{
                        tooltip_pwd_fail.showAlignBottom(edit_pwd_again, -130, 12);
                        edit_pwd_again.setBackgroundResource(R.drawable.editext_sign_fail);
                        edit_pwd_again.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                edit_pwd_again.setBackgroundResource(R.drawable.editext_sign);
                                return false;
                            }
                        });
                    }

                }

            }
        });



    }
}