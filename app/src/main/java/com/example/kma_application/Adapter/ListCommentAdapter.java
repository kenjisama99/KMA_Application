package com.example.kma_application.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.kma_application.AsyncTask.DeletePostTask;
import com.example.kma_application.Models.Comment;
import com.example.kma_application.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class ListCommentAdapter extends RecyclerView.Adapter<ListCommentAdapter.MyViewHolder> {
    Context context;
    ArrayList<Comment> modelCommentArrayList = new ArrayList<>();
    List<JSONObject> comments = new ArrayList<>();
    RequestManager glide;

    public ListCommentAdapter(Context context, ArrayList<Comment> modelCommentArrayList){
        this.context = context;
        this.modelCommentArrayList = modelCommentArrayList;
        glide = Glide.with( context );
    }

    public ListCommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_comment, parent, false);
        ListCommentAdapter.MyViewHolder viewHolder = new ListCommentAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListCommentAdapter.MyViewHolder holder, int position) {
        JSONObject post = comments.get(position);

        try {
            holder.tvNameComment.setText(post.getString("name"));
            holder.tvTimeComment.setText(post.getString("date"));

            if (post.getString("type").equals("text")) {
                System.out.println("text");
                holder.imgViewComment.setVisibility(View.GONE);
                holder.tvComment.setVisibility(View.VISIBLE);
                holder.tvComment.setText(post.getString("content"));
            } else {
                System.out.println("image");
                holder.imgViewComment.setVisibility(View.VISIBLE);
                holder.tvComment.setVisibility(View.GONE);
                Bitmap bitmap = getBitmapFromString(post.getString("content"));
                holder.imgViewComment.setImageBitmap(bitmap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() { return comments.size();}

    public void addItem (JSONObject jsonObject) {
        comments.add(jsonObject);
        notifyDataSetChanged();
    }
    public void DelItem (int position) {
        comments.remove(position);
        notifyDataSetChanged();
    }
    public void clear () {
        comments.clear();
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameComment, tvComment, tvTimeComment, tv_del;
        ImageView imgViewUser, imgViewComment;

        public MyViewHolder(View itemView){
            super(itemView);

            imgViewUser = (ImageView)itemView.findViewById( R.id.imgViewUser );
            imgViewComment = (ImageView)itemView.findViewById( R.id.imgViewComment );

            tvNameComment = (TextView) itemView.findViewById( R.id.tvNameComment );
            tvComment = (TextView) itemView.findViewById( R.id.tvComment );
            tvTimeComment = (TextView) itemView.findViewById( R.id.tvTimeComment );
            tv_del = (TextView) itemView.findViewById( R.id.tv_del );

        }
    }
    private Bitmap getBitmapFromString(String image) {

        byte[] bytes = Base64.decode(image, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
