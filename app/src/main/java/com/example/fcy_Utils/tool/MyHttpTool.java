package com.example.fcy_Utils.tool;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fcy_Utils.Detail_Activity;
import com.example.fcy_Utils.gson_class.HotShow_movie;
import com.example.fcy_Utils.gson_class.Movie_Top250;
import com.example.fcy_Utils.gson_class.Movie_detail;
import com.example.fcy_Utils.Search_movie_fragment;
import com.example.fcy_Utils.gson_class.Weekly;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.litepal.LitePal;

import java.io.EOFException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyHttpTool {
    private final String TAG = "MyHttpTool";
    private Handler handler;
    private Gson gson = new Gson();

    public MyHttpTool(Context context) {
        handler = new MyHandler(context);
    }

    public static final String MOVIE_WEEKLY = "https://api.douban.com/v2/movie/weekly?apikey=0b2bdeda43b5688921839c8ecb20399b";
    public static final String MOVIE_250 = "http://api.douban.com/v2/movie/top250?apikey=0df993c66c0c636e29ecbb5344252a4a";
    public static final String HOT_SHOW_MOVIE = "http://192.168.5.21:3000/movie/movie_hot";
    public static String MOVIE_DETAIL2 = "https://api.douban.com/v2/movie/subject/id?apikey=0df993c66c0c636e29ecbb5344252a4a";
    public static String MOVIE_LIKE = "http://192.168.5.21:3000/movie/movie_like/";
    // 请求热映电影列表
    public static final int TYPE_HOT = 0;
    // 请求相似的电影
    public static final int TYPE_LIKE = 1;
    public static int a = 0;

    public void getInfo(String id, final int type, final Object obj) {
        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(false).callTimeout(30000, TimeUnit.MILLISECONDS).connectTimeout(30000, TimeUnit.MILLISECONDS).readTimeout(30000, TimeUnit.MILLISECONDS).build();
        String real_url = null;
        switch (type) {
            case TYPE_HOT:
                real_url = HOT_SHOW_MOVIE;
                break;
            case TYPE_LIKE:
                real_url = MOVIE_LIKE + id;
                break;
            default:
        }
        Request request = new Request.Builder().url(real_url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: " + e.toString() + "\n" + call.toString());
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        JSONArray array = new JSONArray(response.body().string());
                        if (type == TYPE_HOT) {
                            LitePal.deleteAll(HotShow_movie.class);
                            final ArrayList<HotShow_movie> arrayList = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                HotShow_movie hotShow_movie = gson.fromJson(array.getString(i), HotShow_movie.class);
                                arrayList.add(hotShow_movie);
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Search_movie_fragment search_movie_fragment = (Search_movie_fragment) obj;
                                    search_movie_fragment.showTheHot(arrayList);
                                }
                            });
                        } else {
                            final List<HotShow_movie> hotShow_movies = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                HotShow_movie hotShow_movie = gson.fromJson(array.getString(i), HotShow_movie.class);
                                hotShow_movies.add(hotShow_movie);
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ((Detail_Activity) obj).showTheLikeMovie(hotShow_movies);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        handler.sendEmptyMessage(2);
                    }
                } else handler.sendEmptyMessage(1);
            }
        });
    }

    public static class MyHandler extends Handler {
        private WeakReference<Context> myContext;

        public MyHandler(Context context) {
            myContext = new WeakReference<Context>(context);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(myContext.get(), "获取数据失败", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(myContext.get(), "返回数据异常", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(myContext.get(), "数据解析异常", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    }

    public void getMovieDetail(String id, final Detail_Activity activity) {
        OkHttpClient client = new OkHttpClient();
        String url = MOVIE_DETAIL2;
        String real_url = url.replaceAll("id", id);
        Log.d(TAG, "getMovieDetail: " + real_url);
        Request request = new Request.Builder().url(real_url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String string = response.body().string();
                        Log.d(TAG, "onResponse: " + string);
                        final Movie_detail movie_detail = gson.fromJson(string, Movie_detail.class);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                activity.bindView(movie_detail);
                            }
                        });
                    } catch (EOFException e) {
                        handler.sendEmptyMessage(2);
                    }
                } else handler.sendEmptyMessage(1);
            }
        });
    }

    public void getTop250_movie(final Search_movie_fragment fragment) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30000, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder().url(MOVIE_250).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    final Movie_Top250 movie_top250 = gson.fromJson(responseBody, Movie_Top250.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            fragment.showThe250(movie_top250);
                        }
                    });
                } else handler.sendEmptyMessage(1);
            }
        });
        Request request1 = new Request.Builder().url(MOVIE_WEEKLY).build();
        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    final Weekly weekly = gson.fromJson(response.body().string(), Weekly.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            fragment.showTheWeekly(weekly);
                        }
                    });
                } else handler.sendEmptyMessage(1);
            }
        });
    }
}
