package com.example.sipcdouban;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;


import android.os.Bundle;

import android.view.MenuItem;

import com.example.fcy_Utils.BaseActivity;

import com.example.fcy_Utils.Search_Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends BaseActivity {
    private BottomNavigationView mBottomNavigationView;
    private Fragment[] fragments = new Fragment[]{new Search_Fragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.my_bottom_view);
        mBottomNavigationView.setSelectedItemId(R.id.search_movie);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, fragments[0]).commit();
        mBottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search_movie:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, fragments[0]).commit();
                        break;
                    case R.id.hot_show:
                        // 更改到热映fragment
                        break;
                    default:
                }
            }
        });


    }

    private static final String TAG = "MainActivity";

}