package com.bistu.sim.xwy.news.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bistu.sim.xwy.news.R;
import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private List<NewsInfo> newsInfos;
    private Context context;
    public NewsAdapter(List<NewsInfo> newsInfos,Context context){
        this.newsInfos=newsInfos;
        this.context=context;
    }
    @Override
    public int getCount() {
        return newsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return newsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.news_item,null);
        SmartImageView siv_pic = (SmartImageView)view.findViewById(R.id.com_user_icon);
        TextView tv_title=(TextView)view.findViewById(R.id.tv_title);
//        TextView tv_description = (TextView)view.findViewById(R.id.tv_description);
        TextView tv_author=(TextView)view.findViewById(R.id.tv_author);
        TextView tv_content=(TextView)view.findViewById(R.id.tv_content);
        TextView tv_time=(TextView)view.findViewById(R.id.tv_time);
        TextView tv_greate=(TextView)view.findViewById(R.id.tv_greate);
        TextView tv_com=(TextView)view.findViewById(R.id.tv_com);

        NewsInfo newsInfo = newsInfos.get(position);

        siv_pic.setImageUrl(newsInfo.getNews_user().getUser_icon(),R.drawable.ic_launcher,R.drawable.ic_launcher);
        tv_title.setText(newsInfo.getNews_title());
        tv_author.setText(newsInfo.getNews_user().getUser_name());
        tv_content.setText(newsInfo.getNews_content());
        tv_com.setText("评论 23");
        tv_greate.setText("点赞"+newsInfo.getNews_greate());
        tv_time.setText("发表于"+newsInfo.getNews_time());


        return view;
    }
}
