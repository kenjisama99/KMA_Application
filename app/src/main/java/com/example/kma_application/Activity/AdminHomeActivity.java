package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.kma_application.R;

public class AdminHomeActivity extends AppCompatActivity {
    Button showMenu;
    private String textSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        showMenu = (Button) findViewById(R.id.show_dropdown_menu);
        showMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), showMenu);
                dropDownMenu.getMenuInflater().inflate(R.menu.item_list_class, dropDownMenu.getMenu());
                showMenu.setText("Chọn");

                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        textSelected = String.valueOf(menuItem.getTitle());
                        showMenu.setText(textSelected);
                        return true;
                    }
                });
                dropDownMenu.show();

            }
        });

        Button btManageNotification = (Button)findViewById(R.id.buttonNotificationManage);
        btManageNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickManageNotification();
            }
        });

        Button btManageUser = (Button)findViewById(R.id.buttonUserManage);
        btManageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickManageUser();
            }
        });
        Button btManageGallery = (Button)findViewById(R.id.buttonAlbumManage);
        btManageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickManageGallery();
            }
        });
        Button btManageHealth = (Button)findViewById(R.id.buttonHealthManage);
        btManageHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickManageHealth();
            }
        });Button btManageAbsent = (Button)findViewById(R.id.buttonAbsentManage);
        btManageAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickManageAbsent();
            }
        });Button btManageMedicine = (Button)findViewById(R.id.buttonMedicineManage);
        btManageMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickManageMedicine();
            }
        });
    }

    private void onClickManageHealth() {
        if(!showMenu.getText().equals("Chọn")){
            Intent manageNotification = new Intent(this, TeacherHealthActivity.class);
            manageNotification.putExtra("class",showMenu.getText().toString().trim());
            startActivity(manageNotification);
        }
        else
            Toast.makeText(this,"Yêu cần bạn chọn lớp",Toast.LENGTH_LONG).show();
    }

    private void onClickManageAbsent() {
        if(!showMenu.getText().equals("Chọn")){
            Intent manageNotification = new Intent(this, TeacherAbsentActivity.class);
            manageNotification.putExtra("class",showMenu.getText().toString().trim());
            startActivity(manageNotification);
        }
        else
            Toast.makeText(this,"Yêu cần bạn chọn lớp",Toast.LENGTH_LONG).show();

    }

    private void onClickManageMedicine() {
        if(!showMenu.getText().equals("Chọn")){
            Intent manageNotification = new Intent(this, TeacherPrescriptionActivity.class);
            manageNotification.putExtra("class",showMenu.getText().toString().trim());
            startActivity(manageNotification);
        }
        else
            Toast.makeText(this,"Yêu cần bạn chọn lớp",Toast.LENGTH_LONG).show();
    }

    private void onClickManageGallery() {
        if(!showMenu.getText().equals("Chọn")){
            Intent manageNotification = new Intent(this, GalleryActivity.class);
            manageNotification.putExtra("class",showMenu.getText().toString().trim());
            manageNotification.putExtra("role","teacher");
            startActivity(manageNotification);
        }
        else
            Toast.makeText(this,"Yêu cần bạn chọn lớp",Toast.LENGTH_LONG).show();
    }

    private void onClickManageNotification() {
        Intent manageNotification = new Intent(this, AdminNotificationActivity.class);
        startActivity(manageNotification);
    }

    private void onClickManageUser() {
        Intent manageUser = new Intent(this, AdminManageUser.class);
        startActivity(manageUser);
    }
}