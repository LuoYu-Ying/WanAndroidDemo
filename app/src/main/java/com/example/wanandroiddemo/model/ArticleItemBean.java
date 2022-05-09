package com.example.wanandroiddemo.model;

import android.widget.TextView;

public class ArticleItemBean {
    private String author,title, date, link;
    private boolean isFav;
    private int id;

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public ArticleItemBean(String author, String title, String date, String link, boolean isFav, int id) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.link = link;
        this.isFav = isFav;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    public boolean isFav() {
        return isFav;
    }
}
