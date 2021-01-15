package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.kma_application.AsyncTask.LoadClassImageTask;
import com.example.kma_application.AsyncTask.SubmitImageTask;
import com.example.kma_application.Models.Teacher;
import com.example.kma_application.R;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GalleryActivity extends AppCompatActivity {
    String role;
    String _class;
    Teacher teacher;
    ImageButton btAdd;
    GridView gridView;
    private final int IMAGE_REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        btAdd = (ImageButton) findViewById(R.id.buttonAddImage);
        gridView = (GridView) findViewById(R.id.gridViewGallery);

        Intent data = getIntent();
        role = data.getStringExtra("role");
        //teacher = (Teacher) data.getSerializableExtra("info");
         _class = data.getStringExtra("class");
        if (role.equals("parent"))
            btAdd.setVisibility(View.INVISIBLE);

        getImagesFromServer();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendImage();
                choosePicture();
            }
        });
    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_ID);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "RQ code: " + requestCode + "\tRS code: " + resultCode);
        //Toast.makeText(this,"RQ code: "+requestCode+"\tRS code: "+resultCode,Toast.LENGTH_LONG).show();
        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {
            try {
                Uri imageURI = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(imageURI);
                Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                //imgAvatar.setImageBitmap(bitmap);
                Bitmap resizeBitmap = resize(originalBitmap, 100, 100);
                sendImage(originalBitmap, resizeBitmap);
                getImagesFromServer();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendImage(Bitmap originalBitmap, Bitmap resizeBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        String originalBase64 = Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);

        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream2);
        String resizeBase64 = Base64.encodeToString(outputStream2.toByteArray(), Base64.NO_WRAP);
        System.out.println(resizeBase64);

        SubmitImageTask submitImageTask = new SubmitImageTask(
                originalBase64,
                resizeBase64,
                "Gallery",
                _class
        );
        submitImageTask.execute();
    }

    private void getImagesFromServer() {
        new LoadClassImageTask(
            this,gridView,_class,"gallery")
                .execute();
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