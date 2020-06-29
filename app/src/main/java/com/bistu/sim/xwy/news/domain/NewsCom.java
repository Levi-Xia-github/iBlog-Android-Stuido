package com.bistu.sim.xwy.news.domain;

public class NewsCom {
    private long com_id;
    private String com_content;
    private String  com_time;
    private int com_type;
    private NewsUser com_user;

    public NewsCom(long com_id, String com_content, String com_time, int com_type, NewsUser com_user) {
        this.com_id = com_id;
        this.com_content = com_content;
        this.com_time = com_time;
        this.com_type = com_type;
        this.com_user = com_user;
    }

    public long getCom_id() {
        return com_id;
    }

    public void setCom_id(long com_id) {
        this.com_id = com_id;
    }

    public String getCom_content() {
        return com_content;
    }

    public void setCom_content(String com_content) {
        this.com_content = com_content;
    }

    public String getCom_time() {
        return com_time;
    }

    public void setCom_time(String com_time) {
        this.com_time = com_time;
    }

    public int getCom_type() {
        return com_type;
    }

    public void setCom_type(int com_type) {
        this.com_type = com_type;
    }

    public NewsUser getCom_user() {
        return com_user;
    }

    public void setCom_user(NewsUser com_user) {
        this.com_user = com_user;
    }
}
