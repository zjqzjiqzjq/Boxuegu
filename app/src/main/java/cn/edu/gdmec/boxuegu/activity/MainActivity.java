package cn.edu.gdmec.boxuegu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.gdmec.boxuegu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //利用Timer让此界面延迟3秒后再跳转，timer中有一个线程，这个线程不断执行task

        Timer timer = new Timer();
        //timertask实现runnable接口，TimerTask类表示一个在指定时间内执行的task

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        };
        timer.schedule(task,1000);   //设置这个task在延迟3秒之后自动执行

    }
}
