package com.example.kma_application.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

public class LoadLessonTask extends AsyncTask<Void,Void,String> {
    Context context;
    ImageView imgLesson;
    EditText txtLessonContent, txtLessonTitle;
    String _class;

    public LoadLessonTask(Context context, ImageView imgLesson, EditText txtLessonContent, EditText txtLessonTitle, String _class) {
        this.context = context;
        this.imgLesson = imgLesson;
        this.txtLessonContent = txtLessonContent;
        this.txtLessonTitle = txtLessonTitle;
        this._class = _class;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String postResponse = doPostRequest(
                    "https://nodejscloudkenji.herokuapp.com/getClassLesson",
                    classJson()
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
                Toast.makeText(this.context, jsonobject.getString("response"), Toast.LENGTH_LONG).show();
            }else {
                String title = jsonobject.getString("title");
                String content = jsonobject.getString("content");
                String image = jsonobject.getString("image");

                txtLessonTitle.setText(title);
                txtLessonContent.setText(content);
                imgLesson.setImageBitmap(getBitmapFromString(image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this.context, "Lỗi tải bữa ăn!", Toast.LENGTH_LONG).show();
        }
    }



    // post request code here
    String classJson() {
        //get current day for fetch today lesson
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        //to String
        String date =
                calendar.get(Calendar.DAY_OF_MONTH)+"/"+
                        calendar.get(Calendar.MONTH)+"/"+
                        calendar.get(Calendar.YEAR);
        String result = "{\"_class\":\"" + _class + "\","
                +"\"date\":\"" + date +"\"}";
        //System.out.println(result);
        return result;
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
    private Bitmap getBitmapFromString(String image) {

        byte[] bytes = Base64.decode(image, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
