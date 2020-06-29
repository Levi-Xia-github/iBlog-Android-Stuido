package com.bistu.sim.xwy.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

public class ResetIconActivity extends AppCompatActivity implements View.OnClickListener {

    private String user_tel;
    private SmartImageView a;
    private SmartImageView b;
    private SmartImageView c;
    private SmartImageView d;
    private SmartImageView e;
    private SmartImageView f;
    private SmartImageView g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_icon);
        iniView();
    }
    public void iniView(){
       user_tel=getIntent().getExtras().getString("user_tel");
        a=findViewById(R.id.reset_icon_a);
        b=findViewById(R.id.reset_icon_b);
        c=findViewById(R.id.reset_icon_c);
        d=findViewById(R.id.reset_icon_d);
        e=findViewById(R.id.reset_icon_e);
        f=findViewById(R.id.reset_icon_f);
        g=findViewById(R.id.reset_icon_g);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        String url = getString(R.string.serverurl2);
        a.setImageUrl(url+"a.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);
        b.setImageUrl(url+"b.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);
        c.setImageUrl(url+"c.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);
        d.setImageUrl(url+"d.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);
        e.setImageUrl(url+"e.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);
        f.setImageUrl(url+"f.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);
        g.setImageUrl(url+"g.jpg",R.drawable.ic_launcher,R.drawable.ic_launcher);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset_icon_a:
                resetIcon(user_tel,"a.jpg");
                Intent intent = new Intent(this,MineActivity.class);
                startActivity(intent);

                ResetIconActivity.this.finish();
                break;
            case R.id.reset_icon_b:
                resetIcon(user_tel,"b.jpg");
                Intent intent2 = new Intent(this,MineActivity.class);
                startActivity(intent2);
                ResetIconActivity.this.finish();
                break;
            case R.id.reset_icon_c:
                resetIcon(user_tel,"c.jpg");
                Intent intent3 = new Intent(this,MineActivity.class);
                startActivity(intent3);
                ResetIconActivity.this.finish();
                break;
            case R.id.reset_icon_d:
                resetIcon(user_tel,"d.jpg");
                Intent intent4 = new Intent(this,MineActivity.class);
                startActivity(intent4);
                ResetIconActivity.this.finish();
                break;
            case R.id.reset_icon_e:
                resetIcon(user_tel,"e.jpg");
                Intent intent5 = new Intent(this,MineActivity.class);
                startActivity(intent5);
                ResetIconActivity.this.finish();
                break;
            case R.id.reset_icon_f:
                resetIcon(user_tel,"f.jpg");
                Intent intent6 = new Intent(this,MineActivity.class);
                startActivity(intent6);
                ResetIconActivity.this.finish();
                break;
            case R.id.reset_icon_g:
                resetIcon(user_tel,"g.jpg");
                Intent intent7 = new Intent(this,MineActivity.class);
                startActivity(intent7);
                ResetIconActivity.this.finish();
                break;
        }
    }
    public void resetIcon(String user_tel,String icon_url){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = getString(R.string.serverurl)+"user/resetIcon";
        RequestParams params = new RequestParams();
        params.put("user_tel",user_tel);
        params.put("icon_url",getString(R.string.serverurl2)+icon_url);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(ResetIconActivity.this,"错误,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
