package com.example.kma_application.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.kma_application.Models.Comment;
import com.example.kma_application.R;

import java.util.ArrayList;

import static com.example.kma_application.R.id.tvLikeComment;

public class ListCommentAdapter extends RecyclerView.Adapter<ListCommentAdapter.MyViewHolder> {
    Context context;
    ArrayList<Comment> modelCommentArrayList = new ArrayList<>();
    RequestManager glide;

    public ListCommentAdapter(Context context, ArrayList<Comment> modelCommentArrayList){
        this.context = context;
        this.modelCommentArrayList = modelCommentArrayList;
        glide = Glide.with( context );
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_comment, parent, false);
        ListCommentAdapter.MyViewHolder viewHolder = new ListCommentAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListCommentAdapter.MyViewHolder holder, int position) {
        final Comment modelComment = modelCommentArrayList.get(position);
    }

    @Override
    public int getItemCount() { return modelCommentArrayList.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameComment, tvComment, tvTimeComment, tvLikeComment;
        ImageView imgViewUser, imgViewComment;

        public MyViewHolder(View itemView){
            super(itemView);

            imgViewUser = (ImageView)itemView.findViewById( R.id.imgViewUser );
            imgViewComment = (ImageView)itemView.findViewById( R.id.imgViewComment );

            tvNameComment = (TextView) itemView.findViewById( R.id.tvNameComment );
            tvComment = (TextView) itemView.findViewById( R.id.tvComment );
            tvTimeComment = (TextView) itemView.findViewById( R.id.tvTimeComment );
            tvLikeComment = (TextView) itemView.findViewById( R.id.tvLikeComment );

        }
    }
}
