package com.upu.classbrand.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.upu.classbrand.R;
import com.upu.classbrand.bean.TaskitemBean;
import com.upu.classbrand.listener.OnTaskItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class AdapterForTask extends RecyclerView.Adapter {
    private List<TaskitemBean> tasklist=new ArrayList<>();
    private Context context=null;

    private String TAG="AdapterForTask";
    private OnTaskItemClickListener onTaskItemClickListener;
    public void setTaskItemClickListener(OnTaskItemClickListener onTaskItemClickListener){
        this.onTaskItemClickListener=onTaskItemClickListener;
    }
    public AdapterForTask(List<TaskitemBean> tasklist,Context context){
        this.tasklist=tasklist;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        int lay=R.layout.item_cycler_zuoye;
        CardView layout=(CardView)LayoutInflater.from(context).inflate(lay,parent,false);
        return new MyViewHolderForTask(layout,onTaskItemClickListener);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position) {
        MyViewHolderForTask holderForTask = (MyViewHolderForTask) holder;
        //Glide.with(context).load(urls.get(position - 1)).into(holder.);
//        if (tasklist.get(position).getTasklist().size() > 1) {
//            holderForTask.getButton().setVisibility(View.VISIBLE);
//        } else {
//            holderForTask.getButton().setVisibility(View.GONE);
//        }
        holderForTask.getIndexView().setText(position+1+"");
        holderForTask.getContentView().setText(tasklist.get(position).getTasklist().get(0).getContent());
        if (tasklist.get(position).getTasklist().get(0).getImgpath1() != null) {
            Picasso.get().load(tasklist.get(position).getTasklist().get(0).getImgpath1()).into(holderForTask.getImage1View());
        } else {
            holderForTask.getImage1View().setVisibility(View.GONE);
        }
        if (tasklist.get(position).getTasklist().get(0).getImapath2() != null) {
            Picasso.get().load(tasklist.get(position).getTasklist().get(0).getImapath2()).into(holderForTask.getImage2View());
        } else {
            holderForTask.getImage2View().setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount(){
        if(tasklist!=null){
            return tasklist.size();
        }else{
            return 0;
        }
    }
}
