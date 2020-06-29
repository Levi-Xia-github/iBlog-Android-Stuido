package com.bistu.sim.xwy.news;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bistu.sim.xwy.news.Adapter.ComAdapter;
import com.bistu.sim.xwy.news.Adapter.NewsAdapter;
import com.bistu.sim.xwy.news.Other.MyListView;
import com.bistu.sim.xwy.news.Other.SPSaveAcount;
import com.bistu.sim.xwy.news.domain.NewsCom;
import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

import java.util.List;
import java.util.Map;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView details_news_title;
    private TextView details_news_content;
    private TextView details_news_author;
    private TextView details_news_time;
    private TextView details_news_greate;
    private TextView details_news_read;
    private TextView details_news_comnum;
    private SmartImageView details_news_author_icon;
    private MyListView com_list;
    private EditText details_new_com_content;
    private ImageButton com_btn;
    private TextView delete_news;

    private int details_news_id;
    public static  final String EXTRA_DETAILS="DETAILS_NEWS_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("博文详情");
        initView();
        fillData();
        fillCom();
    }

    private void initView() {

        Intent intent = getIntent();
        details_news_id = getIntent().getExtras().getInt(EXTRA_DETAILS);

        details_news_title = (TextView) findViewById(R.id.details_news_title);
        details_news_content = (TextView) findViewById(R.id.details_news_content);
        details_news_author = (TextView) findViewById(R.id.details_news_author);
        details_news_time=(TextView)findViewById(R.id.details_news_time);
        details_news_author_icon=(SmartImageView)findViewById(R.id.details_news_author_icon);
        com_list=findViewById(R.id.com_list);
        delete_news=findViewById(R.id.delete_news);
        details_new_com_content=findViewById(R.id.details_new_com_content);
        com_btn=findViewById(R.id.com_btn);

        com_btn.setOnClickListener(this);
        delete_news.setOnClickListener(this);

    }

    private void fillData() {
       String url = getString(R.string.serverurl)+"news/DetailsNews?news_id="+details_news_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes,"utf-8");
                    NewsInfo details_news = JsonParse.getDetailsNews(json);
                    if(details_news==null){
                        Toast.makeText(DetailsActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    }else{
                          details_news_author_icon.setImageUrl(details_news.getNews_user().getUser_icon(),R.drawable.ic_launcher,R.drawable.ic_launcher);
                          details_news_title.setText(details_news.getNews_title());
                          details_news_content.setText(details_news.getNews_content());
                          details_news_author.setText(details_news.getNews_user().getUser_name());
                          details_news_time.setText("发表于 "+details_news.getNews_time());
                          //判断是否为当前登陆者的文章
                        Map<String,String> userInfo = SPSaveAcount.getUserInfo(DetailsActivity.this);
                          if(details_news.getNews_user().getUser_tel().equals(userInfo.get("username"))){
                              delete_news.setVisibility(View.VISIBLE);
                          }
                        }
                     } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(DetailsActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void fillCom() {
        String url = getString(R.string.serverurl)+"com/AllComs?news_id="+details_news_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes,"utf-8");
                    List<NewsCom> coms = JsonParse.getAllComs(json);
                    if(coms==null){
                        Toast.makeText(DetailsActivity.this,"暂无评论",Toast.LENGTH_SHORT).show();
                    }else{
                        com_list.setAdapter(new ComAdapter(coms,DetailsActivity.this));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(DetailsActivity.this,"评论请求失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void addCom(){
        String content = details_new_com_content.getText().toString().trim();
        Map<String,String> userInfo = SPSaveAcount.getUserInfo(this);
        String user_tel = userInfo.get("username");
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this,"不能评论空文字哦",Toast.LENGTH_SHORT).show();
            return;
        }

        String url = getString(R.string.serverurl)+"com/addCom";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("news_id",details_news_id);
        params.put("user_tel",user_tel);
        params.put("com_content",content);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(DetailsActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailsActivity.this,DetailsActivity.class);
                intent.putExtra(EXTRA_DETAILS,details_news_id);
                startActivity(intent);
                DetailsActivity.this.finish();
                overridePendingTransition(0,0);
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(DetailsActivity.this,"评论失败,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteNews(){
        String url = getString(R.string.serverurl)+"news/deleteNews?news_id="+details_news_id;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(DetailsActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
                DetailsActivity.this.finish();
                overridePendingTransition(0,0);
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(DetailsActivity.this,"删除失败,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.com_btn:
                addCom();
                break;
            case R.id.delete_news:
                deleteNews();
                break;
        }
    }
}