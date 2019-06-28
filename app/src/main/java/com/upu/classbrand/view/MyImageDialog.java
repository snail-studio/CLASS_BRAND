package com.upu.classbrand.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.upu.classbrand.R;
import com.upu.classbrand.util.NavigationBarUtil;

public class MyImageDialog extends Dialog implements View.OnClickListener{
    private Window window=null;
    private ImageView iv;
    private String url;
    private int width;
    private int height;

    public MyImageDialog(Context context,int theme,String url,int width,int height){
        super(context,theme);
        this.url=url;
        this.width=width;
        this.height=height;
    }
    protected void onCreate(Bundle savedInstanceState){
        //hideActionBar();
//        setFullScree();
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        setCanceledOnTouchOutside(true);
        View loadingview= LayoutInflater.from(getContext()).inflate(R.layout.imagedialogview,null);
        iv=loadingview.findViewById(R.id.imageview_head_big);
        Picasso.get().load(url).into(iv);
        iv.setOnClickListener(this);
        setContentView(loadingview);

        Window w=getWindow();

        w.setGravity(Gravity.LEFT);

        WindowManager.LayoutParams params=getWindow().getAttributes();
//        params.width= (int)(width*0.66);
//        params.height=WindowManager.LayoutParams.WRAP_CONTENT;;
        params.x=(int)(width*0.43);
        params.width= WindowManager.LayoutParams.WRAP_CONTENT;
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
        super.onCreate(savedInstanceState);
    }


    public void show(){
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
    }
    public  void dismiss(){
        super.dismiss();
    }
//    private void hideActionBar() {
//        //Hide UI
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
//    }
    /**
     * set the activity display in full scree
     */
    private  void setFullScree(){
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }
//    @Override
//    public void onStart(){
//        Window window=this.getWindow();
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        int uiOptions= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                |View.SYSTEM_UI_FLAG_IMMERSIVE
//                |View.SYSTEM_UI_FLAG_FULLSCREEN;
//        window.getDecorView().setSystemUiVisibility(uiOptions);
//    }
    @Override
    public void onClick(View v){
        dismiss();
    }
}
