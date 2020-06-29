package com.bistu.sim.xwy.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bistu.sim.xwy.news.Adapter.NewsAdapter;
import com.bistu.sim.xwy.news.domain.NewsInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username_register_edit;
    private EditText password_register_edit;
    private EditText password_register_edit2;
    private Button register_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    public void initView(){
        password_register_edit = (EditText)findViewById(R.id.password_register_edit);
        password_register_edit2 = (EditText)findViewById(R.id.password_register_edit2);
        username_register_edit= (EditText)findViewById(R.id.username_register_edit);
        register_button = (Button)findViewById(R.id.register_button);
        register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                Register();
                break;
        }
    }

    public void Register(){
        String username = username_register_edit.getText().toString().trim();
        String password= password_register_edit.getText().toString().trim();
        String password2= password_register_edit2.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"请输入账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this,"请确认密码",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(password2)){
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }

        String url = getString(R.string.serverurl)+"user/addUser";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username",username);
        params.put("password",password);
        client.post(url, params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    Toast.makeText(RegisterActivity.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(RegisterActivity.this,"请求失败,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
