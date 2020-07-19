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
 * Use the {@link Search_Tv_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search_Tv_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Search_Tv_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search_Tv_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Search_Tv_fragment newInstance(String param1, String param2) {
        Search_Tv_fragment fragment = new Search_Tv_fragment();
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
        //豆瓣榜单
        View view = inflater.inflate(R.layout.fragment_search__tv_fragment, container, false);
        View rankList = view.findViewById(R.id.douban_rankList_tv);
        TextView total_movie = rankList.findViewById(R.id.douban_hotshow_total);
        TextView title = rankList.findViewById(R.id.title);
        RecyclerView recyclerView1 = rankList.findViewById(R.id.hotshow_list);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(manager1);
        title.setText(getResources().getString(R.string.rank_list));
        // Inflate the layout for this fragment

        View category = view.findViewById(R.id.browser_by_category_tv);
        TextView tv_1_name = category.findViewById(R.id.type_1);
        TextView tv_2_name = category.findViewById(R.id.type_2);
        tv_1_name.setText(R.string.type_2_1);
        tv_2_name.setText(R.string.type_2_2);

        // 即将播出

        View on_the_air_view = view.findViewById(R.id.on_the_air);
        TextView onTheAir_title = on_the_air_view.findViewById(R.id.title);
        TextView onTheAir_total = on_the_air_view.findViewById(R.id.douban_hotshow_total);//根据返回数据设置
        onTheAir_title.setText(R.string.on_the_air);


        //找电视
        View movies = view.findViewById(R.id.search_tvs);
        TextView title3 = movies.findViewById(R.id.title);
        TextView textView = movies.findViewById(R.id.douban_hotshow_total);
        textView.setVisibility(View.GONE);
        title3.setText(getResources().getString(R.string.search_tv));
        return view;
    }

}