package com.example.fcy_Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fcy_Utils.gson_class.HotShow_movie;
import com.example.fcy_Utils.gson_class.Movie_Top250;
import com.example.fcy_Utils.gson_class.Weekly;
import com.example.fcy_Utils.tool.MyHttpTool;
import com.example.sipcdouban.R;

import org.litepal.LitePal;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search_movie_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search_movie_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MyHttpTool myHttpTool = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView_hot;
    private MyApplication myApplication;
    private RecyclerView recyclerView_seekMovies;

    public Search_movie_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment movie_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Search_movie_fragment newInstance(String param1, String param2) {
        Search_movie_fragment fragment = new Search_movie_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private static final String TAG = "Search_movie_fragment";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_movie_fragment, container, false);
        // 豆瓣热门
        View hot_show_frag = view.findViewById(R.id.movie_hot_show);
        recyclerView_hot = hot_show_frag.findViewById(R.id.hotshow_list);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_hot.setLayoutManager(manager);

        View movie_list = view.findViewById(R.id.douban_movie_list);
        TextView title1 = movie_list.findViewById(R.id.title);
        title1.setText(getResources().getString(R.string.movie_list));
        TextView total = movie_list.findViewById(R.id.douban_hotshow_total);
        RecyclerView movie_list_recycler = movie_list.findViewById(R.id.hotshow_list);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        movie_list_recycler.setLayoutManager(manager2);

        View short_video = view.findViewById(R.id.douban_video);
        TextView title2 = short_video.findViewById(R.id.title);
        title2.setText(R.string.douban_short_video);

        View movies = view.findViewById(R.id.search_movies);
        TextView title3 = movies.findViewById(R.id.title);
        TextView textView = movies.findViewById(R.id.douban_hotshow_total);
        recyclerView_seekMovies = movies.findViewById(R.id.hotshow_list);
        textView.setVisibility(View.GONE);
        title3.setText(getResources().getString(R.string.search_movie));
        mView = view;
        return view;
    }

    private View mView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myApplication = (MyApplication) getActivity().getApplication();
        myHttpTool = myApplication.getMyHttpTool();
        myHttpTool.getInfo(null, MyHttpTool.TYPE_HOT, this);
        myHttpTool.getTop250_movie(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        LitePal.deleteAll(HotShow_movie.class);
    }

    // 在数据加载进数据库以后 通过 MainActivity 调用
    public void showTheHot(final List<HotShow_movie> all) {
        Log.d(TAG, "showTheHot: " + all.size());
        final MyRecycleAdapter<HotShow_movie> adapter = new MyRecycleAdapter<HotShow_movie>(all) {

            @Override
            public void onClick(View v) {
                int childAdapterPosition = recyclerView_hot.getChildAdapterPosition(v);
                String id = all.get(childAdapterPosition).douBanId;
                toTheDetail(id);
            }


            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_hot_category;
            }

            @Override
            public void bindView(VH holder, HotShow_movie movie, int position) {
                holder.setText(R.id.movie_title, movie.movieName);
                holder.setImagine(R.id.movie_img, movie.imgUrl);
                holder.getView(R.id.movie_img).setTag(position);
            }
        };
        recyclerView_hot.setAdapter(adapter);
    }

    public void showTheSeekMovie(final Movie_Top250 movie_top250) {
        // 解决scrollView 跟recyclerView 嵌套导致的 滑动卡顿
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
//         方法一  取消recyclerView滑动
        recyclerView_seekMovies.setNestedScrollingEnabled(false);
        // 方法二 取消scrollView滑动

//            LinearLayoutManager manager1 = new LinearLayoutManager(getContext()){
//                @Override
//                public boolean canScrollVertically() {
//                    return false;
//                }
//            };

        recyclerView_seekMovies.setLayoutManager(manager);
        for (int i = 0; i < 3; i++) {
            movie_top250.subjects.remove(i);
        }
        recyclerView_seekMovies.setAdapter(new MyRecycleAdapter<Movie_Top250.Subject>(movie_top250.subjects) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_search_movie_tv;
            }

            @Override
            public void bindView(VH holder, Movie_Top250.Subject data, int position) {
                holder.setImagine(R.id.show_img, data.images.small_url);
                String name = data.title + "(" + data.year + ")";
                holder.setText(R.id.content_name, name);
                MaterialRatingBar ratingBar = holder.getView(R.id.starts);
                ratingBar.setProgress((int) Double.parseDouble(data.rating.average));
                holder.setText(R.id.score, data.rating.average);
                StringBuilder builder = new StringBuilder();
                for (String genre : data.genres) {
                    builder.append(genre);
                    builder.append("  ");
                }
                holder.setText(R.id.content_description, builder.toString());
            }

            @Override
            public void onClick(View v) {
                int childAdapterPosition = recyclerView_seekMovies.getChildAdapterPosition(v);
                String douBanId = movie_top250.subjects.get(childAdapterPosition).doubanId;
                toTheDetail(douBanId);
            }

        });
    }

    public void toTheDetail(String id) {
        Intent intent = new Intent(myApplication.getContext(), Detail_Activity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void showThe250(final Movie_Top250 movie_top250) {
        Log.d(TAG, "showThe250: " + movie_top250.subjects.size());
        final View view = mView.findViewById(R.id.rank_250);
        TextView textView = view.findViewById(R.id.rankList_name);
        textView.setText(myApplication.getResources().getString(R.string.ranklist250));
        View view1 = view.findViewById(R.id.show_item_1);
        View veiw2 = view.findViewById(R.id.show_item_2);
        View veiw3 = view.findViewById(R.id.show_item_3);
        ArrayList<View> arrayList = new ArrayList<>();
        arrayList.add(view1);
        arrayList.add(veiw2);
        arrayList.add(veiw3);
        for (int i = 0; i < 3; i++) {
            View viewC = arrayList.get(i);
            TextView textView1 = viewC.findViewById(R.id.ranking);
            textView1.setText(String.valueOf(i + 1));
            textView1 = viewC.findViewById(R.id.higher_ranking);
            textView1.setVisibility(View.GONE);
            ImageView imageView = viewC.findViewById(R.id.movie_img);
            Glide.with(mView).load(movie_top250.subjects.get(i).images.small_url).into(imageView);
            textView1 = viewC.findViewById(R.id.name);
            textView1.setText(movie_top250.subjects.get(i).title);
            textView1 = viewC.findViewById(R.id.score);
            textView1.setText(movie_top250.subjects.get(i).rating.average);
            MaterialRatingBar materialRatingBar = viewC.findViewById(R.id.stars);
            double stars = Double.parseDouble(movie_top250.subjects.get(i).rating.average);
            materialRatingBar.setProgress((int) stars);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = (InputStream) new URL(movie_top250.subjects.get(0).images.small_url).getContent();
                    final Drawable drawable = Drawable.createFromStream(inputStream,"src");
                    inputStream.close();
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (drawable != null)
                                view.setBackground(drawable);
                            view.getBackground().setAlpha(180);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        showTheSeekMovie(movie_top250);
    }

    public void showTheWeekly(final Weekly weekly) {
        Log.d(TAG, "showTheWeekly: " + weekly.subjects.size());
        final View view = mView.findViewById(R.id.rank_weekly);
        TextView textView = view.findViewById(R.id.rankList_name);
        textView.setText(myApplication.getResources().getString(R.string.ranklist250));
        View view1 = view.findViewById(R.id.show_item_1);
        final View veiw2 = view.findViewById(R.id.show_item_2);
        View veiw3 = view.findViewById(R.id.show_item_3);
        ArrayList<View> arrayList = new ArrayList<>();
        arrayList.add(view1);
        arrayList.add(veiw2);
        arrayList.add(veiw3);
        for (int i = 0; i < 3; i++) {
            View viewC = arrayList.get(i);
            TextView textView1 = viewC.findViewById(R.id.ranking);
            textView1.setText(String.valueOf(i + 1));
            textView1 = viewC.findViewById(R.id.higher_ranking);
            textView1.setVisibility(View.GONE);
            ImageView imageView = viewC.findViewById(R.id.movie_img);
            Glide.with(mView).load(weekly.subjects.get(i).subject.images.small_url).into(imageView);
            textView1 = viewC.findViewById(R.id.name);
            textView1.setText(weekly.subjects.get(i).subject.title);
            textView1 = viewC.findViewById(R.id.score);
            textView1.setText(weekly.subjects.get(i).subject.rating.average);
            MaterialRatingBar materialRatingBar = viewC.findViewById(R.id.stars);
            double stars = Double.parseDouble(weekly.subjects.get(i).subject.rating.average);
            materialRatingBar.setProgress((int) stars);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = (InputStream) new URL(weekly.subjects.get(0).subject.images.small_url).getContent();
                    final Drawable drawable = Drawable.createFromStream(inputStream,"src");
                    inputStream.close();
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (drawable != null)
                            view.setBackground(drawable);
                            view.getBackground().setAlpha(180);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}