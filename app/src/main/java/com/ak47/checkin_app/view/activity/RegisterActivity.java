package com.ak47.checkin_app.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ak47.checkin_app.R;
import com.ak47.checkin_app.RegisterEmail;
import com.ak47.checkin_app.RegisterPhone;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bnRegPhone;
    private Button bnRegEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        设置获取布局文件的ui元素
        bnRegPhone = (Button) findViewById(R.id.bn_reg_phoneNum);
        bnRegEmail = (Button) findViewById(R.id.bn_reg_email);
//        设置监听器
        bnRegPhone.setOnClickListener(this);
        bnRegEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //TODO:如果点击用手机注册，跳到RegisterPhone Activity
        if(v == bnRegPhone) {
            startActivity(new Intent(RegisterActivity.this,RegisterPhone.class));
        }
        //TODO:如果用邮箱注册，跳到RegisterEmail Activity
        if(v == bnRegEmail) {
            startActivity(new Intent(RegisterActivity.this,RegisterEmail.class));
        }


    }
}
