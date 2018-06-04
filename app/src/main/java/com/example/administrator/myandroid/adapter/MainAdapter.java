package com.example.administrator.myandroid.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by whq on 2018/5/24.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    ArrayList<String> mList;
    Context mContext;
    OnitemClick onitemClick;

    public MainAdapter(Context context, ArrayList<String> mList) {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_main_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        holder.tv_main_item.setText(mList.get(position));

        holder.tv_main_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isChick = false;
                onitemClick.onItemClick(holder.tv_main_item, holder.getLayoutPosition());
                if( holder.iv_main_item.getVisibility() == View.GONE){
                    holder.iv_main_item.setVisibility(View.VISIBLE);
                }else if( holder.iv_main_item.getVisibility() == View.VISIBLE){
                    holder.iv_main_item.setVisibility(View.GONE);
                }

            }
        });
        holder.iv_main_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onitemClick.onItemLongClick(holder.tv_main_item, holder.getLayoutPosition());
                return false;
            }
        });
    }
    public void remove(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{

        TextView tv_main_item;
        ImageView iv_main_item;
        public MainViewHolder(View itemView) {
            super(itemView);
            tv_main_item = itemView.findViewById(R.id.tv_main_item);
            iv_main_item = itemView.findViewById(R.id.iv_main_item);

        }
    }

    public interface OnitemClick{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnitemClick(OnitemClick onitemClick){
        this.onitemClick = onitemClick;
    }
}
