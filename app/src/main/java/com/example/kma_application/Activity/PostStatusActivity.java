package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kma_application.AsyncTask.SubmitImageTask;
import com.example.kma_application.AsyncTask.SubmitNotificationTask;
import com.example.kma_application.AsyncTask.SubmitPostTask;
import com.example.kma_application.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.kma_application.Activity.GalleryActivity.resize;

public class PostStatusActivity extends AppCompatActivity {

    ImageButton btClose, btPostPicture, btPostVideo, btTakeAPhoto;
    Button btPost;
    ImageView imgPost;
    TextInputEditText txtPostInputText;
    private final int IMAGE_REQUEST_ID = 1;
    String  _class, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_status);

        btClose = (ImageButton)findViewById( R.id.imgBtClose );
        btPostPicture = (ImageButton)findViewById( R.id.btPostPicture );
        btPostVideo = (ImageButton)findViewById( R.id.btPostVideo );
        btTakeAPhoto = (ImageButton)findViewById( R.id.btTakeAPhoto );

        btPost = (Button)findViewById( R.id.btPost );

        imgPost = (ImageView) findViewById( R.id.imgPost );

        txtPostInputText = (TextInputEditText) findViewById( R.id.txtPostInputText );

        Intent data = getIntent();
        try {
            _class = data.getStringExtra("_class");
            name = data.getStringExtra("name");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Không lấy được intent",Toast.LENGTH_LONG).show();
        }
        btClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        btPost.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        } );
        btPostPicture.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        } );

    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_ID);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                imgPost.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void submitPost() {

        String  description = txtPostInputText.getText().toString().trim().replaceAll("(\\r|\\n​|\\r\\n|\n)+", "\\\\n");
        String notification = "";
        boolean OK = true;

        if (TextUtils.isEmpty(description)) {
            notification ="Vui lòng nhập nội dung";
            OK = false;
        }
//        if (imgPost.getDrawable() == null) {
//            notification = "Vui lòng chọn ảnh đính kèm";
//            OK = false;
//        }

        if ( !OK){
            Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
            return;
        }else if (imgPost.getDrawable() == null) {
            new SubmitPostTask(
                    this,
                    _class,
                    name,
                    description,
                    "",
                    "",
                    this
            ).execute();
        }else {
            Bitmap originalBitmap =((BitmapDrawable)imgPost.getDrawable()).getBitmap();
            Bitmap resizeBitmap = resize(originalBitmap, 400, 400);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            String originalBase64 = Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);

            ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
            resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream2);
            String resizeBase64 = Base64.encodeToString(outputStream2.toByteArray(), Base64.NO_WRAP);
            System.out.println(resizeBase64);

            new SubmitPostTask(
                    this,
                    _class,
                    name,
                    description,
                    originalBase64,
                    resizeBase64,
                    this
            ).execute();
        }

    }

}