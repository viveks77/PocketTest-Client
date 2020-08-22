package com.example.pockettest.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.pockettest.Fragments.AccountFragment;
import com.example.pockettest.Fragments.HomeFragment;
import com.example.pockettest.Fragments.LecturesFragment;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout main_frame;
    private HomeFragment homeFragment;
    private LecturesFragment lecturesFragment;
    private AccountFragment accountFragment;
    private BottomNavigationView bottomNavigationView;private ArrayList<Quiz> catlist;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.nav_home:
                    setFragment(new HomeFragment());
                    return true;

                case R.id.nav_lecture:
                    setFragment(new LecturesFragment());
                    return true;

                case R.id.nav_account:
                    setFragment(new AccountFragment());
                    return true;
            }

            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        main_frame = findViewById(R.id.main_frame);
        homeFragment=new HomeFragment();
        lecturesFragment=new LecturesFragment();
        accountFragment=new AccountFragment();
        bottomNavigationView=findViewById(R.id.bottom_nav_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        setFragment(homeFragment);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_home:
                setFragment(new HomeFragment());
                return true;

            case R.id.nav_lecture:
                 setFragment(lecturesFragment);
                return true;

            case R.id.nav_account:
                setFragment(accountFragment);
                return true;
        }

        return false;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(), fragment);

        transaction.commit();
    }
    }

