package com.upu.classbrand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upu.classbrand.R;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.listener.OnItemClickListener;
import com.upu.classbrand.view.CircleImageView;

public class MyViewHolderForChild extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CircleImageView imageView;
    private TextView textView;
    private LinearLayout bg;
    private LinearLayout lineMessage;

    private LinearLayout test;
    private ImageView imgbg;
    private TextView txtMessage;
    private TextView txtHomework;

    private OnItemClickListener myItemClickListener;
    public MyViewHolderForChild(View itemView,OnItemClickListener listener){
        super(itemView);
        imgbg=itemView.findViewById(R.id.imgbg);
        imageView=itemView.findViewById(R.id.child_icon);
        textView=itemView.findViewById(R.id.txt_child_name);
        bg=itemView.findViewById(R.id.itemBg);
        test=itemView.findViewById(R.id.linetest);
        lineMessage=itemView.findViewById(R.id.lineMessage);


        myItemClickListener=listener;
        imageView.setOnClickListener(this);
        //test.setOnClickListener(this);
        setMessageClick();

    }
    public void setMessageClick(){
        txtMessage=itemView.findViewById(R.id.txtbtnmessage);
        txtHomework=itemView.findViewById(R.id.txtbtnhomework);
        txtMessage.setOnClickListener(this);
        txtHomework.setOnClickListener(this);
    }
    public void setMessageVis(){
        bg.setVisibility(View.GONE);
        lineMessage.setVisibility(View.VISIBLE);
    }
    public void setMessageInvis(){
        bg.setVisibility(View.VISIBLE);
        lineMessage.setVisibility(View.GONE);
    }
    public CircleImageView getImageView(){
        return imageView;
    }
    public TextView getTextView(){
        return textView;
    }
    public LinearLayout getBg(){
        return bg;
    }
    public LinearLayout getLineMessage(){
        return lineMessage;
    }
    public ImageView getImgBg(){
        return imgbg;
    }
    public LinearLayout getTest(){
        return test;
    }
    @Override
    public void onClick(View v){
      switch (v.getId()){
//          case R.id.linetest:
//              if (myItemClickListener!=null){
//                  publicParam.itemindex=getPosition();
//                  myItemClickListener.onItemClick(v,getPosition());
//              }
          case R.id.txtbtnmessage:
              myItemClickListener.onMessageClick(getPosition());
              break;
          case R.id.txtbtnhomework:
              myItemClickListener.onHomeworkClick(getPosition());
              break;
          case R.id.child_icon:
              if (myItemClickListener!=null){
                  publicParam.itemindex=getPosition();
                  myItemClickListener.onItemClick(v,getPosition());
              }
              break;
      }

    }
}
