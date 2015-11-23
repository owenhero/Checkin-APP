package com.ak47.checkin_app.mediarecorder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.ak47.checkin_app.mediarecorder.MovieRecorderView.OnRecordFinishListener;
import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.ak47.checkin_app.R;

import cn.bmob.v3.datatype.BmobFile;

public class MediaActivity extends Activity {

    private MovieRecorderView mRecorderView;
    private Button mShootBtn;
    private boolean isFinish = true;
    private String upFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_main);
        mRecorderView = (MovieRecorderView) findViewById(R.id.movieRecorderView);
        mShootBtn = (Button) findViewById(R.id.shoot_button);
//        Bmob.initialize(this, "9a1fe4ae5a53d693be1f6d203879d5aa");


        mShootBtn.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mRecorderView.record(new OnRecordFinishListener() {

                        @Override
                        public void onRecordFinish() {
//                            upVedio(mRecorderView.getRecordFile().getPath());
                            handler.sendEmptyMessage(1);
                        }
                    });
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mRecorderView.getTimeCount() > 1) {

                        handler.sendEmptyMessage(1);
                    } else {
                        if (mRecorderView.getRecordFile() != null)
                            mRecorderView.getRecordFile().delete();
                        mRecorderView.stop();
                        Toast.makeText(MediaActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    }

    public void upVedio(String file){
        Log.i(MediaActivity.class.getSimpleName(), file);
        BTPFileResponse response = BmobProFile.getInstance(this).upload(file, new UploadListener() {

            @Override
            public void onSuccess(String fileName,String url,BmobFile file) {
                Log.i("bmob", "文件上传成功：" + fileName + ",可访问的文件地址：" + file.getUrl());
                // TODO Auto-generated method stub
                // fileName ：文件名（带后缀），这个文件名是唯一的，开发者需要记录下该文件名，方便后续下载或者进行缩略图的处理
                // url        ：文件地址
                // file        :BmobFile文件类型，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
            }

            @Override
            public void onProgress(int progress) {
                // TODO Auto-generated method stub
                Log.i("bmob", "onProgress :" + progress);
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                // TODO Auto-generated method stub
                Log.i("bmob", "文件上传失败：" + errormsg);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isFinish = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isFinish = false;
        mRecorderView.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            finishActivity();
        }
    };

    private void finishActivity() {
        if (isFinish) {
            mRecorderView.stop();
            upVedio(mRecorderView.getRecordFile().getPath());
        }
    }

    /**
     * 录制完成回调
     */
    public interface OnShootCompletionListener {
        public void OnShootSuccess(String path, int second);
        public void OnShootFailure();
    }
}