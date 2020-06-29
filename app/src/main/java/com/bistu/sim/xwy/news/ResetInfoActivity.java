package com.bistu.sim.xwy.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.bistu.sim.xwy.news.domain.NewsUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.image.SmartImageView;

import org.apache.http.Header;

public class ResetInfoActivity extends AppCompatActivity implements View.OnClickListener {

   private TextView tv_name;
   private TextView tv_dianhua;
    private TextView tv_sex;
    private TextView tv_danwei;
    private TextView tv_youxiang;
   private Button tv_btn;
   private ImageView tv_cancel;
   private SmartImageView iv_avatar;
   private RelativeLayout re_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_info);
        initView();
    }
    public void initView(){
        String user_name=getIntent().getExtras().getString("user_name");
        String user_tel=getIntent().getExtras().getString("user_tel");
        String user_sex=getIntent().getExtras().getString("user_gender");
        String user_mail=getIntent().getExtras().getString("user_mail");
        String user_work=getIntent().getExtras().getString("user_work");
        String user_icon=getIntent().getExtras().getString("user_icon");
        tv_name=findViewById(R.id.tv_name);
        tv_dianhua=findViewById(R.id.tv_dianhua);
        tv_cancel=findViewById(R.id.tv_cancel);
        tv_btn=findViewById(R.id.tv_btn);
        tv_danwei=findViewById(R.id.tv_danwei);
        tv_youxiang=findViewById(R.id.tv_youxiang);
        tv_sex=findViewById(R.id.tv_sex);
        iv_avatar=findViewById(R.id.iv_avatar);
        re_avatar=findViewById(R.id.re_avatar);

        tv_dianhua.setText(user_tel);
        tv_name.setText(user_name);
        tv_sex.setText(user_sex);
        tv_danwei.setText(user_work);
        tv_youxiang.setText(user_mail);
        iv_avatar.setImageUrl(user_icon,R.drawable.ic_launcher,R.drawable.ic_launcher);

        tv_cancel.setOnClickListener(this);
        tv_btn.setOnClickListener(this);
        re_avatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_btn:
                ResetInfo();
                break;
            case R.id.re_avatar:
                Intent intent = new Intent(this,ResetIconActivity.class);
                String user_tel=getIntent().getExtras().getString("user_tel");
                intent.putExtra("user_tel",user_tel);
                startActivity(intent);
        }
    }
    public void ResetInfo(){
        String user_name = tv_name.getText().toString().trim();
        String user_tel = tv_dianhua.getText().toString().trim();
        String user_gender = tv_sex.getText().toString().trim();
        String user_mail=tv_youxiang.getText().toString().trim();
        String user_work=tv_danwei.getText().toString().trim();

        if(TextUtils.isEmpty(user_name)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(user_tel)){
            Toast.makeText(this,"电话不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        String url = getString(R.string.serverurl)+"user/resetInfo";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("user_name",user_name);
        params.put("user_tel",user_tel);
        params.put("user_gender",user_gender);
        params.put("user_mail",user_mail);
        params.put("user_work",user_work);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    Toast.makeText(ResetInfoActivity.this,"修改信息成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetInfoActivity.this,MineActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(ResetInfoActivity.this,"错误,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
