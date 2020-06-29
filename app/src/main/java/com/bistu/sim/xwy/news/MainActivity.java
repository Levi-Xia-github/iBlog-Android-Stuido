package com.bistu.sim.xwy.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bistu.sim.xwy.news.Adapter.NewsAdapter;
import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout loading;
    private ListView lv_news;
    private List<NewsInfo> newsInfos;
    private LinearLayout index_bottom_bar_home;
    private LinearLayout index_bottom_bar_dynamic_state;
    private LinearLayout index_bottom_bar_me;
    private LinearLayout index_bottom_bar_integral;
    private ImageView index_botton_bar_scan;

    private ImageView index_bottom_bar_home_image;
    private ImageView index_bottom_bar_dynamic_state_image;
    private ImageView index_bottom_bar_integral_image;
    private ImageView index_bottom_bar_me_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        fillData();
    }

    //初始化控件
    private void initView() {
        loading =findViewById(R.id.loading);
        lv_news = findViewById(R.id.lv_news);
        index_bottom_bar_home = findViewById(R.id.index_bottom_bar_home);
        index_bottom_bar_dynamic_state = findViewById(R.id.index_bottom_bar_dynamic_state);
        index_bottom_bar_me=findViewById(R.id.  index_bottom_bar_me);
        index_bottom_bar_integral=findViewById(R.id.index_bottom_bar_integral);
        index_botton_bar_scan=findViewById(R.id.index_bottom_bar_scan);

        index_bottom_bar_home_image = (ImageView) findViewById(R.id.index_bottom_bar_home_image);
        index_bottom_bar_dynamic_state_image = (ImageView) findViewById(R.id.index_bottom_bar_dynamic_state_image);
        index_bottom_bar_integral_image = (ImageView) findViewById(R.id.index_bottom_bar_integral_image);
        index_bottom_bar_me_image = (ImageView) findViewById(R.id.index_bottom_bar_me_image);

    }

    private void initEvent() {
        index_bottom_bar_home_image.setImageResource(R.mipmap.index_bottom_bar_home_select);

        index_bottom_bar_home.setOnClickListener(this);
        index_bottom_bar_dynamic_state.setOnClickListener(this);
        index_bottom_bar_integral.setOnClickListener(this);
        index_bottom_bar_me.setOnClickListener(this);
        index_botton_bar_scan.setOnClickListener(this);

    }
    //使用AsyncHttpClient访问网络
    private void fillData() {

        String url = getString(R.string.serverurl)+"news/AllNews";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes,"utf-8");
                    newsInfos = JsonParse.getNewsInfo(json);
                    if(newsInfos==null){
                        Toast.makeText(MainActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    }else{
                        loading.setVisibility(View.INVISIBLE);
                        lv_news.setAdapter(new NewsAdapter(newsInfos,MainActivity.this));
                        //设置listview的点击事件
                        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                NewsInfo newsInfo = (NewsInfo)parent.getItemAtPosition(position);
                                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                                intent.putExtra(DetailsActivity.EXTRA_DETAILS,(int)newsInfo.getNews_id());
                                startActivity(intent);
//                                Toast.makeText(MainActivity.this,newsInfo.getNews_title()+"ttt"+id,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.index_bottom_bar_home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_integral:
                Intent intent1 = new Intent(this,MessageActivity.class);
                startActivity(intent1);
                MainActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_dynamic_state:
                Intent intent2 = new Intent(this,FocusActivity.class);
                startActivity(intent2);
                MainActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_me:
                Intent intent3 = new Intent(this,MineActivity.class);
                startActivity(intent3);
                MainActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case R.id.index_bottom_bar_scan:
                Intent intent4 = new Intent(this,PublishActivity.class);
                startActivity(intent4);
                break;

        }
    }
}
