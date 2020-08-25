package com.example.pockettest.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pockettest.Activites.LoginActivity;
import com.example.pockettest.DataBase.SharedPrefManager;
import com.example.pockettest.DataBase.UserDataBaseHandler;
import com.example.pockettest.Model.User;
import com.example.pockettest.R;

public class AccountFragment extends Fragment implements View.OnClickListener{

    private Button editProfile;
    private Button starredQuizes;
    private Button givenQuizes;
    private Button logOut;
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
        editProfile = view.findViewById(R.id.account_editProfile);
        starredQuizes = view.findViewById(R.id.account_starredQuiz);
        givenQuizes = view.findViewById(R.id.account_quiz_taken);
        logOut = view.findViewById(R.id.account_logout);
        db = new UserDataBaseHandler(getActivity());
        User user = db.getUser();
        email.setText(user.getEmail());
        name.setText(user.getName());
        mobileNo.setText(user.getMobileNo());

        editProfile.setOnClickListener(this);
        starredQuizes.setOnClickListener(this);
        givenQuizes.setOnClickListener(this);
        logOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.account_editProfile:
                Log.d("pressed", "Button pressed");
                //Set Edit view activity
                break;
            case R.id.account_starredQuiz:
                //todo
                break;
            case R.id.account_quiz_taken:
                //todo
                break;
            case R.id.account_logout:
                logout();
                break;
        }
    }

    public  void logout(){
        db.deleteUser();
        SharedPrefManager.getInstance(getActivity()).deleteToken();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
