package com.example.oncare;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ModPwdDialog extends Dialog {

    Button btn_cancle, btn_submit;
    private EditText edit_old_pwd, edit_new_pwd, edit_new_pwd_again;

    public ModPwdDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.mod_pwd_dialog);

        btn_cancle=findViewById(R.id.btn_cancel);
        btn_submit=findViewById(R.id.btn_submit);
        edit_old_pwd=findViewById(R.id.edit_old_pwd);
        edit_new_pwd=findViewById(R.id.edit_new_pwd);
        edit_new_pwd_again=findViewById(R.id.edit_new_pwd_again);
    }

    public String getOld(){
        return String.valueOf(edit_old_pwd.getText());
    };
    public String getNew(){
        return String.valueOf(edit_new_pwd.getText());
    };
    public String getAgain(){
        return String.valueOf(edit_new_pwd_again.getText());
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
