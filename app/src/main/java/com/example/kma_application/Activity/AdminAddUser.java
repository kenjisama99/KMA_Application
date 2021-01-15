package com.example.kma_application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.kma_application.R;

public class AdminAddUser extends AppCompatActivity {

    private String textSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);

        EditText editTextSelectClass = (EditText)findViewById(R.id.editTextSelectClass);
        TextView txtChildName = (TextView)findViewById(R.id.txtChildName);
        EditText editTextChildName = (EditText)findViewById(R.id.editTextChildName);

        RadioButton rdParent = (RadioButton)findViewById(R.id.radioButtonParent);
        rdParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChildName.setVisibility(View.VISIBLE);
                editTextChildName.setVisibility(View.VISIBLE);
            }
        });

        RadioButton rdTeacher = (RadioButton)findViewById(R.id.radioButtonTeacher);
        rdTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtChildName.setVisibility(View.INVISIBLE);
                editTextChildName.setVisibility(View.INVISIBLE);
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
    };
}