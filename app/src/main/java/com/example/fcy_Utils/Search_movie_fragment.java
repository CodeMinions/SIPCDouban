package com.example.fcy_Utils;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sipcdouban.R;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_fragment, container, false);
        // 豆瓣热门
        View hot_show_frag = view.findViewById(R.id.movie_hot_show);
        RecyclerView recyclerView = hot_show_frag.findViewById(R.id.hotshow_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        /*
        通过网络请求拿到数据 设置adapter的数据
         */
        // 豆瓣榜单
        View rankList = view.findViewById(R.id.douban_rankList);
        TextView total_movie = rankList.findViewById(R.id.douban_hotshow_total);
        TextView title = rankList.findViewById(R.id.title);
        RecyclerView recyclerView1 = rankList.findViewById(R.id.hotshow_list);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(manager1);
        title.setText(getResources().getString(R.string.rank_list));
        /*
        设置adapter 全设置为图片
         */
        View  movie_list = view.findViewById(R.id.douban_movie_list);
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
        textView.setVisibility(View.GONE);
        title3.setText(getResources().getString(R.string.search_movie));

        return view;
    }
}