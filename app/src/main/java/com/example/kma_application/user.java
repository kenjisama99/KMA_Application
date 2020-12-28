package com.example.kma_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kma_application.Activity.ChangePasswordActivity;
import com.example.kma_application.Activity.LoginActivity;
import com.example.kma_application.Models.InfoModel;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class user extends AppCompatActivity {
    Button btLogout, btChangePassword, btEditAvatar;
    EditText txtName, txtPhone, txtId;
    Socket mSocket;
    ImageView imgAvatar;
    Emitter.Listener onNewImage;
    String phone, role;
    InfoModel infoModel;
    private  final  String CLIENT_SEND_IMAGE = "CLIENT_SEND_IMAGE";
    private  final  String CLIENT_SEND_PHONE = "CLIENT_SEND_PHONE";
    private  final  String CLIENT_SEND_REQUEST = "CLIENT_SEND_REQUEST";
    private  final  String SERVER_SEND_IMAGE = "SERVER_SEND_IMAGE";
    private final int REQUEST_TAKE_PHOTO = 123;
    private final int REQUEST_CHOOSE_PHOTO = 321;


    {
        try {
            mSocket = IO.socket("https://nodejscloudkenji.herokuapp.com");
            //mSocket = IO.socket("http://192.168.1.68:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        onNewImage = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                handleNewImage(args[0]);
            }
        };
    }
    private  void getImageFromServer(){
        mSocket.emit(CLIENT_SEND_REQUEST, phone);
    }
    private void handleNewImage(Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                byte[] imageByteArr = (byte[]) arg;
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArr,0,imageByteArr.length);
                imgAvatar.setImageBitmap(bitmap);
            }
        });
    }

    private  void  takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }
    private  void  choosePicture(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "RQ code: "+requestCode+"\tRS code: "+resultCode);
        Toast.makeText(this,"RQ code: "+requestCode+"\tRS code: "+resultCode,Toast.LENGTH_LONG).show();
        if (requestCode == REQUEST_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            try {
                Uri imageURI = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(imageURI);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //imgAvatar.setImageBitmap(bitmap);
                bitmap = resize(bitmap, 100, 100);
                sendImage(bitmap);
                getImageFromServer();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

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
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }
    public byte[] getByteArrayFromBitmap(Bitmap bitmap){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private  void addControls(){
        btEditAvatar = (Button)findViewById(R.id.buttonEditAvatar);
        imgAvatar = (ImageView)findViewById(R.id.avatar);
    }
    private void addEvents(){
        btEditAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendImage();
                choosePicture();
            }
        });
    }

    private void sendImage(Bitmap bitmap) {
        byte[] bytes = getByteArrayFromBitmap(bitmap);
        Log.d("TAG", bytes.toString());
        mSocket.emit(CLIENT_SEND_PHONE, getPhone());
        mSocket.emit(CLIENT_SEND_IMAGE, bytes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user);

        btLogout = (Button)findViewById(R.id.buttonLogout);
        btChangePassword = (Button)findViewById(R.id.buttonChangePass);
        txtPhone = (EditText)findViewById(R.id.editTextPersonPhone);
        txtId = (EditText)findViewById(R.id.editTextPersonID);
        txtName = (EditText)findViewById(R.id.editTextPersonName);

        mSocket.connect();
        mSocket.on(SERVER_SEND_IMAGE, onNewImage);

        addControls();
        addEvents();
        loadInfos();

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtLogout();
            }
        });

        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePass();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSocket.disconnect();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mSocket.connect();
    }

    String infoJson(String phone) {
        return "{\"phone\":\"" + phone +"\"}";
    }
    private void loadInfos(){
        phone = infoModel.getPhone();
        getImageFromServer();
        Intent data = getIntent();
        role = data.getStringExtra("role");
        infoModel = (InfoModel) data.getSerializableExtra("info");

        //loadInfosTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        //loadInfosTask.execute(infoJson(phone));
                txtName.setText(infoModel.getName());
        txtId.setText(infoModel.getId());
        txtPhone.setText(infoModel.getPhone());

    }
    private String getPhone(){
        Intent data = getIntent();
        String phone = data.getStringExtra("phone");
        return phone;
    }
    private void onClickChangePass() {
        Intent data = getIntent();
        String phone = data.getStringExtra("phone");
        Intent changePassActivity = new Intent(this, ChangePasswordActivity.class);
        changePassActivity.putExtra("phone",phone);
        startActivity(changePassActivity);
    }

    private void onClickBtLogout() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }
}