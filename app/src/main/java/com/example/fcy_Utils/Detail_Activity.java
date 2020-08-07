package com.example.fcy_Utils;


import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.fcy_Utils.gson_class.Cast;
import com.example.fcy_Utils.gson_class.Director;
import com.example.fcy_Utils.gson_class.HotShow_movie;
import com.example.fcy_Utils.gson_class.Movie_detail;
import com.example.fcy_Utils.gson_class.Photo;
import com.example.fcy_Utils.tool.MyHttpTool;
import com.example.sipcdouban.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;


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
        myApplication.getMyHttpTool().getInfo(id, MyHttpTool.TYPE_LIKE, this);


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
    public void bindView(Movie_detail movie_detail) {
        ImageView imageView = findViewById(R.id.movie_img);
        Log.d(TAG, "bindView: " + movie_detail.toString());
        TextView textView = findViewById(R.id.tv_Cname);
        textView.setText(movie_detail.title_ch);
        textView = findViewById(R.id.tv_details);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < movie_detail.categories.size(); i++) {
            stringBuilder.append(movie_detail.categories.get(i));
            stringBuilder.append("  ");
        }
        textView.setText(stringBuilder);
        ((TextView) findViewById(R.id.tv_Ename)).setText(movie_detail.original_title);
        // 电影图片设置
        Glide.with(getApplicationContext()).load(movie_detail.imgUrl.small_url).into(imageView);
        // 简介设置
        ((TextView) findViewById(R.id.short_introduction)).setText(movie_detail.briefIntroduction);

        // 分级评分设置
        double pro_1 = movie_detail.rating.details.one;
        double pro_2 = movie_detail.rating.details.two;
        double pro_3 = movie_detail.rating.details.three;
        double pro_4 = movie_detail.rating.details.four;
        double pro_5 = movie_detail.rating.details.five;
        double sum = pro_1 + pro_2 + pro_3 + pro_4 + pro_5;
        ProgressBar progressBar = findViewById(R.id.pro_1);
        progressBar.setProgress(1);
        progressBar.setProgress((int) (pro_1 * 100 / sum));
        progressBar = findViewById(R.id.pro_2);
        progressBar.setProgress((int) (pro_2 * 100 / sum));
        progressBar = findViewById(R.id.pro_3);
        progressBar.setProgress((int) (pro_3 * 100 / sum));
        progressBar = findViewById(R.id.pro_4);
        progressBar.setProgress((int) (pro_4 * 100 / sum));
        progressBar = findViewById(R.id.pro_5);
        progressBar.setProgress((int) (pro_5 * 100 / sum));
        Log.d(TAG, "bindView: " + (pro_1 / sum));
        // 综合评分设置
        MaterialRatingBar materialRatingBar = findViewById(R.id.mrb_averStore);
        materialRatingBar.setProgress((int) (Double.parseDouble(movie_detail.rating.average)));
        textView = findViewById(R.id.average_store);
        textView.setText(movie_detail.rating.average);

        // 所属频道设置
        RecyclerView recyclerView = findViewById(R.id.channel_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyRecycleAdapter(movie_detail.tags) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.category_channel_item;
            }

            @Override
            public void bindView(VH holder, Object data, int position) {
                holder.setBtnText(R.id.btn_channel_name, (String) data);
            }

            @Override
            public void onClick(View v) {

            }

        });

        // 设置演员表
        ArrayList<Bean> arrayList = new ArrayList<>();
        for (Director director : movie_detail.directors) {
            Bean bean = new Bean();
            bean.name = director.name;
            bean.url = director.avatars.img_url;
            arrayList.add(bean);
        }
        for (Cast cast : movie_detail.casts) {
            Bean bean = new Bean();
            bean.name = cast.name;
            bean.url = cast.avatars.img_url;
            arrayList.add(bean);
        }
        View view = findViewById(R.id.cast_of_performers);
        textView = view.findViewById(R.id.title);
        textView.setText("演职员");
        textView = view.findViewById(R.id.douban_hotshow_total);
        String s = "全部" + arrayList.size();
        textView.setText(s);
        recyclerView = view.findViewById(R.id.hotshow_list);
        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(new MyRecycleAdapter(arrayList) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_hot_category;
            }

            @Override
            public void bindView(VH holder, Object data, int position) {
                Bean bean = (Bean) data;
                holder.setImagine(R.id.movie_img, bean.url);
                holder.setText(R.id.movie_title, bean.name);
                View view = holder.getView(R.id.stars);
                view.setVisibility(View.GONE);
            }

            @Override
            public void onClick(View v) {

            }

        });

        // 设置剧照
        view = findViewById(R.id.stage_photo);
        textView = view.findViewById(R.id.title);
        textView.setText("剧照");
        textView = view.findViewById(R.id.douban_hotshow_total);
        textView.setText("全部" + movie_detail.photos.size());
        recyclerView = view.findViewById(R.id.hotshow_list);
        StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
//        LinearLayoutManager manager2 = new LinearLayoutManager(this);
//        manager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager2);
        recyclerView.setAdapter(new MyRecycleAdapter(movie_detail.photos) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.photo_big;
            }

            @Override
            public void bindView(VH holder, Object data, int position) {
                Photo photo = (Photo) data;
                holder.setImagine(R.id.photo, photo.imgUrl);
            }

            @Override
            public void onClick(View v) {

            }

            @Override
            public int getItemViewType(int position) {
                return position < 2 ? 0 : 1;
            }
        });

    }

    public void showTheLikeMovie(List<HotShow_movie> a) {
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
                holder.setImagine(R.id.movie_img, movie.imgUrl);
                holder.setText(R.id.movie_title, movie.movieName);
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

    private static class Bean {
        String url;
        String name;
    }
}