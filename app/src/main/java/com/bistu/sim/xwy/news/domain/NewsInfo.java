package com.bistu.sim.xwy.news.domain;

public class NewsInfo {
    private long news_id;
    private String news_title;
    private String news_description;
    private String news_content;
    private String news_time;
    private long news_greate;
    private NewsUser news_user;


    public NewsInfo(long news_id, String news_title, String news_description, String news_content, String news_time, long news_greate, NewsUser news_user) {
        this.news_id = news_id;
        this.news_title = news_title;
        this.news_description = news_description;
        this.news_content = news_content;
        this.news_time = news_time;
        this.news_greate = news_greate;
        this.news_user = news_user;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_description() {
        return news_description;
    }

    public void setNews_description(String news_description) {
        this.news_description = news_description;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_time() {
        return news_time;
    }

    public void setNews_time(String news_time) {
        this.news_time = news_time;
    }

    public long getNews_greate() {
        return news_greate;
    }

    public void setNews_greate(long news_greate) {
        this.news_greate = news_greate;
    }

    public long getNews_id() {
        return news_id;
    }

    public void setNews_id(long news_id) {
        this.news_id = news_id;
    }

    public NewsUser getNews_user() {
        return news_user;
    }

    public void setNews_user(NewsUser news_user) {
        this.news_user = news_user;
    }
}
