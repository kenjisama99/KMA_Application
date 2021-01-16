package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.SubmitNotificationTask;
import com.example.kma_application.Models.Prescription;
import com.example.kma_application.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AdminCreateNotificationActivity extends AppCompatActivity {
    String role;
    EditText txtNofiTitle, txtNofiContent;
    Button  buttonPost;
    ImageButton btAddNofiImg;
    ImageView imgNotifi;

    private  final int IMAGE_REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        txtNofiTitle = (EditText) findViewById(R.id.txtNofiTitle);
        txtNofiContent = (EditText) findViewById(R.id.txtNofiContent);

        btAddNofiImg =(ImageButton) findViewById(R.id.btAddNofiImg);
        buttonPost =(Button) findViewById(R.id.buttonPost);

        imgNotifi =(ImageView) findViewById(R.id.imgNotifi);

        Intent intent = getIntent();
        role = intent.getStringExtra("role");

        try {
            String title = intent.getStringExtra("title");
            if (title != null)
                txtNofiTitle.setText(title);

            String content = intent.getStringExtra("content");
            if (content != null)
                txtNofiContent.setText(content);

            String image = intent.getStringExtra("image");
            if (image != null)
                setImage(image);

            if ( !role.equals("admin")){
                txtNofiTitle.setFocusable(false);
                txtNofiTitle.setClickable(false);
                btAddNofiImg.setVisibility(View.INVISIBLE);
                buttonPost.setVisibility(View.INVISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPost();
            }
        });
        btAddNofiImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddImg();
            }
        });
    }

    private void setImage(String image) {
        byte[] bytes = Base64.decode(image, Base64.NO_WRAP);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imgNotifi.setImageBitmap(bitmap);
    }

    private void onClickAddImg() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent, "Pick image"),
                IMAGE_REQUEST_ID);

    }

    private void onClickPost() {
        String  title = txtNofiTitle.getText().toString().trim();
        String content = txtNofiContent.getText().toString().trim();
        String notification = "";
        boolean OK = true;

        if (TextUtils.isEmpty(title)) {
            notification ="Vui lòng nhập tiêu đề";
            OK = false;
        }
        if (TextUtils.isEmpty(content)) {
            notification = "Vui lòng nhập nội dung";
            OK = false;
        }

        if ( !OK){
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
            return;
        }else {
            Bitmap bitmap =((BitmapDrawable)imgNotifi.getDrawable()).getBitmap();
            bitmap = resize(bitmap,800,800);

            ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream2);
            String resizeBase64 = Base64.encodeToString(outputStream2.toByteArray(), Base64.NO_WRAP);
            System.out.println(resizeBase64);

            new SubmitNotificationTask(
                    this,
                    title,
                    content,
                    resizeBase64
            ).execute();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                imgNotifi.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float) maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float) maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }
}