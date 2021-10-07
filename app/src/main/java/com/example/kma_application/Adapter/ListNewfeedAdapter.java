package com.example.kma_application.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.kma_application.Activity.PostStatusActivity;
import com.example.kma_application.Activity.ViewCommentActivity;
import com.example.kma_application.Activity.ViewStatusDetailActivity;
import com.example.kma_application.AsyncTask.DeletePostTask;
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
    String name, phone;

    public ListNewfeedAdapter(Context context, ArrayList<ModelFeed> modelFeedArrayList) {

        this.context = context;
        this.modelFeedArrayList = modelFeedArrayList;
        glide = Glide.with( context );

    }

    public ListNewfeedAdapter(Context context) {
        this.context = context;
    }

    public ListNewfeedAdapter(Context context, String name, String phone) {
        this.context = context;
        this.name = name;
        this.phone = phone;
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

            if(!name.equals(post.getString("name")))
                holder.tv_del.setVisibility(View.GONE);

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

        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCmt(position);
            }
        });
        holder.imgView_postPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImagePost(position);
            }
        });
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelPost(position);
            }
        });
    }

    private void onClickDelPost(int position) {
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setMessage("Xác nhận xóa.")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        delPostAt(position);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private void delPostAt(int position) {
        try {
            new DeletePostTask(
                    context,
                    posts.get(position).getString("postId"),
                    this,
                    position
            ).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onClickImagePost(int position) {
        Intent intent = new Intent(context, ViewStatusDetailActivity.class );
        JSONObject post = posts.get(position);
        try {
            intent.putExtra("postId", post.getString("postId"));
            intent.putExtra("date", post.getString("date"));
            intent.putExtra("teacherName", post.getString("name"));
            intent.putExtra("like", post.getInt("like"));
            intent.putExtra("description", post.getString("description"));
            intent.putExtra("comment", post.getInt("comment"));
            intent.putExtra("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }

    private void onClickCmt(int position) {
        Intent intent = new Intent(context, ViewCommentActivity.class );
        JSONObject post = posts.get(position);
        try {
            intent.putExtra("postId", post.getString("postId"));
            intent.putExtra("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        context.startActivity(intent);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_time, tv_likes, tv_comments, tv_status, tv_del;
        ImageView imgView_proPic, imgView_postPic;
        LinearLayout btnComment;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgView_proPic = (ImageView) itemView.findViewById(R.id.imgView_proPic);
            imgView_postPic = (ImageView) itemView.findViewById(R.id.imgView_postPic);

            btnComment = (LinearLayout) itemView.findViewById(R.id.btnComment);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_likes = (TextView) itemView.findViewById(R.id.tv_like);
            tv_comments = (TextView) itemView.findViewById(R.id.tv_comment);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_del = (TextView) itemView.findViewById(R.id.tv_del);
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
    public void DelItem (int position) {
        posts.remove(position);
        notifyDataSetChanged();
    }
    public void clear () {
        posts.clear();
        notifyDataSetChanged();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}