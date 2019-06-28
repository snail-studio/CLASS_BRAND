package com.upu.classbrand.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.upu.classbrand.R;
import com.upu.classbrand.bean.ChildlistBean;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.listener.OnItemClickListener;

import java.util.List;
public class AdampterForChild extends RecyclerView.Adapter{
    private String TAG="AdampterForChild";
    private Context context=null;
    private List<ChildlistBean> children;
    private OnItemClickListener onItemClickListener;
    private int itemwidth;
    private int itemheight;
    private int txtsise=12;
    private int touchindex=-1;
    public void setItemClickListener(OnItemClickListener itemClickListener){
        onItemClickListener=itemClickListener;
    }
    public AdampterForChild(List<ChildlistBean> children, Context context){
        this.context=context;
        this.children=children;
        int childsize=children.size();
        if (childsize>=0&&childsize<=4){
            itemwidth=240;
            itemheight=240;
            txtsise=40;
        }else if(childsize>=5&&childsize<=6){
            itemheight=240;
            itemwidth=180;
            txtsise=36;
        }else if(childsize>=7&&childsize<=9){
            itemheight=160;
            itemwidth=160;
            txtsise=23;
        }
        else if(childsize>=10&&childsize<=12){
            itemheight=155;
            itemwidth=130;
            txtsise=20;
        }
        else if(childsize>=13&&childsize<=16){
            itemheight=113;
            itemwidth=113;
            txtsise=18;
        }
        else if(childsize>=17&&childsize<=20){
            itemheight=115;
            itemwidth=100;
            txtsise=20;
        }
        else if(childsize>=21&&childsize<=25){
            itemheight=90;
            itemwidth=100;
            txtsise=15;
        }
        else if(childsize>=26&&childsize<=30){
            itemheight=90;
            itemwidth=90;
            txtsise=15;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View layout=LayoutInflater.from(context).inflate(R.layout.item_cycler_childinfo_30,parent,false);
        ViewGroup.LayoutParams layoutParams=layout.getLayoutParams();
        layoutParams.height=(int)dipToPx(context,itemheight);
        layoutParams.width=(int)dipToPx(context,itemwidth);
        layout.setLayoutParams(layoutParams);

        return new MyViewHolderForChild(layout,onItemClickListener);
    }
    //dp转px
    private float dipToPx(Context context, float dip) {
        return dip * getDeviceDensity(context) + 0.5f;
    }
    //获取屏幕密度
    private float getDeviceDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
        MyViewHolderForChild holderForChild=(MyViewHolderForChild)holder;
        //Glide.with(context).load(urls.get(position - 1)).into(holder.);
        Drawable drawable;
        switch (children.get(position).getType()){
            case "N":
                drawable=context.getResources().getDrawable(R.drawable.nomal);
                holderForChild.getImgBg().setBackgroundDrawable(drawable);
                break;
            case "J":
                drawable=context.getResources().getDrawable(R.drawable.qingjia);
                holderForChild.getImgBg().setBackgroundDrawable(drawable);
                break;
            case "Z":
                drawable=context.getResources().getDrawable(R.drawable.zhiri);
                holderForChild.getImgBg().setBackgroundDrawable(drawable);
                break;
            case "M":
                drawable=context.getResources().getDrawable(R.drawable.message);
                holderForChild.getImgBg().setBackgroundDrawable(drawable);
                break;
            case "H":
                drawable=context.getResources().getDrawable(R.drawable.homework);
                holderForChild.getImgBg().setBackgroundDrawable(drawable);
                break;
                default:
                    holderForChild.getImgBg().setBackgroundDrawable(null);
                    break;
        }
        if (publicParam.itemindex!=-1){
            if (position==publicParam.itemindex){
                holderForChild.getBg().setVisibility(View.GONE);
                holderForChild.getLineMessage().setVisibility(View.VISIBLE);
                //holderForChild.setMessageClick();
            }else{
                holderForChild.getBg().setVisibility(View.VISIBLE);
                holderForChild.getLineMessage().setVisibility(View.GONE);
            }
        }else{
            holderForChild.getBg().setVisibility(View.VISIBLE);
            holderForChild.getLineMessage().setVisibility(View.GONE);
        }
        //holderForChild.getImageView().setBorderColor(R.color.message);
        if (children.get(position).getIconpath()==null){
            Picasso.get().load(R.drawable.head).into(holderForChild.getImageView());
        }else{
            Picasso.get().load(children.get(position).getIconpath()).into(holderForChild.getImageView());
        }

        holderForChild.getTextView().setText(children.get(position).getChildname());
        holderForChild.getTextView().setTextSize(txtsise);

    }
    @Override
    public int getItemCount(){
        if (children!=null){
            return children.size();
        }else{
            return 0;
        }
    }
    public void updateChild(List<ChildlistBean> children){
        //this.children=children;
        //notifyDataSetChanged();
    }
}
