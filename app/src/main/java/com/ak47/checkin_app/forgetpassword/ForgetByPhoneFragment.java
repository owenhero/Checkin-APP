package com.ak47.checkin_app.forgetpassword;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ak47.checkin_app.R;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.ResetPasswordByCodeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetByPhoneFragment extends Fragment {

    private EditText etPhoneNum;
    private EditText etVerifyCode;
    private EditText etPwd;
    private Button btnGetCode;
    private Button btnResetPwd;
    private View view;

    public ForgetByPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //初始化界面
        view = inflater.inflate(R.layout.fragment_forget_by_phone, container, false);
        etPhoneNum = (EditText) view.findViewById(R.id.etPhoneNum);
        etVerifyCode = (EditText) view.findViewById(R.id.etVerifyCode);
        etPwd = (EditText) view.findViewById(R.id.etPwd);
        btnGetCode = (Button) view.findViewById(R.id.btnGetCode);
        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        btnResetPwd = (Button) view.findViewById(R.id.btnResetPwd);
        btnResetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPwd();
            }
        });

        return view;
    }

    private void getCode() {
        String phoneNum = etPhoneNum.getText().toString();
        BmobSMS.requestSMSCode(getActivity(), phoneNum, "移动签到验证码", new RequestSMSCodeListener() {

            @Override
            public void done(Integer smsId, BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//验证码发送成功
                    Log.i("smile", "短信id：" + smsId);//用于查询本次短信发送详情
                    toast("验证码已发送，请耐心等待...");
                }
            }
        });
    }
    private void resetPwd() {
        String code = etVerifyCode.getText().toString();
        String pwd = etPwd.getText().toString();
        BmobUser.resetPasswordBySMSCode(getActivity(), code, pwd, new ResetPasswordByCodeListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {
                    Log.i("smile", "密码重置成功");
                    toast("密码重置成功");
                    //让Activiy关闭自己。
                    getActivity().finish();

                } else {
                    Log.i("smile", "重置失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                    toast("重置密码失败:" + ex);
                }
            }
        });
    }
    //Toast显示
    private void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

}
