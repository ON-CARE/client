package com.example.oncare;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ConfirmDialog extends Dialog {

    TextView txt_title, txt_msg;
    Button btn_close;

    public ConfirmDialog(@NonNull Context context, int contents_title, int contents_msg) {
        super(context);
        setContentView(R.layout.confirm_dialog);

        txt_title=findViewById(R.id.dialog_title);
        txt_msg=findViewById(R.id.dialog_msg);
        btn_close=findViewById(R.id.btn_close);

        txt_title.setText(contents_title);
        txt_msg.setText(contents_msg);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
}
