package com.bistu.sim.xwy.news.domain;

public class NewsUser {

    private long user_id;
    private String user_icon;
    private String user_name;
    private String user_tel;
    private String user_password;
    private String user_sign;
    private String user_gender;
    private String user_work;
    private String user_mail;

    public NewsUser(long user_id, String user_icon,String user_name, String user_tel, String user_password, String user_sign,String user_gender,String user_work,String user_mail) {
        this.user_id = user_id;
        this.user_icon=user_icon;
        this.user_name = user_name;
        this.user_tel = user_tel;
        this.user_password = user_password;
        this.user_sign = user_sign;
        this.user_gender=user_gender;
        this.user_work=user_work;
        this.user_mail=user_mail;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_sign() {
        return user_sign;
    }

    public void setUser_sign(String user_sign) {
        this.user_sign = user_sign;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_work() {
        return user_work;
    }

    public void setUser_work(String user_work) {
        this.user_work = user_work;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
    }
}
