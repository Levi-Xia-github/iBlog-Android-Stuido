package com.bistu.sim.xwy.news;

import com.bistu.sim.xwy.news.domain.NewsCom;
import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.bistu.sim.xwy.news.domain.NewsUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    public static List<NewsInfo> getNewsInfo(String json){
        Gson gson=new Gson();
        Type listType=new TypeToken<List<NewsInfo>>(){}.getType();
        List<NewsInfo> newsInfos=gson.fromJson(json,listType);
        return newsInfos;
    }
    public static NewsInfo getDetailsNews(String json){
        Gson gson = new Gson();
        NewsInfo newsInfo = gson.fromJson(json,NewsInfo.class);
        return newsInfo;
    }
    public static NewsUser getNewsUser(String json){
        Gson gson=new Gson();
        NewsUser newsUser = gson.fromJson(json,NewsUser.class);
        return newsUser;
    }
    public static List<NewsCom>  getAllComs(String json){
        Gson gson=new Gson();
        Type listType=new TypeToken<List<NewsCom>>(){}.getType();
        List<NewsCom> newsCom = gson.fromJson(json,listType);
        return newsCom;
    }
}
