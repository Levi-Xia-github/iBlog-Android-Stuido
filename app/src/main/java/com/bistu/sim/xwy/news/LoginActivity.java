package com.bistu.sim.xwy.news;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bistu.sim.xwy.news.Other.SPSaveAcount;
import com.bistu.sim.xwy.news.domain.NewsUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;


public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button button_login;
    private EditText login_username;
    private EditText login_password;
    private TextView regist_link;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //判断是否已经有保存成功的用户名和密码
        Map<String,String> userInfo = SPSaveAcount.getUserInfo(this);
        if(userInfo.get("username").isEmpty() || userInfo.get("password").isEmpty()){
            initView();
        }else{
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username",userInfo.get("username"));
            startActivity(intent);
            LoginActivity.this.finish();
        }

    }
    private void initView(){
        button_login = (Button)findViewById(R.id.login_button);
        login_username=(EditText)findViewById(R.id.username_login_edit);
        login_password=(EditText)findViewById(R.id.password_login_edit);
        regist_link=(TextView)findViewById(R.id.register_link);
        button_login.setOnClickListener(this);
        regist_link.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String username = login_username.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        switch (v.getId()){
            case R.id.login_button:
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                //设置提示框
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("正在登陆");
                dialog.setMessage("请稍后");
                dialog.setCancelable(true);//设置可以通过back键取消
                dialog.show();

                String url=getString(R.string.serverurl)+"user/loginConfirm";
                RequestParams params = new RequestParams();
                params.put("username",username);
                params.put("password",password);
                final AsyncHttpClient client = new AsyncHttpClient();
                client.post(url,params,new AsyncHttpResponseHandler(){

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {
                            String username = login_username.getText().toString().trim();
                            String password = login_password.getText().toString().trim();
                            //判断是否成功
                            String json = new String(bytes,"utf-8");
                            NewsUser newsUser = JsonParse.getNewsUser(json);
                            if(newsUser==null){
                                Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                            }else{
                                //存储
                                boolean isSaveSuccess = SPSaveAcount.saveUserInfo(LoginActivity.this,username,password);
                                if(isSaveSuccess){
                                    Intent intent=new Intent();
                                    intent.setClass(LoginActivity.this,MainActivity.class);
                                    intent.putExtra("username",username);
                                    startActivity(intent);
                                    LoginActivity.this.finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"出现问题，请重新尝试",Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(LoginActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
                break;

            case R.id.register_link:
                Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
