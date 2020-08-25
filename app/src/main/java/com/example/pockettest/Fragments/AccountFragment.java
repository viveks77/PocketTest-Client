package com.example.pockettest.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pockettest.DataBase.UserDataBaseHandler;
import com.example.pockettest.Model.User;
import com.example.pockettest.R;

public class AccountFragment extends Fragment {

    private Button editProfile;
    private TextView name;
    private TextView email;
    private TextView mobileNo;
    private UserDataBaseHandler db;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        name = view.findViewById(R.id.account_name);
        email = view.findViewById(R.id.account_email);
        mobileNo = view.findViewById(R.id.account_mobile);
        db = new UserDataBaseHandler(getActivity());

        User user = db.getUser();
        email.setText(user.getEmail());
        name.setText(user.getName());
        mobileNo.setText(user.getMobileNo());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
