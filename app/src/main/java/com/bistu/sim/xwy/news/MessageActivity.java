package com.bistu.sim.xwy.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_message);
        initView();
    }
    //初始化控件
    private void initView() {

        index_bottom_bar_home = findViewById(R.id.index_bottom_bar_home);
        index_bottom_bar_dynamic_state = findViewById(R.id.   index_bottom_bar_dynamic_state);
        index_bottom_bar_me=findViewById(R.id.  index_bottom_bar_me);
        index_bottom_bar_integral=findViewById(R.id.index_bottom_bar_integral);
        index_botton_bar_scan=findViewById(R.id.index_bottom_bar_scan);

        index_bottom_bar_home_image = (ImageView) findViewById(R.id.index_bottom_bar_home_image);
        index_bottom_bar_dynamic_state_image = (ImageView) findViewById(R.id.index_bottom_bar_dynamic_state_image);
        index_bottom_bar_integral_image = (ImageView) findViewById(R.id.index_bottom_bar_integral_image);
        index_bottom_bar_me_image = (ImageView) findViewById(R.id.index_bottom_bar_me_image);

        index_bottom_bar_integral_image.setImageResource(R.mipmap.index_bottom_bar_integral_select);

        index_bottom_bar_home.setOnClickListener(this);
        index_bottom_bar_dynamic_state.setOnClickListener(this);
        index_bottom_bar_me.setOnClickListener(this);
        index_bottom_bar_integral.setOnClickListener(this);
        index_botton_bar_scan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.index_bottom_bar_home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
               MessageActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_integral:
                Intent intent1 = new Intent(this,MessageActivity.class);
                startActivity(intent1);
                MessageActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_dynamic_state:
                Intent intent2 = new Intent(this,FocusActivity.class);
                startActivity(intent2);
                MessageActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case  R.id.index_bottom_bar_me:
                Intent intent3 = new Intent(this,MineActivity.class);
                startActivity(intent3);
                MessageActivity.this.finish();
                overridePendingTransition(0,0);
                break;
            case R.id.index_bottom_bar_scan:
                Intent intent4 = new Intent(this,PublishActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
