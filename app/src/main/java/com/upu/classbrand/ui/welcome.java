package com.upu.classbrand.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.upu.classbrand.R;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.util.PreferencesUtil;

public class welcome extends BaseActivity {
    private LinearLayout rlSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        rlSplash=findViewById(R.id.welcome);
        startAnim();
        initParam();
    }
    private void initParam(){
        publicParam.schoolname=PreferencesUtil.getString("schoolname","");
        publicParam.schoolid=PreferencesUtil.getString("schoolid","0");
        publicParam.recordpsw= PreferencesUtil.getBoolean("recordpsw",false);
        publicParam.user=PreferencesUtil.getString("user","");
        publicParam.psw=PreferencesUtil.getString("psw","");
        publicParam.classid=PreferencesUtil.getString("classid","0");
        publicParam.classname=PreferencesUtil.getString("classname","");
    }
    private void startAnim(){
        //渐变动画，从完全透明到完全不透明
        AlphaAnimation alpha=new AlphaAnimation(0,1);
        //持续时间2秒
        alpha.setDuration(3000);
        //动画结束后，保持动画状态
        alpha.setFillAfter(false);
        //设置动画监听器
        alpha.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){

            }
            @Override
            public  void onAnimationRepeat(Animation animation){

            }
            //动画结束时调此方法
            @Override
            public void onAnimationEnd(Animation animation){
                //跳转到下一个页面
                //初始化参数
                //initParam();
                jumpNextPage();
            }
        });
        //启动动画
        rlSplash.startAnimation(alpha);
    }
    private void jumpNextPage(){
        Intent intent=new Intent(welcome.this,login.class);
        startActivity(intent);
        finish();
    }
}
