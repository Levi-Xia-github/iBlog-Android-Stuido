package com.bistu.sim.xwy.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bistu.sim.xwy.news.Other.SPSaveAcount;
import com.bistu.sim.xwy.news.domain.NewsUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

import java.util.Map;

public class MineActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout index_bottom_bar_home;
    private LinearLayout index_bottom_bar_dynamic_state;
    private LinearLayout index_bottom_bar_me;
    private LinearLayout index_bottom_bar_integral;
    private ImageView index_botton_bar_scan;
    private TextView tv_name;
    private SmartImageView iv_avatar;
    private RelativeLayout iv_tuichu;

    private RelativeLayout re_myinfo;

    private ImageView index_bottom_bar_home_image;
    private ImageView index_bottom_bar_dynamic_state_image;
    private ImageView index_bottom_bar_integral_image;
    private ImageView index_bottom_bar_me_image;

    private NewsUser newsUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
        fillData();
    }
    //初始化控件
    private void initView() {

        index_bottom_bar_home = findViewById(R.id.index_bottom_bar_home);
        index_bottom_bar_dynamic_state = findViewById(R.id.   index_bottom_bar_dynamic_state);
        index_bottom_bar_me=findViewById(R.id.  index_bottom_bar_me);
        index_bottom_bar_integral=findViewById(R.id.index_bottom_bar_integral);
        index_botton_bar_scan=findViewById(R.id.index_bottom_bar_scan);
        re_myinfo=findViewById(R.id.re_myinfo);
        tv_name=findViewById(R.id.tv_name);
        iv_avatar=findViewById(R.id.iv_avatar);
        iv_tuichu=findViewById(R.id.tuichu);

        index_bottom_bar_home_image = (ImageView) findViewById(R.id.index_bottom_bar_home_image);
        index_bottom_bar_dynamic_state_image = (ImageView) findViewById(R.id.index_bottom_bar_dynamic_state_image);
        index_bottom_bar_integral_image = (ImageView) findViewById(R.id.index_bottom_bar_integral_image);
        index_bottom_bar_me_image = (ImageView) findViewById(R.id.index_bottom_bar_me_image);

        index_bottom_bar_me_image.setImageResource(R.mipmap.index_bottom_bar_me_select);

        index_bottom_bar_home.setOnClickListener(this);
        index_bottom_bar_dynamic_state.setOnClickListener(this);
        index_bottom_bar_me.setOnClickListener(this);
        index_bottom_bar_integral.setOnClickListener(this);
        index_botton_bar_scan.setOnClickListener(this);
        re_myinfo.setOnClickListener(this);
        iv_tuichu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.index_bottom_bar_home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                MineActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_integral:
                Intent intent1 = new Intent(this,MessageActivity.class);
                startActivity(intent1);
                overridePendingTransition(0,0);
                MineActivity.this.finish();
                break;
            case  R.id.index_bottom_bar_dynamic_state:
                Intent intent2 = new Intent(this,FocusActivity.class);
                startActivity(intent2);
                overridePendingTransition(0,0);
                MineActivity.this.finish();
                break;
            case  R.id.index_bottom_bar_me:
                Intent intent3 = new Intent(this,MineActivity.class);
                startActivity(intent3);
                overridePendingTransition(0,0);
                MineActivity.this.finish();
                break;
            case R.id.index_bottom_bar_scan:
                Intent intent4 = new Intent(this,PublishActivity.class);
                startActivity(intent4);
                break;
            case R.id.re_myinfo:
                Intent intent5 = new Intent(this,ResetInfoActivity.class);
                intent5.putExtra("user_name",newsUser.getUser_name());
                intent5.putExtra("user_tel",newsUser.getUser_tel());
                intent5.putExtra("user_work",newsUser.getUser_work());
                intent5.putExtra("user_gender",newsUser.getUser_gender());
                intent5.putExtra("user_mail",newsUser.getUser_mail());
                intent5.putExtra("user_icon",newsUser.getUser_icon());
                startActivity(intent5);
                break;
            case R.id.tuichu:
                SharedPreferences sp = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Intent intent6 = new Intent(this,LoginActivity.class);
                startActivity(intent6);
                MineActivity.this.finish();
                break;
        }
    }
    public void fillData(){
        Map<String,String> userInfo = SPSaveAcount.getUserInfo(this);
        String user_tel = userInfo.get("username");

        String url = getString(R.string.serverurl)+"user/findByTel";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_tel",user_tel);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
              try {
                  String json = new String(bytes,"utf-8");
                  newsUser = JsonParse.getNewsUser(json);
                  tv_name.setText("用户名： "+newsUser.getUser_name());
                  iv_avatar.setImageUrl(newsUser.getUser_icon(),R.drawable.ic_launcher,R.drawable.ic_launcher);
              }catch (Exception e){
                  e.printStackTrace();
              }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(MineActivity.this,"错误,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
