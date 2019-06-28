package com.upu.classbrand.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.upu.classbrand.R;
import com.upu.classbrand.bean.CheckBean;
import com.upu.classbrand.bean.ChildlistBean;
import com.upu.classbrand.bean.GetcheckinfoBean;
import com.upu.classbrand.bean.GetchildBean;
import com.upu.classbrand.bean.GetversionBean;
import com.upu.classbrand.bean.LoginBean;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.listener.GetchildListener;
import com.upu.classbrand.model.RuleTableE;
import com.upu.classbrand.model.SdefaultRuleE;
import com.upu.classbrand.model.TdefaultRuleE;
import com.upu.classbrand.presenter.MyPresenter;
import com.upu.classbrand.tools.getTime;
import com.upu.classbrand.util.PreferencesUtil;
import com.upu.classbrand.util.ToastUtils;
import com.upu.classbrand.view.Loading_view;
import com.upu.classbrand.view.ModelView;

public class login extends BaseActivity implements ModelView.MyView , GetchildListener {
    private static Context mContext;
    private MyPresenter myPresenter;
    private EditText txtUser;
    private EditText txtPsw;
    private CheckBox chkpsw;
    private Button btnLogin;
    private Loading_view loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext=getApplicationContext();
        loading=new Loading_view(login.this,R.style.CustomDialog);
        myPresenter=new MyPresenter(this);
        myPresenter.setGetchildListener(this);
        initView();
    }
    private void initView(){
        txtUser=findViewById(R.id.login_user);
        txtPsw=findViewById(R.id.login_psw);
        chkpsw=findViewById(R.id.chkpsw);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(onclickLogin);

        if (publicParam.recordpsw){
            txtUser.setText(publicParam.user);
            txtPsw.setText(publicParam.psw);
            chkpsw.setChecked(true);
        }
    }
    private View.OnClickListener onclickLogin=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (txtUser.getText().equals("")){
                Toast.makeText(login.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                return;
            }
            if (txtPsw.getText().equals("")){
                Toast.makeText(login.this,"密码不能为空",Toast.LENGTH_LONG).show();
                return;
            }
            loading.show();
            if (chkpsw.isChecked()){
                publicParam.recordpsw=true;
                publicParam.user=txtUser.getText().toString();
                publicParam.psw=txtPsw.getText().toString();
                PreferencesUtil.putBoolean("recordpsw",publicParam.recordpsw);
                PreferencesUtil.putString("user",publicParam.user);
                PreferencesUtil.putString("psw",publicParam.psw);
            }else{
                publicParam.recordpsw=false;
                publicParam.user="";
                publicParam.psw="";
                PreferencesUtil.putBoolean("recordpsw",publicParam.recordpsw);
                PreferencesUtil.putString("user",publicParam.user);
                PreferencesUtil.putString("psw",publicParam.psw);
            }
            myPresenter.Login(txtUser.getText().toString(),txtPsw.getText().toString());
        }
    };
    @Override
    public void onGetchildResult(GetchildBean getchildBean){
        if (getchildBean.getCode()==1000){
            if (getchildBean.getData()!=null){
                publicParam.children.clear();
                for (int i=0;i<getchildBean.getData().getChildren().size();i++){
                    publicParam.children.add(new ChildlistBean(
                            getchildBean.getData().getChildren().get(i).getChildid()
                            ,getchildBean.getData().getChildren().get(i).getHeadicon()
                            ,getchildBean.getData().getChildren().get(i).getChildname()
                            ,""));
                }
            }

            jumpNextPage();
        }else{
            //没有学生
            publicParam.children.clear();
            jumpNextPage();
        }
        ToastUtils.toast(mContext,"登录成功");
    }
    @Override
    public void onFailure(int code){
        ToastUtils.toast(mContext,"更新学生信息失败");
    }
    @Override
    public void success(GetversionBean getversionBean){
        ;
    }
    @Override
    public void login(LoginBean loginBean){
        if (loginBean.getCode()==1000){
            loading.dismiss();
            if (!publicParam.schoolid.equals(loginBean.getData().getSchoolid())){
                publicParam.classid="0";
            }
            publicParam.schoolid=loginBean.getData().getSchoolid();
            publicParam.schoolname=loginBean.getData().getSchoolname();

            PreferencesUtil.putString("schoolid",loginBean.getData().getSchoolid());
            PreferencesUtil.putString("schoolname",loginBean.getData().getSchoolname());
            if (loginBean.getData().getClassinfo()!=null){
                for (int i=0;i<loginBean.getData().getClassinfo().size();i++){
                    publicParam.classes.add(loginBean.getData().getClassinfo().get(i));
                }
            }

            if (loginBean.getData().getCheckrule()!=null){
                for (int i=0;i<loginBean.getData().getCheckrule().size();i++){
                    publicParam.ruleTableEs.add(new RuleTableE(loginBean.getData().getCheckrule().get(i).getRuleid()
                            , loginBean.getData().getCheckrule().get(i).getRulename()
                            , getTime.getStime(loginBean.getData().getCheckrule().get(i).getRule().getAmtimein())
                            , getTime.getTtime(loginBean.getData().getCheckrule().get(i).getRule().getAmtimein())
                            , getTime.getStime(loginBean.getData().getCheckrule().get(i).getRule().getAmtimeleave())
                            , getTime.getTtime(loginBean.getData().getCheckrule().get(i).getRule().getAmtimeleave())
                            , getTime.getStime(loginBean.getData().getCheckrule().get(i).getRule().getMidin())
                            , getTime.getTtime(loginBean.getData().getCheckrule().get(i).getRule().getMidin())
                            , getTime.getStime(loginBean.getData().getCheckrule().get(i).getRule().getMidleave())
                            , getTime.getTtime(loginBean.getData().getCheckrule().get(i).getRule().getMidleave())
                            , getTime.getStime(loginBean.getData().getCheckrule().get(i).getRule().getAfterin())
                            , getTime.getTtime(loginBean.getData().getCheckrule().get(i).getRule().getAfterin())
                            , getTime.getStime(loginBean.getData().getCheckrule().get(i).getRule().getAfterleave())
                            , getTime.getTtime(loginBean.getData().getCheckrule().get(i).getRule().getAfterleave())
                            ,loginBean.getData().getCheckrule().get(i).getExecutime()
                    ));
                }
            }
            if (loginBean.getData().getSdefaultrule()!=null){
                publicParam.sdefaultRuleE=new SdefaultRuleE(
                        getTime.getStime(loginBean.getData().getSdefaultrule().getAmtimein())
                        , getTime.getTtime(loginBean.getData().getSdefaultrule().getAmtimein())
                        , getTime.getStime(loginBean.getData().getSdefaultrule().getAmtimeleave())
                        , getTime.getTtime(loginBean.getData().getSdefaultrule().getAmtimeleave())
                        , getTime.getStime(loginBean.getData().getSdefaultrule().getMidin())
                        , getTime.getTtime(loginBean.getData().getSdefaultrule().getMidin())
                        , getTime.getStime(loginBean.getData().getSdefaultrule().getMidleave())
                        , getTime.getTtime(loginBean.getData().getSdefaultrule().getMidleave())
                        , getTime.getStime(loginBean.getData().getSdefaultrule().getAfterin())
                        , getTime.getTtime(loginBean.getData().getSdefaultrule().getAfterin())
                        , getTime.getStime(loginBean.getData().getSdefaultrule().getAfterleave())
                        , getTime.getTtime(loginBean.getData().getSdefaultrule().getAfterleave())
                        ,loginBean.getData().getSdefaultrule().getExecutime()
                );
            }
            if (loginBean.getData().getTdefaultrule()!=null){
                publicParam.tdefaultRuleE=new TdefaultRuleE(
                        getTime.getStime(loginBean.getData().getTdefaultrule().getAmtimein())
                        , getTime.getTtime(loginBean.getData().getTdefaultrule().getAmtimein())
                        , getTime.getStime(loginBean.getData().getTdefaultrule().getAmtimeleave())
                        , getTime.getTtime(loginBean.getData().getTdefaultrule().getAmtimeleave())
                        , getTime.getStime(loginBean.getData().getTdefaultrule().getMidin())
                        , getTime.getTtime(loginBean.getData().getTdefaultrule().getMidin())
                        , getTime.getStime(loginBean.getData().getTdefaultrule().getMidleave())
                        , getTime.getTtime(loginBean.getData().getTdefaultrule().getMidleave())
                        , getTime.getStime(loginBean.getData().getTdefaultrule().getAfterin())
                        , getTime.getTtime(loginBean.getData().getTdefaultrule().getAfterin())
                        , getTime.getStime(loginBean.getData().getTdefaultrule().getAfterleave())
                        , getTime.getTtime(loginBean.getData().getTdefaultrule().getAfterleave())
                        ,loginBean.getData().getTdefaultrule().getExecutime()
                );
            }
            myPresenter.Getstudentlist(publicParam.schoolid,publicParam.classid);
            //jumpNextPage();
        }else{
            loading.dismiss();
            ToastUtils.toast(mContext,loginBean.getMsg());
        }
    }
    @Override
    public  void getcheckinfo(GetcheckinfoBean getcheckinfoBean){
        ;
    }
    @Override
    public void failed(int code){
        loading.dismiss();
        if (code==10002){
            ToastUtils.toast(mContext,"登录失败,检查网络");
        }else{
            ToastUtils.toast(mContext,"其他错误");
        }

    }
   @Override
   public void checkin(CheckBean checkBean){
        ;
   }
    private void jumpNextPage(){
        Intent intent=new Intent(login.this,register.class);
        startActivity(intent);
        finish();
    }
}
