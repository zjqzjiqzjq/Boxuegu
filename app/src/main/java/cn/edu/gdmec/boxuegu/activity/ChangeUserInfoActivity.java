package cn.edu.gdmec.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.boxuegu.R;

public class ChangeUserInfoActivity extends Activity implements View.OnClickListener {

    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_save;
    private RelativeLayout title_bar;
    private EditText et_content;
    private ImageView iv_delete;
    private String title,content;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_save = (TextView) findViewById(R.id.tv_save);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        et_content = (EditText) findViewById(R.id.et_content);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag",0);
        tv_main_title.setText(title);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);
        tv_save.setOnClickListener(this);

        tv_save.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(content)){
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
    }
    private void contentListener() {
        switch (flag){
            case 3:
                et_content.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                break;
        }
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_content.getText();
                int len = editable.length();
                if (len > 0){
                    iv_delete.setVisibility(View.VISIBLE);
                }else {
                    iv_delete.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1:
                        if (len > 8){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();

                            String newStr = str.substring(0,8);
                            et_content.setText(newStr);
                            editable = et_content.getText();

                            int newLen = editable.length();
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);

                        }
                        break;
                    case 2:
                        if (len > 16){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            String newStr = str.substring(0,16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    case 3:
                        if (len > 12){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            String newStr = str.substring(0,12);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    default:
                        break;

                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_delete:
                et_content.setText("");
                break;
            case R.id.tv_save:
                Intent data = new Intent();
                String etContent = et_content.getText().toString().trim();
                switch (flag){
                    case 1:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("nickName",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("signature",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("qq",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"QQ号不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
    }
}







