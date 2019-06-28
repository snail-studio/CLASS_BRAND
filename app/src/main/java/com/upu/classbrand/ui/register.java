package com.upu.classbrand.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.upu.classbrand.R;
import com.upu.classbrand.bean.CheckBean;
import com.upu.classbrand.bean.GetcheckinfoBean;
import com.upu.classbrand.bean.GetversionBean;
import com.upu.classbrand.bean.LoginBean;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.listener.DownloadImageListener;
import com.upu.classbrand.presenter.MyPresenter;
import com.upu.classbrand.util.DownloadUtil;
import com.upu.classbrand.util.ToastUtils;
import com.upu.classbrand.view.ModelView;

import java.io.File;

public class register extends BaseActivity implements View.OnClickListener, ModelView.MyView {
    private MyPresenter myPresenter;
    private Button btnCheckin;
    private Button btnUpdate;
    private Button btnSetting;
    private TextView txtInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myPresenter=new MyPresenter(this);
        initView();

        if (!publicParam.classid.equals("0")){
            jumpNextPage();
        }else{
            txtInfo.setVisibility(View.VISIBLE);
            txtInfo.setText("请设置班级信息");
        }
    }
    private void initView(){
        txtInfo=findViewById(R.id.txtInfo);
        txtInfo.setVisibility(View.GONE);

        btnCheckin=findViewById(R.id.btnCheckin);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnSetting=findViewById(R.id.btnSetting);

        btnCheckin.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
    }
    private void jumpNextPage(){
        Intent intent=new Intent(register.this,MainActivity.class);
        startActivity(intent);
        //finish();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnCheckin:
                if (publicParam.classid.equals("0")){
                    ToastUtils.toast(this,"请点击【参数设置】，选择使用班级");
                    return;
                }
                Intent intent1=new Intent(register.this,MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnUpdate:
                myPresenter.getVersionInfo();
                break;
            case R.id.btnSetting:
                Intent intent=new Intent(register.this,setting.class);
                startActivity(intent);
                finish();
                break;
                default:
                    break;
        }
    }
    @Override
    public void login(LoginBean loginBean){
        ;
    }
    @Override
    public void success(GetversionBean getversionBean){
        if (getversionBean.getCode()==1000){
            if(Float.parseFloat(getversionBean.getData().getVersionName())>Float.parseFloat(getVersionName())){
                showUpdaloadDialog(getversionBean.getData().getDownloadUrl());
            }else{
                showVersionDialog(getVersionName());
            }

        }
    }
    @Override
    public  void getcheckinfo(GetcheckinfoBean getcheckinfoBean){
        ;
    }
    @Override
    public void checkin(CheckBean checkBean){
        ;
    }
    @Override
    public void failed(int code){
        Toast.makeText(this,code+"",Toast.LENGTH_LONG).show();
    }
    private void showVersionDialog(final String versionname){
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                setMessage("当前版本"+versionname+"为最新版本！").// 设置要显示的信息
                setPositiveButton("确定", null);
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();
    }
    private void showUpdaloadDialog(final String downloadUrl){
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                setIcon(R.mipmap.ic_launcher). // 设置提示框的图标
                setMessage("发现新版本！请及时更新").// 设置要显示的信息
                setPositiveButton("确定", new DialogInterface.OnClickListener() {// 设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadapk(downloadUrl);//下载最新的版本程序
            }
        }).setNegativeButton("取消", null);//设置取消按钮,null是什么都不做，并关闭对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();
    }
    private void openAPK(String fileSavePath){
        File file=new File(fileSavePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // "com.ansen.checkupdate.fileprovider"即是在清单文件中配置的authorities
            // 通过FileProvider创建一个content类型的Uri
            data = FileProvider.getUriForFile(this, "com.upu.classbrand.fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }
    private void downloadapk(String url){
        if (url==""||url==null){
            return;
        }
        DownloadUtil mDownloadUtil=new DownloadUtil();
        mDownloadUtil.downloadApk(url, new DownloadImageListener() {
            @Override
            public void onStart(String filename) {
                Log.e("Mainactiviey","开始下载");
            }

            @Override
            public void onProgress(String filename, int currentLength) {

            }

            @Override
            public void onFinish(int faceinfoid, String oldpath, String newpath) {
                Log.e("Mainactiviey","下载完成");
                openAPK(Environment.getExternalStorageDirectory()+"/upudownload/"+"temp.apk");
            }

            @Override
            public void onFailure(File file, String erroInfo, int faceinfoid) {
                Log.e("Mainactiviey","下载错误");
            }
        });
    }

    private String getVersionName()
    {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String version = packInfo.versionName;
            return version;
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }
}
