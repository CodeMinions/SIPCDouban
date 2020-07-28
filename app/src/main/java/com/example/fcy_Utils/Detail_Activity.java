package com.example.fcy_Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;


import com.example.sipcdouban.R;
import com.google.android.material.tabs.TabLayout;

public class Detail_Activity extends AppCompatActivity {
    //单位 dp
    private final int ITEM_WIDTH = 80;
    private final int ITEM_HEIGHT = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        TabLayout tabLayout = findViewById(R.id.my_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("电影"));
        tabLayout.addTab(tabLayout.newTab().setText("片单"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString();
                if (tabName.startsWith("电影")) {
                    // 更新展示表项中的adapter数据
                } else {
                    //
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        View view;

    }
    // 在设置adapter时调用

    private View setItemParams(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ITEM_WIDTH, getResources().getDisplayMetrics());
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ITEM_HEIGHT, getResources().getDisplayMetrics());
        view.setLayoutParams(layoutParams);
        return view;
    }
}