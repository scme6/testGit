package com.houyikj.myapplication.moments;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

abstract class BaseMomentViewHolder extends RecyclerView.ViewHolder {
    public BaseMomentViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void setData(int position, MomentBean.ResultBean resultBean) {
        logic(position,resultBean);
    }


    public abstract void logic(int position,MomentBean.ResultBean resultBean);


}
