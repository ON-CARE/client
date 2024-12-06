package com.example.oncare;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteActivity extends AppCompatActivity {


    private ImageView btn_back;
    private Button btn_delete;
    private ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_back=findViewById(R.id.btn_back);
        btn_delete=findViewById(R.id.btn_delete);

        confirmDialog = new ConfirmDialog(this, R.string.delete_dialog_title, R.string.delete_dialog_msg);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 뒤로가기 버튼 클릭
                Intent to_main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(to_main);
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로딩 애니메이션 추가 예정
                confirmDialog.show(); // 탈퇴 완료 팝업

                confirmDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) { // 팝업창 닫히고 로그인 화면으로 이동
                        Intent to_login = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(to_login);
                    }
                });
            }
        });
    }

}