package com.ak47.checkin_app;


import android.content.Intent;
import com.ak47.checkin_app.forgetpassword.ForgetAcitvity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ak47.checkin_app.R;


public class ExploreFragment extends Fragment {

    private View view;
    private Button bntest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        bntest = (Button) view.findViewById(R.id.bn_test);
        bntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ForgetAcitvity.class));
            }
        });

        return view;
    }

}
