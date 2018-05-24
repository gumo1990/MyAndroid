package com.example.administrator.myandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myandroid.R;

/**
 * Created by whq on 2018/5/24.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyFragmentHolder> {

    Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public MyFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyFragmentHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_second_item, parent,
                false));
    }

    @Override
    public void onBindViewHolder(MyFragmentHolder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"fragment"+position+"位置被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyFragmentHolder extends RecyclerView.ViewHolder {
        View view;

        public MyFragmentHolder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
