package com.example.kma_application.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.kma_application.Fragment.NewfeedFragment;
import com.example.kma_application.Models.ModelFeed;
import com.example.kma_application.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class ListNewfeedAdapter extends RecyclerView.Adapter<ListNewfeedAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    List<JSONObject> posts = new ArrayList<>();
    RequestManager glide;

    public ListNewfeedAdapter(Context context, ArrayList<ModelFeed> modelFeedArrayList) {

        this.context = context;
        this.modelFeedArrayList = modelFeedArrayList;
        glide = Glide.with( context );

    }

    public ListNewfeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JSONObject post = posts.get(position);

        try {
            holder.tv_name.setText(post.getString("name"));
            holder.tv_time.setText(post.getString("date"));
            holder.tv_likes.setText(String.valueOf(post.getInt("like")));
            holder.tv_comments.setText(post.getInt("comment") + " bình luận");
            holder.tv_status.setText(post.getString("description"));

            String previewImgB64 = post.getString("previewImg");

            if (isEmpty(previewImgB64)) {
                holder.imgView_postPic.setVisibility(View.GONE);
            } else {
                holder.imgView_postPic.setVisibility(View.VISIBLE);

                Bitmap bitmap = getBitmapFromString(previewImgB64);
                holder.imgView_postPic.setImageBitmap(bitmap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_time, tv_likes, tv_comments, tv_status;
        ImageView imgView_proPic, imgView_postPic;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgView_proPic = (ImageView) itemView.findViewById(R.id.imgView_proPic);
            imgView_postPic = (ImageView) itemView.findViewById(R.id.imgView_postPic);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_likes = (TextView) itemView.findViewById(R.id.tv_like);
            tv_comments = (TextView) itemView.findViewById(R.id.tv_comment);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }

    private Bitmap getBitmapFromString(String image) {

        byte[] bytes = Base64.decode(image, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addItem (JSONObject jsonObject) {
        posts.add(jsonObject);
        notifyDataSetChanged();
    }
    public void clear () {
        posts.clear();
        notifyDataSetChanged();
    }

}