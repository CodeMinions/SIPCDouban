package com.example.fcy_Utils;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sipcdouban.R;
import com.example.sipcdouban.databinding.FragmentSearchBinding;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search_Fragment extends Fragment implements View.OnClickListener {
    private int current_type = -1;
    private Fragment[] fragments = new Fragment[]{new Search_movie_fragment(), new Search_Tv_fragment()};
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentSearchBinding binding;

    public Search_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Search_Fragment newInstance(String param1, String param2) {
        Search_Fragment fragment = new Search_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private static final String TAG = "Search_Fragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_, container, false);
        binding.setMContext(this);
        binding.setLifecycleOwner(getActivity());

        Log.d(TAG, "onCreateView: ");
        if (getFragmentManager() != null)
//            getFragmentManager().beginTransaction().replace(binding.contentShow.getId(), new Search_movie_fragment()).commit();
        {
            getFragmentManager().beginTransaction().add(R.id.content_show,fragments[1]).hide(fragments[1]).add(R.id.content_show,fragments[0]).commit();
        }
        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        changeLabel(0);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                break;
            case R.id.b2:
                break;
            case R.id.b3:
                break;
            case R.id.b4:
                break;
            default:
        }
    }

    private final int[][] ids_1 = new int[][]{{R.drawable.pic_search, R.drawable.rank_list_pic, R.drawable.dou_ban_cai_pic, R.drawable.movies_list_pic}
            , {R.drawable.pic_search, R.drawable.rank_list_pic, R.drawable.dou_ban_cai_pic, R.drawable.schedule_pic}};
    private final int[][] ids_2 = new int[][]{{R.string.search_movie, R.string.rank_list, R.string.dou_ban_cai, R.string.movie_list}
            , {R.string.search_tv, R.string.rank_list, R.string.dou_ban_cai, R.string.schedule}};

    public void changeLabel(int x) {
        if (x == current_type) return;
        else current_type = (current_type + 1) % 2;
        int[] ids = new int[]{R.id.b1, R.id.b2, R.id.b3, R.id.b4};
        if (getFragmentManager() != null)
//            getFragmentManager().beginTransaction().replace(binding.contentShow.getId(), fragments[current_type]).commit();
            getFragmentManager().beginTransaction().hide(fragments[(current_type+1)%2]).show(fragments[current_type]).commit();
        for (int i = 0; i < 4; i++) {
            if (getView() != null) {
                View view = getView().findViewById(ids[i]);
                ImageView imageView = view.findViewById(R.id.icon);
                TextView title = view.findViewById(R.id.title);
                imageView.setImageResource(ids_1[current_type][i]);
                title.setText(getResources().getString(ids_2[current_type][i]));
            }
        }
    }


}