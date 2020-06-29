package com.bistu.sim.xwy.news.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bistu.sim.xwy.news.R;
import com.bistu.sim.xwy.news.domain.NewsCom;
import com.bistu.sim.xwy.news.domain.NewsUser;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class ComAdapter extends BaseAdapter {
    private List<NewsCom> newComs;
    private Context context;

    public ComAdapter(List<NewsCom> newComs, Context context) {
        this.newComs = newComs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newComs.size();
    }

    @Override
    public Object getItem(int position) {
        return newComs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.com_item,null);
        SmartImageView com_user_icon = (SmartImageView)view.findViewById(R.id.com_user_icon);
        TextView com_user = (TextView) view.findViewById(R.id.com_user);
        TextView com_time =(TextView)view.findViewById(R.id.com_time);
        TextView com_content=(TextView)view.findViewById(R.id.com_content);

        NewsCom newsCom = newComs.get(position);

        com_user_icon.setImageUrl(newsCom.getCom_user().getUser_icon());
        com_user.setText(newsCom.getCom_user().getUser_name());
        com_time.setText(newsCom.getCom_time());
        com_content.setText(newsCom.getCom_content());

        return view;

    }
}
