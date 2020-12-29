package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.ChangePassTask;
import com.example.kma_application.AsyncTask.LoginTask;
import com.example.kma_application.R;

public class ChangePasswordActivity extends AppCompatActivity {
    Button btDoneChangePass;
    EditText txtOldPassword, txtNewPassword, txtNewPasswordPart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtOldPassword = (EditText)findViewById(R.id.editTextOldPassword);
        txtNewPassword = (EditText)findViewById(R.id.editTextNewPassword);
        txtNewPasswordPart2 = (EditText)findViewById(R.id.editTextNewPasswordPart2);
        
        btDoneChangePass = (Button)findViewById(R.id.buttonDoneChangPass);

        btDoneChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtDoneChangePass();
            }
        });
    }

    private void onClickBtDoneChangePass() {
        Intent data = getIntent();
        String phone = data.getStringExtra("phone");
        //Toast.makeText(this,""+phone,Toast.LENGTH_LONG).show();
        changPass(phone,
                txtOldPassword.getText().toString().trim(),
                txtNewPassword.getText().toString().trim(),
                txtNewPasswordPart2.getText().toString().trim());
    }


    // test data
    String userJson(String phone, String oldPassword, String newPassword) {
        return "{\"phone\":\"" + phone + "\","
                +"\"password\":\"" + oldPassword +"\","
                +"\"newPassword\":\"" + newPassword +"\"}";
    }
    private void changPass(String phone, String oldPassword, String newPassword, String newPasswordPart2) {
        if (TextUtils.isEmpty(oldPassword)) {

            Toast.makeText(this, "Mật khẩu cũ không được để trống", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(newPassword)){

            Toast.makeText(this,"Mật khẩu mới không được để trống",Toast.LENGTH_LONG).show();
            return;

        }if (TextUtils.isEmpty(newPasswordPart2)){

            Toast.makeText(this,"Nhập lại Mật khẩu mới không được để trống",Toast.LENGTH_LONG).show();
            return;
        }if (!newPassword.equals(newPasswordPart2)){

            Toast.makeText(this,"Nhập lại Mật khẩu mới không khớp",Toast.LENGTH_LONG).show();
            return;
        }
        ChangePassTask changePassTask = new ChangePassTask(this, phone);
        changePassTask.execute(userJson(phone,oldPassword,newPassword));

    
    }
}