package com.example.kma_application.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kma_application.Adapter.ListCommentAdapter;
import com.example.kma_application.AsyncTask.LoadCommentsTask;
import com.example.kma_application.AsyncTask.SubmitCommentTask;
import com.example.kma_application.AsyncTask.SubmitImageTask;
import com.example.kma_application.AsyncTask.SubmitPostTask;
import com.example.kma_application.Models.Comment;
import com.example.kma_application.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.kma_application.Activity.GalleryActivity.resize;

public class ViewCommentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Comment> modelCommentArrayList = new ArrayList<>();
    ListCommentAdapter listCommentAdapter;
    ImageButton imgBtClose, btCommentImage, btSendComment;
    String name, postId;
    EditText txtEnterComment;
    private final int IMAGE_REQUEST_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_comment );

        imgBtClose = (ImageButton)findViewById( R.id.imgBtClose );
        btCommentImage = (ImageButton)findViewById( R.id.btCommentImage );
        btSendComment = (ImageButton)findViewById( R.id.btSendComment );
        txtEnterComment = (EditText) findViewById( R.id.txtEnterComment );
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewComment);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listCommentAdapter = new ListCommentAdapter( this, modelCommentArrayList );
        recyclerView.setAdapter( listCommentAdapter );

        Intent data = getIntent();
        try {
            postId = data.getStringExtra("postId");
            name = data.getStringExtra("name");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Không lấy được intent",Toast.LENGTH_LONG).show();
        }
        //preload list cmt
        new LoadCommentsTask(
                this,
                postId,
                listCommentAdapter
        ).execute();

        imgBtClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        btCommentImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        } );
        btSendComment.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSentCmt();
            }
        } );
    }

    private void onClickSentCmt() {
        String  content = txtEnterComment.getText().toString().trim().replaceAll("(\\r|\\n​|\\r\\n|\n)+", "\\\\n");
        String notification = "";
        boolean OK = true;

        if (TextUtils.isEmpty(content)) {
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
        }else {
            JSONObject commentJSON = new JSONObject();
            try {
                commentJSON.put("postId", postId);
                commentJSON.put("name", name);
                commentJSON.put("type", "text");
                commentJSON.put("content", content);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            new SubmitCommentTask(
                    this,
                    commentJSON,
                    listCommentAdapter,
                    recyclerView,
                    txtEnterComment
            ).execute();
        }
    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_ID);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("TAG", "RQ code: " + requestCode + "\tRS code: " + resultCode);
        //Toast.makeText(this,"RQ code: "+requestCode+"\tRS code: "+resultCode,Toast.LENGTH_LONG).show();
        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {
            try {
                Uri imageURI = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(imageURI);
                Bitmap originalBitmap = BitmapFactory.decodeStream(inputStream);
                //imgAvatar.setImageBitmap(bitmap);
                Bitmap resizeBitmap = resize(originalBitmap, 500, 500);
                sendImage(originalBitmap, resizeBitmap);
                //getImagesFromServer();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendImage(Bitmap originalBitmap, Bitmap resizeBitmap) {
        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream2);
        String resizeBase64 = Base64.encodeToString(outputStream2.toByteArray(), Base64.NO_WRAP);
        System.out.println(resizeBase64);

        JSONObject commentJSON = new JSONObject();
        try {
            commentJSON.put("postId", postId);
            commentJSON.put("name", name);
            commentJSON.put("type", "image");
            commentJSON.put("content", resizeBase64);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new SubmitCommentTask(
                this,
                commentJSON,
                listCommentAdapter,
                recyclerView,
                txtEnterComment
        ).execute();
    }
    private void populateRecyclerView() {

        Comment modelComment = new Comment( 1, 5, R.drawable.teacherava,"Lê Thị Thanh", "Ảnh đẹp quá...", "30 phút" );
        modelCommentArrayList.add( modelComment );
        modelComment = new Comment(2, 9, R.drawable.techaerava1, R.drawable.smile,
                "Lê Thị Thanh", "Ảnh đẹp quá...", "30 phút");
        modelCommentArrayList.add( modelComment );

        listCommentAdapter.notifyDataSetChanged();
    }
}