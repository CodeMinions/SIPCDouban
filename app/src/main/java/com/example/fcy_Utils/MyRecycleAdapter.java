package com.example.fcy_Utils;


import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public abstract class MyRecycleAdapter<T> extends RecyclerView.Adapter<MyRecycleAdapter.VH> {
    private List<T> mData;

    public MyRecycleAdapter(List<T> data) {
        mData = data;
    }

    public abstract int getLayoutId(int viewType);
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VH.getInstance(parent,getLayoutId(viewType));
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindView(holder,mData.get(position),position);
    }

    public abstract void bindView(VH holder, T data, int position);

    public static class VH extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mItemView;

        private VH(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mViews = new SparseArray<>();
        }

        public static VH getInstance(ViewGroup viewGroup, int layoutId) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
            return new VH(inflate);
        }

        private <T extends View> T getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = mItemView.findViewById(id);
                mViews.put(id,view);
            }
            return (T) view;
        }

        public void setImagine(int id, String url) {
            ImageView imageView = getView(id);
            Glide.with(mItemView).load(url).into(imageView);
        }
        
        public void setText(int id, String content) {
            TextView textView = getView(id);
            textView.setText(content);
        }
    }

}
