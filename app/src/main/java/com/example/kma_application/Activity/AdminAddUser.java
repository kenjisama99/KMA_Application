package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.SubmitNotificationTask;
import com.example.kma_application.AsyncTask.SubmitUserTask;
import com.example.kma_application.R;

import java.io.ByteArrayOutputStream;

public class AdminAddUser extends AppCompatActivity {
    EditText editTextSelectClass, editTextChildName,
            editTextName, editTextPhoneNumber,
            editTextEmail, editTextPassword;

    Boolean selectParent = true;

    private String textSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhoneNumber);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextSelectClass = (EditText)findViewById(R.id.editTextSelectClass);
        TextView txtChildName = (TextView)findViewById(R.id.txtChildName);
        editTextChildName = (EditText)findViewById(R.id.editTextChildName);

        RadioButton rdParent = (RadioButton)findViewById(R.id.radioButtonParent);
        rdParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChildName.setVisibility(View.VISIBLE);
                editTextChildName.setVisibility(View.VISIBLE);
                selectParent = true;
            }
        });

        RadioButton rdTeacher = (RadioButton)findViewById(R.id.radioButtonTeacher);
        rdTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChildName.setVisibility(View.INVISIBLE);
                editTextChildName.setVisibility(View.INVISIBLE);
                selectParent = false;
            }
        });

        Button btSelectClass = (Button)findViewById(R.id.buttonSelectClass);
        btSelectClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), btSelectClass);
                dropDownMenu.getMenuInflater().inflate(R.menu.item_list_class, dropDownMenu.getMenu());
//                btSelectClass.setText("Chọn");

                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        textSelected = String.valueOf(menuItem.getTitle());
                        editTextSelectClass.setText(textSelected);
//                        btSelectClass.setText(textSelected);
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });

        Button btSignUp =  (Button) findViewById(R.id.buttonSignUp);
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignUp();
            }
        });
    }

    private void onClickSelectClass() {

    }

    private void onClickSignUp() {
        String  name = editTextName.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String _class = editTextSelectClass.getText().toString().trim();
        String childName = editTextChildName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        String notification = "";
        boolean OK = true;

        if (TextUtils.isEmpty(name)) {
            notification ="Vui lòng nhập tên";
            OK = false;
        }
        if (TextUtils.isEmpty(phone)) {
            notification = "Vui lòng nhập số điện thoại";
            OK = false;
        }if (TextUtils.isEmpty(email)) {
            notification = "Vui lòng nhập email";
            OK = false;
        }if (TextUtils.isEmpty(_class)) {
            notification = "Vui lòng chọn lớp";
            OK = false;
        }if (TextUtils.isEmpty(childName) && selectParent) {
            notification = "Vui lòng nhập tên trẻ";
            OK = false;
        }if (TextUtils.isEmpty(password)) {
            notification = "Vui lòng nhập mật khẩu";
            OK = false;
        }

        if ( !OK){
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
            return;
        }else {

            if(selectParent){
                new SubmitUserTask(
                        this,
                        "parent",
                        phone,
                        password,
                        name,
                        email,
                        _class,
                        childName
                ).execute();
            }else {
                new SubmitUserTask(
                        this,
                        "parent",
                        phone,
                        password,
                        name,
                        email,
                        _class,
                        ""
                ).execute();
            }
        }

    }
}