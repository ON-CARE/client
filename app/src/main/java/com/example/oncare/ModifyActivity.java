package com.example.oncare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModifyActivity extends AppCompatActivity {

    Button btn_modi_email, btn_modi_pwd, btn_update;
    TextView txt_email, edit_name, edit_birthday, edit_phone, edit_call;
    RadioGroup radio_sex;
    RadioButton radio_m, radio_f;
    ImageView btn_back;
    String old_pwd;
    String old_birthday;
    String old_name;
    String old_phone;
    String old_call;
    String new_pwd;
    String new_pwd_again;
    String new_name;
    String new_birthday;
    String new_phone;
    String new_call;
    ModPwdDialog modPwdDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_modi_email = findViewById(R.id.btn_modi_email);
        btn_back = findViewById(R.id.btn_back);
        btn_modi_pwd = findViewById(R.id.btn_modi_pwd);
        btn_update = findViewById(R.id.btn_update);
        txt_email = findViewById(R.id.data_email);
        edit_name = findViewById(R.id.edit_modi_name);
        edit_birthday = findViewById(R.id.edit_modi_birthday);
        edit_phone = findViewById(R.id.edit_modi_phone);
        edit_call = findViewById(R.id.edit_modi_call);
        radio_sex = findViewById(R.id.radio_sex);
        radio_m=findViewById(R.id.radio_m);
        radio_f=findViewById(R.id.radio_f);

        modPwdDialog = new ModPwdDialog(this);

        // 테스트용
        old_name = "김김김";
        old_birthday = "20211010";
        old_phone = "01012345678";
        old_call = "119";
        // 현재 성별에 따라 라디오 버튼 기본값 지정
        radio_m.setChecked(true);


            // 현재 정보 표시
            edit_name.setHint(old_name);
            edit_birthday.setHint(old_birthday);
            edit_phone.setHint(old_phone);
            edit_call.setHint(old_call);

            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent back = new Intent(ModifyActivity.this, MainActivity.class);
                    startActivity(back);
                }
            });

            btn_modi_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 이메일 변경 버튼 클릭 시
                }
            });

            btn_modi_pwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modPwdDialog.show();
                    modPwdDialog.btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            old_pwd = modPwdDialog.getOld();
                            new_pwd = modPwdDialog.getNew();
                            new_pwd_again = modPwdDialog.getAgain();

                            // 검사
                            if (old_pwd.equals(new_pwd)) {
                                Log.v("Did I make it?", "현재 비밀번호 == 새 비밀번호");
                            }

                            // 업데이트

                            modPwdDialog.dismiss();
                        }
                    });
                }
            });

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new_name = String.valueOf(edit_name.getText());
                    new_birthday = String.valueOf(edit_birthday.getText());
                    new_phone = String.valueOf(edit_phone.getText());
                    new_call = String.valueOf(edit_call.getText());
                    if (radio_sex.getCheckedRadioButtonId() == R.id.radio_m) {
                        Log.v("라디오 버튼 - 성별 저장 ", "남성");
                    } else {
                        Log.v("라디오 버튼 - 성별 저장", "여성");
                    }

                }
            });


        }
    }