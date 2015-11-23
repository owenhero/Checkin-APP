package com.ak47.checkin_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ak47.checkin_app.camera.CameraActivity;
import com.ak47.checkin_app.mediarecorder.MediaActivity;
import com.ak47.checkin_app.R;

/**
 * Created by owen on 2015/11/10.
 */
public class MainFragment extends Fragment{
    private View view;
    private Button bnTakePhoto;
    private Button bnTakeMedia;
    private Button bnGetLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        bnTakePhoto = (Button) view.findViewById(R.id.bnTakePhoto);
        bnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
            }
        });
        bnTakeMedia = (Button) view.findViewById(R.id.bnTakeMedia);
        bnTakeMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MediaActivity.class);
                startActivity(intent);
            }
        });

        bnGetLocation = (Button)view.findViewById(R.id.bn_get_locatoin);
        bnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MapMainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
