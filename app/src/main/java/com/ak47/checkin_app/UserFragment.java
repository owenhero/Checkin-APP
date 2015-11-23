package com.ak47.checkin_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak47.checkin_app.R;

/**
 * Created by Dombi on 2015/11/2.
 * The Fragment must from v4 lib.
 */
public class UserFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
