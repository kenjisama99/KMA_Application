package com.example.kma_application.AsyncTask;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UnlikePostTask extends AsyncTask<Void,Void,String> {
    Context context;
    String postId, name, phone;
    TextView tv_like, txt_like_button;

    public UnlikePostTask(Context context, String postId, String name, String phone, TextView tv_like, TextView txt_like_button) {
        this.context = context;
        this.postId = postId;
        this.name = name;
        this.phone = phone;
        this.tv_like = tv_like;
        this.txt_like_button = txt_like_button;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String postResponse = doPostRequest(
                    "https://nodejscloudkenji.herokuapp.com/unlikePost",
                    reqJson()
            );
            //String postResponse = doPostRequest("http://192.168.1.68:3000/login", jsons[0]);
            return postResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String postResponse) {
        try {
            JSONObject jsonobject = new JSONObject(postResponse);
            if (jsonobject.has("res")){

                if(jsonobject.getBoolean("res")){
                    int newLike = Integer.parseInt(tv_like.getText().toString().trim()) - 1;
                    tv_like.setText(newLike+"");
                    txt_like_button.setText("Thích");
                    txt_like_button.setTextColor(Color.BLACK);
//                    listNewfeedAdapter.notifyDataSetChanged();
                }else
                    Toast.makeText(this.context, jsonobject.getString("response"), Toast.LENGTH_LONG).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this.context, "Đã có lỗi xảy ra, vui lòng thử lại sau!", Toast.LENGTH_LONG).show();
        }
    }

    // post request code here

    String reqJson() {

        return "{\"postId\":\"" + postId + "\","
                +"\"name\":\"" + name + "\","
                + "\"phone\":\"" + phone + "\"}";
    }


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String doPostRequest(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}