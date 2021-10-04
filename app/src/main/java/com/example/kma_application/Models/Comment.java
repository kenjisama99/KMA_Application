package com.example.kma_application.Models;

public class Comment {
    int id, tvLikeComment, imgViewUser, imgViewComment;
    String tvNameComment, tvComment, tvTimeComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTvLikeComment() {
        return tvLikeComment;
    }

    public void setTvLikeComment(int tvLikeComment) {
        this.tvLikeComment = tvLikeComment;
    }

    public int getImgViewUser() {
        return imgViewUser;
    }

    public void setImgViewUser(int imgViewUser) {
        this.imgViewUser = imgViewUser;
    }

    public int getImgViewComment() {
        return imgViewComment;
    }

    public void setImgViewComment(int imgViewComment) {
        this.imgViewComment = imgViewComment;
    }

    public String getTvNameComment() {
        return tvNameComment;
    }

    public void setTvNameComment(String tvNameComment) {
        this.tvNameComment = tvNameComment;
    }

    public String getTvComment() {
        return tvComment;
    }

    public void setTvComment(String tvComment) {
        this.tvComment = tvComment;
    }

    public String getTvTimeComment() {
        return tvTimeComment;
    }

    public void setTvTimeComment(String tvTimeComment) {
        this.tvTimeComment = tvTimeComment;
    }

    public Comment(int id, int tvLikeComment, int imgViewUser, int imgViewComment, String tvNameComment, String tvComment, String tvTimeComment) {
        this.id = id;
        this.tvLikeComment = tvLikeComment;
        this.imgViewUser = imgViewUser;
        this.imgViewComment = imgViewComment;
        this.tvNameComment = tvNameComment;
        this.tvComment = tvComment;
        this.tvTimeComment = tvTimeComment;
    }

    public Comment(int id, int tvLikeComment, int imgViewUser, String tvNameComment, String tvComment, String tvTimeComment) {
        this.id = id;
        this.tvLikeComment = tvLikeComment;
        this.imgViewUser = imgViewUser;
        this.tvNameComment = tvNameComment;
        this.tvComment = tvComment;
        this.tvTimeComment = tvTimeComment;
    }


}
