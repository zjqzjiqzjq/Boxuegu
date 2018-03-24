package cn.edu.gdmec.boxuegu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdmec.boxuegu.R;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private String userName;
    private EditText et_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        if (loginStatus()){
            SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
            String loginUserName = sp.getString("loginUserName","");
            tv1.setText(loginUserName+"登录成功");
            //userName = et_user_name.getText().toString().trim();

        }else {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            };
            timer.schedule(task,3000);
        }
    }
    private boolean loginStatus(){
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin",false);
        return isLogin;
    }
}
