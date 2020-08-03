package com.example.fcy_Utils;


import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fcy_Utils.LitePal_Class.HotShow_movie;
import com.example.fcy_Utils.LitePal_Class.Movie_detail;
import com.example.fcy_Utils.tool.MyHttpTool;
import com.example.sipcdouban.R;
import com.google.android.material.tabs.TabLayout;

import org.litepal.LitePal;

import java.util.List;


public class Detail_Activity extends BaseActivity {
    //单位 dp
    private final int ITEM_WIDTH = 80;
    private final int ITEM_HEIGHT = 120;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        // 开始下载数据
        id = getIntent().getStringExtra("id");
        MyApplication myApplication = (MyApplication) getApplication();
        myApplication.getMyHttpTool().getMovieDetail(id, this);
        myApplication.getMyHttpTool().getInfo(id, MyHttpTool.TYPE_LIKE,this);


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

    private static final String TAG = "Detail_Activity";
    // 在该方法中 从数据库 拿数据 显示出来
    public void bindView() {
        List<Movie_detail> movie_details = LitePal.findAll(Movie_detail.class);
        Movie_detail movie_detail = movie_details.get(0);
        ImageView imageView = findViewById(R.id.movie_img);
        Log.d(TAG, "bindView: " + movie_detail.getDoubanId());
        Glide.with(getApplicationContext()).load(movie_detail.getImgUrl()).into(imageView);
    }
    public void showTheLikeMovie(List<HotShow_movie> a){
        RecyclerView recyclerView = findViewById(R.id.resemble);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyRecycleAdapter(a) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_hot_category;
            }

            @Override
            public void bindView(VH holder, Object data, int position) {
                    HotShow_movie movie = (HotShow_movie) data;
                    holder.setImagine(R.id.movie_img,movie.getImgUrl());
                    holder.setText(R.id.movie_title,movie.getMovieName());
            }

            @NonNull
            @Override
            public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onClick(View v) {

            }

        });
    }
}