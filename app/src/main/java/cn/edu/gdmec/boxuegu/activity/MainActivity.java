package cn.edu.gdmec.boxuegu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.edu.gdmec.boxuegu.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("android studio test");
    }
}
