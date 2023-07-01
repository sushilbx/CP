package com.example.cp.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.cp.Modal.UserSessionManager;
import com.example.cp.R;

public class LeveFragment extends Fragment {


    String userId;
    UserSessionManager userSessionManager;
    RadioGroup radioGroupLanguageSelection;
    RadioButton radioButton1, radioButton2, radioButton3;
    String option, optionSelected;
    private SharedPreferences mSharedPreferences;
//    SweetAlertDialog pDialog;

    public LeveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_level, container, false);






        return view;

    }


}
