package com.example.kma_application.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
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

import java.util.ArrayList;

public class ListNewfeedAdapter extends RecyclerView.Adapter<ListNewfeedAdapter.MyViewHolder> {

    NewfeedFragment context;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    RequestManager glide;

//    public ListNewfeedAdapter(NewfeedFragment context, ArrayList<ModelFeed> modelFeedArrayList) {
//
//        this.context = context;
//        this.modelFeedArrayList = modelFeedArrayList;
//        glide = Glide.with(context);
//
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newfeed, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ModelFeed modelFeed = modelFeedArrayList.get(position);

        holder.tv_name.setText(modelFeed.getName());
        holder.tv_time.setText(modelFeed.getTime());
        holder.tv_likes.setText(String.valueOf(modelFeed.getLikes()));
        holder.tv_comments.setText(modelFeed.getComments() + " comments");
        holder.tv_status.setText(modelFeed.getStatus());

        glide.load(modelFeed.getPropic()).into(holder.imgView_proPic);

        if (modelFeed.getPostpic() == 0) {
            holder.imgView_postPic.setVisibility(View.GONE);
        } else {
            holder.imgView_postPic.setVisibility(View.VISIBLE);
            glide.load(modelFeed.getPostpic()).into(holder.imgView_postPic);
        }
    }

    @Override
    public int getItemCount() {
        return modelFeedArrayList.size();
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
}