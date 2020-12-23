package com.example.kma_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kma_application.Models.InfoModel;
import com.example.kma_application.Models.Parent;
import com.example.kma_application.Models.Teacher;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class contact extends AppCompatActivity {
    String phone, role;
    InfoModel infoModel;
    EditText editText;
    ImageButton btnCall, btnSend, btnSendImage;
    RecyclerView recyclerView;
    TextView txtChatName;



    Socket mSocket;
    private  final int IMAGE_REQUEST_ID = 1;
    private  final  String CLIENT_SEND_CHAT = "CLIENT_SEND_CHAT";
    private  final  String SERVER_SEND_CHAT = "SERVER_SEND_CHAT";
    private  final  String CLIENT_SEND_PHONE = "CLIENT_SEND_PHONE";
    private  final  String CLIENT_SEND_TYPE = "CLIENT_SEND_TYPE";

    private MessageAdapter messageAdapter;


    {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        anhxa();

        getInfo();
        try {
            mSocket = IO.socket("https://nodejscloudkenji.herokuapp.com");
            //mSocket = IO.socket("http://192.168.1.68:3000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.on(SERVER_SEND_CHAT,onRetrieveData);

        messageAdapter = new MessageAdapter(phone, getLayoutInflater());
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSend();
            }
        });
        btnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSendImage();
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

    @Override
    protected void onResume() {
        super.onResume();
        mSocket.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSocket.disconnect();
    }

    private Emitter.Listener onRetrieveData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = (JSONObject) args[0];

                    messageAdapter.addItem(jsonObject);

                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                }
            });
        }
    };
    private void getInfo(){
        txtChatName = (TextView)findViewById(R.id.textChatName);
        Intent data = getIntent();
        role = data.getStringExtra("role");
        infoModel = (InfoModel) data.getSerializableExtra("info");
        phone = infoModel.getPhone();
        if (role.equals("parent")){
            Parent parent = (Parent) infoModel;
            txtChatName.setText("Cô "+parent.getTeacherName()+" - Lớp "+parent.get_class());
        }else {
            Teacher teacher = (Teacher) infoModel;
            txtChatName.setText("Phụ huynh lớp "+teacher.get_class());
        }
    }
    private void onClickSend() {
        String content = editText.getText().toString().trim();
        if ( !TextUtils.isEmpty(content) ){
            mSocket.emit(CLIENT_SEND_PHONE, phone);
            mSocket.emit(CLIENT_SEND_TYPE, "text");
            mSocket.emit(CLIENT_SEND_CHAT,content);
            editText.setText("");
        }
    }

    private void onClickSendImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent, "Pick image"),
                IMAGE_REQUEST_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                sendImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    private void sendImage(Bitmap image) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);

        String base64String = Base64.encodeToString(outputStream.toByteArray(),
                Base64.DEFAULT);

        mSocket.emit(CLIENT_SEND_PHONE, phone);
        mSocket.emit(CLIENT_SEND_TYPE, "image");
        mSocket.emit(CLIENT_SEND_CHAT,base64String);

    }

    private void anhxa() {
        recyclerView = findViewById(R.id.recyclerView);
        editText = (EditText)findViewById(R.id.editextContent);
        btnCall = (ImageButton)findViewById(R.id.buttonCall);
        btnSend = (ImageButton)findViewById(R.id.buttonSend);
        btnSendImage = (ImageButton)findViewById(R.id.buttonSendImage);
    }


}