package com.bistu.sim.xwy.news;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bistu.sim.xwy.news.Adapter.NewsAdapter;
import com.bistu.sim.xwy.news.Other.SPSaveAcount;
import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView pub_news_cancel;
    private Button pub_news_btn;
    private TextView pub_news_title;
    private TextView pub_news_content;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("发表博文");
        initView();
    }
    public void initView(){

        pub_news_cancel=(ImageView)findViewById(R.id.pub_news_cancel);
        pub_news_btn=(Button)findViewById(R.id.pub_news_btn);
        pub_news_content=(TextView)findViewById(R.id.pub_news_content);
        pub_news_title=(TextView)findViewById(R.id.pub_news_title);

        pub_news_btn.setOnClickListener(this);
        pub_news_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pub_news_cancel:
                finish();
                break;
            case R.id.pub_news_btn:
                publish();
                break;
        }
    }
    //使用AsyncHttpClient访问网络
    private void publish() {
        String news_title = pub_news_title.getText().toString().trim();
        String news_content = pub_news_content.getText().toString().trim();
        if(TextUtils.isEmpty(news_title)){
            Toast.makeText(this,"题目不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(news_content)){
            Toast.makeText(this,"博文不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(news_title.length()>17){
            Toast.makeText(this,"标题最多输入16字哦",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> userInfo = SPSaveAcount.getUserInfo(this);
        String user_tel = userInfo.get("username");
        String url = getString(R.string.serverurl)+"news/addNews";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("news_title",news_title);
        params.put("news_content",news_content);
        params.put("user_tel",user_tel);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(PublishActivity.this,"发表成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PublishActivity.this,MainActivity.class);
                startActivity(intent);
                PublishActivity.this.finish();
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(PublishActivity.this,"发表失败,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
