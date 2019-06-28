package com.upu.classbrand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.upu.classbrand.R;
import com.upu.classbrand.listener.OnTaskItemClickListener;

public class MyViewHolderForTask extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView image1;
    private ImageView image2;
    private TextView textContent;
    private TextView textIndex;
    private OnTaskItemClickListener onTaskItemClickListener;
    public MyViewHolderForTask(View itemView, OnTaskItemClickListener onTaskItemClickListener){
        super(itemView);
        image1=itemView.findViewById(R.id.iv_img1);
        image2=itemView.findViewById(R.id.iv_img2);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        textContent=itemView.findViewById(R.id.tv_content);
        textIndex=itemView.findViewById(R.id.txt_index);
        this.onTaskItemClickListener=onTaskItemClickListener;
    }
    public ImageView getImage1View(){
        return image1;
    }
    public ImageView getImage2View(){
        return image2;
    }
    public TextView getContentView(){
        return textContent;
    }
    public TextView getIndexView(){
        return textIndex;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_img1:
                onTaskItemClickListener.onTask1ItemClick(getPosition());
                break;
            case R.id.iv_img2:
                onTaskItemClickListener.onTask2ItemClick(getPosition());
                break;
        }
    }
}
