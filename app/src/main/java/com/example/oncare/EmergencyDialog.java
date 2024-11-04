package com.example.oncare;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import java.util.Objects;

public class EmergencyDialog extends Dialog {

    Button btn_no, btn_yes;

    public EmergencyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_emergency);
        setCancelable(false);

        btn_yes=findViewById(R.id.btn_yes);
        btn_no=findViewById(R.id.btn_no);

    }
}
