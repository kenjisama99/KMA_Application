package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import com.example.kma_application.AsyncTask.LoadLessonTask;
import com.example.kma_application.AsyncTask.SubmitLessonTask;
import com.example.kma_application.AsyncTask.SubmitPostTask;
import com.example.kma_application.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import static com.example.kma_application.Activity.GalleryActivity.resize;

public class TeacherPostLessonActivity extends AppCompatActivity {
    private final int IMAGE_REQUEST_ID = 1;
    EditText  txtLessonContent, txtLessonTitle;
    ImageView imgLesson;
    String _class,date;
    private EditText editTextDate;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher_post_lesson);

        txtLessonContent= findViewById(R.id.txtLessonContent);
        txtLessonTitle= findViewById(R.id.txtLessonTitle);

        ImageButton btBackLesson = (ImageButton)findViewById( R.id.btBackLesson );
        ImageButton btLessonLesson = (ImageButton) findViewById( R.id.btLessonLesson );
        ImageButton btDelLesson = (ImageButton)findViewById( R.id.btDelLesson);
        ImageButton btAddImgLesson = (ImageButton)findViewById( R.id.btAddImgLesson);
        Button btSchedule = (Button) findViewById( R.id.imgBtSchedule );
        editTextDate = (EditText) findViewById(R.id.editTextDate);

        Intent data = getIntent();
        _class = data.getStringExtra("class");
        String role = data.getStringExtra("role");

        imgLesson = (ImageView)findViewById(R.id.imgLesson);
        if (role.equals("parent")){
            btLessonLesson.setVisibility(View.INVISIBLE);
            btDelLesson.setVisibility(View.INVISIBLE);
            btAddImgLesson.setVisibility(View.INVISIBLE);
            txtLessonContent.setFocusable(false);
            txtLessonContent.setClickable(false);
            txtLessonTitle.setFocusable(false);
            txtLessonTitle.setClickable(false);
        }
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        //to String
         date = calendar.get(Calendar.DAY_OF_MONTH)+"/"+
                        calendar.get(Calendar.MONTH)+"/"+
                        calendar.get(Calendar.YEAR);

        new LoadLessonTask(
                this,
                imgLesson,
                txtLessonContent,
                txtLessonTitle,
                _class,
                date
        ).execute();
        btBackLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btLessonLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPostlesson();
            }
        } );

        btDelLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelLesson();
            }
        } );

        btAddImgLesson.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImgLesson();
            }
        } );

        btSchedule.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSchedule();
            }
        } );

//         Get Current Date
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    }

    private void onClickSchedule() {
        DatePickerDialog.OnDateSetListener dateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                editTextDate.setVisibility( View.VISIBLE );
                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
                loadLessonOn(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        };

        new DatePickerDialog(
                this,
                dateSetListenerStart,
                lastSelectedYear,
                lastSelectedMonth,
                lastSelectedDayOfMonth
        ).show();

    }
    void  loadLessonOn(String date){
        new LoadLessonTask(
                this,
                imgLesson,
                txtLessonContent,
                txtLessonTitle,
                _class,
                date
        ).execute();
    }
    private void onClickImgLesson() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_ID);
    }

    private void onClickDelLesson() {
        //abc
    }

    private void onClickPostlesson() {
        String  title = txtLessonTitle.getText().toString().trim().replaceAll("(\\r|\\n​|\\r\\n|\n)+", "\\\\n");
        String  content = txtLessonContent.getText().toString().trim().replaceAll("(\\r|\\n​|\\r\\n|\n)+", "\\\\n");
        String notification = "";
        boolean OK = true;

        if (TextUtils.isEmpty(title)) {
            notification ="Vui lòng nhập nội dung";
            OK = false;
        }
        if (TextUtils.isEmpty(content)) {
            notification ="Vui lòng nhập nội dung";
            OK = false;
        }
        if (imgLesson.getDrawable() == null) {
            notification = "Vui lòng chọn ảnh đính kèm";
            OK = false;
        }

        if ( !OK){
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
            return;

        }else {
            Bitmap bitmap =((BitmapDrawable)imgLesson.getDrawable()).getBitmap();

            new SubmitLessonTask(
                    this,
                    imgLesson,
                    bitmap,
                    _class,
                    title,
                    content
            ).execute();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                imgLesson.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}