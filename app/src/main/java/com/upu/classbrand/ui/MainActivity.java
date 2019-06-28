package com.upu.classbrand.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.upu.classbrand.R;
import com.upu.classbrand.adapter.AdampterForChild;
import com.upu.classbrand.adapter.AdapterForTask;
import com.upu.classbrand.bean.BroadcastBean;
import com.upu.classbrand.bean.CheckBean;
import com.upu.classbrand.bean.CheckinfoBean;
import com.upu.classbrand.bean.ChildlistBean;
import com.upu.classbrand.bean.DutyBean;
import com.upu.classbrand.bean.GetbroadcastBean;
import com.upu.classbrand.bean.GetcheckinfoBean;
import com.upu.classbrand.bean.GetchildBean;
import com.upu.classbrand.bean.GethomeworkBean;
import com.upu.classbrand.bean.GethomeworkoutlineBean;
import com.upu.classbrand.bean.GetnoticeBean;
import com.upu.classbrand.bean.GetversionBean;
import com.upu.classbrand.bean.HomeworkoutlineBean;
import com.upu.classbrand.bean.LeaverBean;
import com.upu.classbrand.bean.LoginBean;
import com.upu.classbrand.bean.NoticeBean;
import com.upu.classbrand.bean.TaskBean;
import com.upu.classbrand.bean.TaskitemBean;
import com.upu.classbrand.common.publicParam;
import com.upu.classbrand.listener.GetbroadcastListener;
import com.upu.classbrand.listener.GetchildListener;
import com.upu.classbrand.listener.GetdutyListener;
import com.upu.classbrand.listener.GethomeworkListener;
import com.upu.classbrand.listener.GethomeworkoutlineListener;
import com.upu.classbrand.listener.GetleaverListener;
import com.upu.classbrand.listener.GetnoticeListener;
import com.upu.classbrand.listener.OnItemClickListener;
import com.upu.classbrand.listener.OnTaskItemClickListener;
import com.upu.classbrand.manager.MyApp;
import com.upu.classbrand.presenter.MyPresenter;
import com.upu.classbrand.tools.getTime;
import com.upu.classbrand.util.SoundsUtil;
import com.upu.classbrand.util.ToastUtils;
import com.upu.classbrand.view.CustomScrollBar;
import com.upu.classbrand.view.ModelView;
import com.upu.classbrand.view.MyImageDialog;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

import static com.upu.classbrand.common.MainHandlerConstant.AWAITOk;
import static com.upu.classbrand.common.MainHandlerConstant.INIT_SUCCESS;
import static com.upu.classbrand.common.MainHandlerConstant.OVER;
import static com.upu.classbrand.common.MainHandlerConstant.PRINT;
import static com.upu.classbrand.common.MainHandlerConstant.START;
import static com.upu.classbrand.common.MainHandlerConstant.STATUS;

public class MainActivity extends BaseActivity implements View.OnClickListener
        , ModelView.MyView , GetchildListener, GetdutyListener, GethomeworkListener, GethomeworkoutlineListener
        , GetbroadcastListener,GetleaverListener, GetnoticeListener, OnItemClickListener, OnTaskItemClickListener {
    public static final String TAG="MainActivity";
    private Context mContext;
    private TextView txtDate;
    private TextView txtTime;
    private TextView txtWeek;
    private TextView txtZuoye;
    private TextView txtUserinfo;
    private TextView txtNochild;
    private TextView txtmessagetitle; //消息标题
    private CustomScrollBar textViewNotice;

    private GifImageView gifload;
    private ImageView imgclose;
    private Button btnQiandao;
    private Button btnQiantui;
    private RecyclerView recyclerView_childinfo;
    private RecyclerView recyclerView_weidao;
    private RecyclerView recyclerView_qingjia;
    private RecyclerView recyclerView_zhiri;
    private RecyclerView recyclerView_mh;

    private LinearLayout linearLayout_data;
    private LinearLayout linearLayout_zone;
    private LinearLayout linearLayout_weidao;
    private LinearLayout linearLayout_qingjia;
    private LinearLayout linearLayout_zhiri;
    private LinearLayout linearLayout_zuoye;
    private LinearLayout linearLayout_childreninfo;
    private LinearLayout linearLayout_linecyc_message;//展示列表区域
    private LinearLayout linearLayout_linecyc_nomessage;//无数据显示区域
    private LinearLayout linearLayout_lineAllmessage;//数据显示框架
    private LinearLayout linearLayout_lineAllchild;//数据显示框架
    private LinearLayout linearLayout_noticeline;//通知显示框架

    private String speakId="2000";

    private SoundPool pool;
    private Map<String, Integer> poolMap=new HashMap<String, Integer>();

    private boolean flagclose=false;
    private boolean playRemind=true;

    public long LastBroadcastTime=0;

    private int screenW;
    private int screenH;
    private RelativeLayout linearLayout_nochild;
    private RelativeLayout linearLayout_nozuoye;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isLeave=true;
    private final SoundHandler myHandler=new SoundHandler(this);
    private final TimeTvHandler timetvHandler=new TimeTvHandler(this);

    private MyPresenter myPresenter;
    private Camera mCamera;
    private  static SurfaceView surfaceCamera;
    private SurfaceHolder mSurfaceHolder;
    private int viewWidth,viewHeight;
    private Camera.Parameters parameters;//相机参数

    private Camera.AutoFocusCallback myAutoFocusCallback;

    private AdampterForChild adapter_childinfo;
    private AdampterForChild adapter_weidao;
    private AdampterForChild adapter_qingjia;
    private AdampterForChild adapter_zhiri;
    private AdapterForTask adapter_mh;

    private String notice="";
    private List<String> urls=new ArrayList<>();
    private List<String> descriptions=new ArrayList<>();

    private List<ChildlistBean> child_weidao=null;
    private List<ChildlistBean> child_qingjia=null;
    private List<ChildlistBean> child_zhiri=null;
    private List<TaskitemBean> taskes=null;
    private List<NoticeBean> noticeList=new ArrayList<>();
    private List<HomeworkoutlineBean> homeworkoutlineList=new ArrayList<>();

    private List<BroadcastBean> broadcastList=new ArrayList<>();
    private BroadcastBean curSpeakBroadcast=null;
    private BroadcastBean curBroadcast=null;

    Handler SetIdleHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (publicParam.itemindex!=-1){
                publicParam.itemindex=-1;
                adapter_childinfo.notifyDataSetChanged();
            }
            return true;
        }
    });
    Handler GetNoticeHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            myPresenter.Getnotice();
            GetNoticeHandle.sendEmptyMessageDelayed(10000,1*60*1000);
            return true;
        }
    });
    Handler GetLeaverAndDutyHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //需补充添加请求条件，如果学生已全部到齐，不用再请求
            if(!Allcheckin()){
                myPresenter.Getleaverlist();
                GetLeaverAndDutyHandle.sendEmptyMessageDelayed(10001,1*60*1000);
            }
            return true;
        }
    });
    Handler GetHomeworkHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            myPresenter.Gethomeworkoutline();
            GetHomeworkHandle.sendEmptyMessageDelayed(10002,1*60*1000);
            return true;
        }
    });
    Handler GetCheckinfoHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //需补充添加请求条件，如果学生已全部到齐，不用再请求
            if (!Allcheckin()){
                myPresenter.Getcheckinfo();
                GetCheckinfoHandle.sendEmptyMessageDelayed(10003,1*60*1000);
            }
            return true;
        }
    });
    Handler GetBroadcastHandle = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //需补充添加请求条件，如果学生已全部到齐，不用再请求
            if (Allcheckin()){
                myPresenter.Getbroadcast();
                GetBroadcastHandle.sendEmptyMessageDelayed(10004,3*1000);
            }
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager w=getWindowManager();
        Display d=w.getDefaultDisplay();
        screenH=d.getHeight();
        screenW=d.getWidth();
        myPresenter=new MyPresenter(this);

        myPresenter.setGetchildListener(this);
        myPresenter.setGetdutyListener(this);
        myPresenter.setGethomeworkListener(this);
        myPresenter.setGethomeworkoutlineListener(this);
        myPresenter.setGetleaverListener(this);
        myPresenter.setGetnoticeListener(this);
        myPresenter.setGetbroadcastListener(this);


        //myPresenter.Gethomework("29","158","2019-06-09","");
        mContext= MyApp.getmContext();
        initView();
        initSound();
        speak("初始化语音",4000);
        new TimeTvThread().start();

        GetNoticeHandle.sendEmptyMessageDelayed(10000,70*1000);
        GetLeaverAndDutyHandle.sendEmptyMessageDelayed(10001,10*1000);
        GetHomeworkHandle.sendEmptyMessageDelayed(10002,50*1000);
        GetCheckinfoHandle.sendEmptyMessageDelayed(10003,30*1000);

        GetBroadcastHandle.sendEmptyMessageDelayed(10004,90*1000);



    }
    private void initSound(){
        pool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);
        poolMap.put("remind", pool.load(this, R.raw.remind, 1));
        poolMap.put("message", pool.load(this, R.raw.message, 1));
        poolMap.put("homework", pool.load(this, R.raw.homework, 1));
        poolMap.put("notice", pool.load(this, R.raw.notice, 1));


        poolMap.put("notice", pool.load(this, R.raw.notice, 1));
    }
    private int index=0;
    private void addTask(){
        index++;
        if (taskes==null){
            taskes=new ArrayList<>();
        }
        List<TaskBean> tasks=new ArrayList<>();
        tasks.add(new TaskBean(index+":语文作业：完成汉子听写100个，看图作文一篇，预习课文《家》"
                ,"http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg"
                ,"http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg"));
        taskes.add(0,new TaskitemBean("李红亮",tasks));
    }
    private void addUrls(){
        urls.add("http://n.sinaimg.cn/sports/transform/20170216/1s3V-fyarzzv2801842.jpg");
        urls.add("http://www.zq1.com/Upload/20170415/235459msc9i5yiqracirfw.jpg");
        urls.add("http://k.sinaimg.cn/n/sports/transform/20160424/dfCS-fxrqhar9877773.JPG/w570fe9.jpg");
        urls.add("http://n.sinaimg.cn/sports/transform/20170423/L9Uj-fyeqcac1387497.jpg");
    }
    private void addChildWeidao(){
        child_weidao=new ArrayList<>();
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","张晓晓",""));
        child_weidao.add(new ChildlistBean("11",null,"李孟凡","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","崔姗姗","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","王慧","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","孔小雅","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","郑梦洁","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","张晓晓","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","李孟凡","J"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","崔姗姗","J"));
        child_weidao.add(new ChildlistBean("11",null,"王慧","N"));
        child_weidao.add(new ChildlistBean("11",null,"孔小雅","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","张晓晓","Z"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","李孟凡","H"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","崔姗姗","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","王慧","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","孔小雅","J"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","郑梦洁","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","张晓晓","M"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","李孟凡","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","崔姗姗","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","王慧","H"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","孔小雅","J"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","张晓晓","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","李孟凡","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","崔姗姗","Z"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","王慧","N"));
//        child_weidao.add(new ChildlistBean("http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","孔小雅","M"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","郑梦洁","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","张晓晓","N"));
        child_weidao.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","李孟凡","N"));

    }
    private void addChildQingjia(){
        child_qingjia=new ArrayList<>();
        child_qingjia.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","杨洋洋","Z"));
        child_qingjia.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","刘静","Z"));
    }
    private void addChildZhiri(){
        child_zhiri=new ArrayList<>();
        child_zhiri.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","王菲","Z"));
        child_zhiri.add(new ChildlistBean("11","http://cms-bucket.nosdn.127.net/18642565fe354752aa2cb95a2469759320161020091552.jpg","周燕飞","Z"));
    }
    private void addDescriptions() {
        descriptions.add("南部之星拜仁慕尼黑");
        descriptions.add("骄傲的大黄蜂多特蒙德");
        descriptions.add("红魔曼联");
        descriptions.add("蓝军切尔西");
    }
    private void showMessagelayout(String title,String childid,int type){
        linearLayout_lineAllchild.setVisibility(View.GONE);
        linearLayout_lineAllmessage.setVisibility(View.VISIBLE);
        gifload.setVisibility(View.VISIBLE);

        txtmessagetitle.setText(title);
        if (type==0){
            //留言请求
        }else {
            //作业请求
            myPresenter.Gethomework(getTime.getData(),childid);
        }

    }
    private void showMH(){
        if (!flagclose){
            if (taskes.size()>0){
                linearLayout_linecyc_nomessage.setVisibility(View.GONE);
                linearLayout_linecyc_message.setVisibility(View.VISIBLE);
            }else{
                linearLayout_linecyc_nomessage.setVisibility(View.VISIBLE);
                linearLayout_linecyc_message.setVisibility(View.GONE);
                gifload.setVisibility(View.GONE);
            }
        }
    }
    private void initView(){
        textViewNotice=findViewById(R.id.textViewNotice);

        imgclose=findViewById(R.id.imgclose);
        gifload=findViewById(R.id.gifload);
        imgclose.setOnClickListener(this);
        //Picasso.get().load(R.drawable.load2).into(gifload);
        txtmessagetitle=findViewById(R.id.txtmessagetitle);


        linearLayout_linecyc_message=findViewById(R.id.linecyc_message);
        linearLayout_linecyc_nomessage=findViewById(R.id.lineNodata);
        linearLayout_lineAllmessage=findViewById(R.id.lineAllmessage);
        linearLayout_lineAllchild=findViewById(R.id.lineAllchild);
        linearLayout_noticeline=findViewById(R.id.noticeline);

        linearLayout_noticeline.setVisibility(View.GONE);


        linearLayout_lineAllmessage.setVisibility(View.GONE);


        linearLayout_childreninfo=findViewById(R.id.lineChildinfo);
        linearLayout_childreninfo.post(new Runnable() {
            @Override
            public void run() {
                int width=linearLayout_childreninfo.getWidth();
                int height=linearLayout_childreninfo.getHeight();
            }
        });
        mSwipeRefreshLayout=findViewById(R.id.swipe);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addTask();
                        mSwipeRefreshLayout.setRefreshing(false);
                        adapter_childinfo.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "更新完成", Toast.LENGTH_SHORT).show();

                    }
                },3000);
            }
        });
        linearLayout_zuoye=findViewById(R.id.lineZuoye);
        linearLayout_data=findViewById(R.id.lineCycle);
        linearLayout_zone=findViewById(R.id.linezong);
        linearLayout_nochild=findViewById(R.id.lineNochild);
        linearLayout_nozuoye=findViewById(R.id.lineNozuoye);

        linearLayout_weidao=findViewById(R.id.lineWeidao);
        linearLayout_weidao.setOnClickListener(this);
        linearLayout_qingjia=findViewById(R.id.lineQingjia);
        linearLayout_qingjia.setOnClickListener(this);
        linearLayout_zhiri=findViewById(R.id.lineZhiri);
        linearLayout_zhiri.setOnClickListener(this);


        addUrls();
        addDescriptions();
        addTask();
        addChildWeidao();
        //int childrensize=child_weidao.size();
        int childrensize=publicParam.children.size();
        int linesum=0;
        if (childrensize>=0&&childrensize<=4){
            linesum=2;
        }else if(childrensize>=5&&childrensize<=6){
            linesum=3;
        }else if(childrensize>=7&&childrensize<=9){
            linesum=3;
        }
        else if(childrensize>=10&&childrensize<=12){
            linesum=4;
        }
        else if(childrensize>=13&&childrensize<=16){
            linesum=4;
        }
        else if(childrensize>=17&&childrensize<=20){
            linesum=5;
        }
        else if(childrensize>=21&&childrensize<=25){
            linesum=5;
        }
        else if(childrensize>=26&&childrensize<=30){
            linesum=6;
        }
        recyclerView_childinfo=findViewById(R.id.cycler_child);
        recyclerView_childinfo.setLayoutManager(new GridLayoutManager(getApplicationContext(),linesum));

        adapter_childinfo=new AdampterForChild(publicParam.children,this);

        recyclerView_childinfo.setAdapter(adapter_childinfo);
        adapter_childinfo.setItemClickListener(this);

        recyclerView_weidao=findViewById(R.id.cycler_weidao);
        recyclerView_weidao.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));

        adapter_weidao=new AdampterForChild(child_weidao,this);
        recyclerView_weidao.setAdapter(adapter_weidao);

        recyclerView_qingjia=findViewById(R.id.cycler_qingjia);
        recyclerView_qingjia.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        addChildQingjia();
        adapter_qingjia=new AdampterForChild(child_qingjia,this);
        recyclerView_qingjia.setAdapter(adapter_qingjia);

        recyclerView_zhiri=findViewById(R.id.cycler_zhiri);
        recyclerView_zhiri.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        addChildZhiri();
        adapter_zhiri=new AdampterForChild(child_zhiri,this);
        recyclerView_zhiri.setAdapter(adapter_zhiri);

        recyclerView_mh=findViewById(R.id.cycler_mh);
        recyclerView_mh.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter_mh=new AdapterForTask(taskes,this);
        recyclerView_mh.setAdapter(adapter_mh);
        adapter_mh.setTaskItemClickListener(this);

        txtDate=findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);
        txtWeek=findViewById(R.id.txtWeek);

        txtZuoye=findViewById(R.id.txtZuoye);
        txtZuoye.setOnClickListener(this);
        txtNochild=findViewById(R.id.txtnochild);

        txtUserinfo=findViewById(R.id.txtUserinfo);
        txtUserinfo.setText(publicParam.schoolname+"  "+publicParam.classname);
        surfaceCamera=findViewById(R.id.surfaceCamera);
        mSurfaceHolder=surfaceCamera.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mSurfaceHolder.addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder){
                initCamera();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder){
                if (mCamera!=null){
                    mCamera.stopPreview();
                    mCamera.release();
                }
            }
        });

        btnQiandao=findViewById(R.id.btnQiandao);
        btnQiantui=findViewById(R.id.btnQiantui);

        btnQiandao.setOnClickListener(this);
        btnQiantui.setOnClickListener(this);
    }
    private void doAutoFocus() {
        parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(parameters);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    if (!Build.MODEL.equals("KORIDY H30")) {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续自动对焦
                        camera.setParameters(parameters);
                    } else {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        camera.setParameters(parameters);
                    }
                }
            }
        });
    }
    private void speak(String text,int index){
        SoundsUtil.speak(text,index+"",mContext,myHandler);
    }
    private static class SoundHandler extends Handler {
        private final WeakReference<MainActivity> mActivty;

        public SoundHandler(MainActivity mActivty) {
            this.mActivty = new WeakReference<MainActivity>(mActivty);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mActivty.get();
            if (activity != null) {
                switch (msg.what) {
                    case STATUS:
                        break;
                    case AWAITOk:
                        break;
                    case PRINT:
                        String res=msg.obj.toString().substring(0,2);
                        switch (res){
                            case "00":
                                Toast.makeText(activity,"开始合成",Toast.LENGTH_LONG).show();
                                break;
                            case "01":
                                Toast.makeText(activity,"结束合成",Toast.LENGTH_LONG).show();
                                break;
                            case "02":
                                Toast.makeText(activity,"开始播放",Toast.LENGTH_LONG).show();
                                break;
                            case "03":
                                Toast.makeText(activity,"开始播放1111",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        break;
                    case START:
                    case OVER:
                        Log.i(TAG,"开始或完成");
                        try{
                            if(!activity.speakId.equals("2000")){
                                if (!activity.speakId.equals(msg.obj.toString())){
                                    //activity.displayWaitList();
                                    return;
                                }
                            }
                            Thread.sleep(500);
                            activity.curSpeakBroadcast=activity.getBroadcastInfo();
                            if (activity.curSpeakBroadcast!=null){
                                activity.broadCast(activity.curSpeakBroadcast);
                                activity.LastBroadcastTime=System.currentTimeMillis();

                                activity.curSpeakBroadcast.broad();
                                activity.playRemind=false;
                                if (activity.curSpeakBroadcast.getTimes()==0){
                                    activity.broadcastList.remove(activity.curSpeakBroadcast);
                                }
                                //activity.displayWaitList();

                            }else{
                                activity.speakId="2000";
                            }

                        }catch (Exception e){
                            activity.speakId="2000";
                            e.printStackTrace();
                        }
                        break;
                    case INIT_SUCCESS:
                        Log.i(TAG,"初始化完成isGetSpeaking=false");
                        activity.speak("欢迎使用学屋宝智能班牌",3000);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    Random rand = new Random();
    public void broadCast(BroadcastBean broadcastInfo) {
        String index = rand.nextInt(100) + "";
        speakId = index;
        try {
            if (playRemind) {
                pool.play(poolMap.get("remind"), 1.0f, 1.0f, 0, 0, 1.0f);
                Thread.sleep(3000);
            }
            Log.e(TAG,"发送播报");
            SoundsUtil.speak(broadcastInfo.getContent(), index, this, myHandler);

        } catch (Exception e) {
            SoundsUtil.speak(broadcastInfo.getContent(), index, this, myHandler);
            e.printStackTrace();
        }
    }
    public BroadcastBean getBroadcastInfo(){
        BroadcastBean broadcastInfo=null;
        int sum=broadcastList.size();
        int indexSum=0;
        if (sum>0){
                if (sum>1){
                    if (sum>3){
                        indexSum=3;
                    }
                    else {
                        indexSum=sum;
                    }
                    broadcastInfo=broadcastList.get(0);
                    for (int i=0;i<indexSum;i++){
                        if ((i+1)>=indexSum)
                            break;
                        int res=broadcastList.get(i+1).getTimes()-broadcastList.get(i).getTimes();
                        switch (res){
                            case 0:
                                broadcastInfo = broadcastList.get(0);
                                break;
                            case 1:
                                broadcastInfo=broadcastList.get(i+1);
                                return broadcastInfo;
                            default:
                                broadcastInfo = broadcastList.get(0);
                                return broadcastInfo;
                        }
                    }
                }
                else {
                    broadcastInfo=broadcastList.get(0);
                }
        }
        return broadcastInfo;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.imgclose:
                linearLayout_lineAllmessage.setVisibility(View.GONE);
                linearLayout_lineAllchild.setVisibility(View.VISIBLE);
                flagclose=true;
                break;
            case R.id.btnQiandao:
                isLeave=false;
                capture();
                ToastUtils.toast(mContext,"正在签到");
                break;
            case R.id.btnQiantui:
                //isLeave=true;
                //capture();
                //ToastUtils.toast(mContext,"正在签退");

                break;
            case R.id.lineWeidao:
                linearLayout_data.setBackgroundColor(getResources().getColor(R.color.colorlightG));
                if (child_weidao==null||child_weidao.size()==0){
                    txtNochild.setText("所有学生都已到齐");
                    linearLayout_zone.setBackgroundColor(getResources().getColor(R.color.colorlightG));
                    linearLayout_nochild.setVisibility(View.VISIBLE);
                    linearLayout_data.setVisibility(View.GONE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }else{
                    linearLayout_nochild.setVisibility(View.GONE);
                    linearLayout_data.setVisibility(View.VISIBLE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }
                //recyclerView_zuoye.setVisibility(View.GONE);
                linearLayout_zuoye.setVisibility(View.GONE);
                recyclerView_weidao.setVisibility(View.VISIBLE);
                recyclerView_qingjia.setVisibility(View.GONE);
                recyclerView_zhiri.setVisibility(View.GONE);
                break;
            case R.id.lineQingjia:
                if (child_qingjia==null||child_qingjia.size()==0){
                    txtNochild.setText("今天没有请假学生");
                    linearLayout_zone.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                    linearLayout_nochild.setVisibility(View.VISIBLE);
                    linearLayout_data.setVisibility(View.GONE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }else{
                    adapter_qingjia.updateChild(child_qingjia);
                    linearLayout_nochild.setVisibility(View.GONE);
                    linearLayout_data.setVisibility(View.VISIBLE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }
                linearLayout_data.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                linearLayout_zuoye.setVisibility(View.GONE);
                //recyclerView_zuoye.setVisibility(View.GONE);
                recyclerView_weidao.setVisibility(View.GONE);
                recyclerView_qingjia.setVisibility(View.VISIBLE);
                recyclerView_zhiri.setVisibility(View.GONE);
                break;
            case R.id.lineZhiri:
                if (child_zhiri==null||child_zhiri.size()==0){
                    txtNochild.setText("今天没有值日学生");
                    linearLayout_zone.setBackgroundColor(getResources().getColor(R.color.colorlightZ));
                    linearLayout_nochild.setVisibility(View.VISIBLE);
                    linearLayout_data.setVisibility(View.GONE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }else{
                    linearLayout_nochild.setVisibility(View.GONE);
                    linearLayout_data.setVisibility(View.VISIBLE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }
                linearLayout_data.setBackgroundColor(getResources().getColor(R.color.colorlightZ));
                linearLayout_zuoye.setVisibility(View.GONE);
                //recyclerView_zuoye.setVisibility(View.GONE);
                recyclerView_weidao.setVisibility(View.GONE);
                recyclerView_qingjia.setVisibility(View.GONE);
                recyclerView_zhiri.setVisibility(View.VISIBLE);
                break;
            case R.id.txtZuoye:
                if (taskes==null||taskes.size()==0){
                    linearLayout_zone.setBackgroundColor(getResources().getColor(R.color.zuoye));
                    linearLayout_nochild.setVisibility(View.GONE);
                    linearLayout_data.setVisibility(View.GONE);
                    linearLayout_nozuoye.setVisibility(View.VISIBLE);
                }else{
                    linearLayout_nochild.setVisibility(View.GONE);
                    linearLayout_data.setVisibility(View.VISIBLE);
                    linearLayout_nozuoye.setVisibility(View.GONE);
                }
                linearLayout_data.setBackgroundColor(getResources().getColor(R.color.zuoye));
                linearLayout_zuoye.setVisibility(View.VISIBLE);
                //recyclerView_zuoye.setVisibility(View.VISIBLE);
                recyclerView_weidao.setVisibility(View.GONE);
                recyclerView_qingjia.setVisibility(View.GONE);
                recyclerView_zhiri.setVisibility(View.GONE);
                break;
                default:
                    break;
        }
    }
    @Override
    protected void onDestroy(){
        SoundsUtil.release();
        super.onDestroy();

    }
    @Override
    public void onTask1ItemClick(int position){
        MyImageDialog dialog=new MyImageDialog(this,R.style.DialogTheme,taskes.get(position).getTasklist().get(0).getImgpath1(),screenW,screenH);
        dialog.show();
    }
    @Override
    public void onTask2ItemClick(int position){
        MyImageDialog dialog=new MyImageDialog(this,R.style.DialogTheme,taskes.get(position).getTasklist().get(0).getImapath2(),screenW,screenH);
        dialog.show();
    }
    @Override
    public void onItemClick(View view, int position){
        adapter_childinfo.notifyDataSetChanged();
        SetIdleHandle.removeMessages(100);
        SetIdleHandle.sendEmptyMessageDelayed(100,5000);
    }
    @Override
    public void onMessageClick(int position){
        try {
            flagclose=false;
            showMessagelayout(publicParam.children.get(position).getChildname() + "家长留言",publicParam.children.get(position).getChildid(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onHomeworkClick(int position){
        flagclose=false;
        publicParam.children.get(position).setType("N");
        showMessagelayout(publicParam.children.get(position).getChildname()+"今日作业",publicParam.children.get(position).getChildid(),1);
    }
    private void initCamera(){
        mCamera= Camera.open(1);
        mCamera.setDisplayOrientation(0);
        if (mCamera!=null){
            try{
                parameters = mCamera.getParameters();
                parameters.setPreviewSize(viewWidth,viewHeight);
                parameters.setPreviewFpsRange(4,10);
                parameters.setPictureFormat(ImageFormat.JPEG);
                parameters.set("jpeg-quality",70);
                parameters.setPictureSize(viewWidth,viewHeight);
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.startPreview();


            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void capture(){
        if (mCamera==null)return;
        mCamera.takePicture(null,null,pictureCallback);
    }
    Camera.PictureCallback pictureCallback=new Camera.PictureCallback(){
        @Override
        public void onPictureTaken(byte[] data,Camera camera){
            final Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
            if (bitmap==null){
                Toast.makeText(MainActivity.this,"拍照失败",Toast.LENGTH_LONG).show();
            }
            //final Matrix matrix=new Matrix();
            //matrix.setRotate(0);
            //Bitmap bitmap=Bitmap.createBitmap(resouce,0,0,resouce.getWidth(),resouce.getHeight(),matrix,true);
            if (bitmap!=null){
                try{
                    //拍完照上传
                    String curImage=Base64util.BitmapToString(bitmap);
                    myPresenter.Checkin(bitmap,isLeave,publicParam.schoolname);
                    mCamera.startPreview();
                    //上传考勤数据

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        }
    };
    class TimeTvThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    timetvHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler

                    if (System.currentTimeMillis()-LastBroadcastTime>10000){
                        playRemind=true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }
    private static class TimeTvHandler  extends Handler {
        private final WeakReference<MainActivity> mActivty;

        public  TimeTvHandler(MainActivity mActivty){
            this.mActivty=new WeakReference<MainActivity>(mActivty);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity=mActivty.get();
            if (activity!=null){
                switch (msg.what) {
                    case 1:
                        long sysTime = System.currentTimeMillis();//获取系统时间
                        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss EEEE", sysTime);//时间显示格式
                        activity.txtDate.setText(activity.getDate(sysTimeStr.toString()));
                        activity.txtTime.setText(activity.getTime(sysTimeStr.toString()));
                        activity.txtWeek.setText(activity.getWeek(sysTimeStr.toString()));


                        break;
                    default:
                        break;
                }
            }
        }
    }
    @Override
    public void onGetnoticeResult(GetnoticeBean getnoticeBean){
        if(getnoticeBean.getCode()==1000){
            notice="";
            if (noticeList==null){
                noticeList=new ArrayList<>();
            }
            //有通知，
            for (int i=0;i<getnoticeBean.getData().size();i++){
                int index=isOldNotice(getnoticeBean.getData().get(i).getNoticeid());
                if (index==-1){
                    noticeList.add(getnoticeBean.getData().get(i));
                    if (noticeList.get(i).getNoticecontent().equals("详情请查看图片")){
                        notice+="                   "+"【"+noticeList.get(i).getNoticetitle()+"】"+":"+"请通过手机查看通知图片，了解详情!";
                    }else{
                        notice+="                   "+"【"+noticeList.get(i).getNoticetitle()+"】"+":"+noticeList.get(i).getNoticecontent();
                    }
                }
            }
        }else{
            //没有通知
            noticeList.clear();
        }
        updateNotice();
    }
    private void updateNotice(){
        try {
            if (notice.equals("")) {
                //textViewNotice.setText("没有新通知");
                textViewNotice.setText("");
                linearLayout_noticeline.setVisibility(View.GONE);
            } else {
                pool.play(poolMap.get("notice"), 1.0f, 1.0f, 0, 0, 1.0f);
                Thread.sleep(3000);
                textViewNotice.setText(notice);
                linearLayout_noticeline.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private int isOldNotice(int noticeid){
        if (noticeList.size()>0){
            for (int i=0;i<noticeList.size();i++){
                if (noticeList.get(i).getNoticeid()==noticeid){
                    return i;
                }
            }
            return -1;
        }else{
            return -1;
        }
    }
    @Override
    public void onGetleaverResult(LeaverBean leaverBean){
        if (leaverBean.getData()!=null){
            for (int i=0;i<leaverBean.getData().getLeavers().size();i++){
                addLeaverInfo(leaverBean.getData().getLeavers().get(i).getLeaverid());
            }
        }
        myPresenter.Getdutylist();
    }
    private void addLeaverInfo(String leaverid){
        for (int i=0;i<publicParam.children.size();i++){
            if (publicParam.children.get(i).getChildid().equals(leaverid)){
                if (!publicParam.children.get(i).getType().equals("N")){
                    publicParam.children.get(i).setType("J");
                }
                return;
            }

        }
    }
    @Override
    public void onHomeworkoutlineResult(GethomeworkoutlineBean gethomeworkoutlineBean){
        if (gethomeworkoutlineBean.getData()!=null){
            boolean haveNewHomework=false;
            if (homeworkoutlineList==null){
                homeworkoutlineList= new ArrayList<>();
            }
            for (int i=0;i<gethomeworkoutlineBean.getData().size();i++){
                int index=isOldHomework(gethomeworkoutlineBean.getData().get(i).getHomeworkid());
                if (index==-1){
                    homeworkoutlineList.add(gethomeworkoutlineBean.getData().get(i));
                    alterStudentStatus(gethomeworkoutlineBean.getData().get(i).getStudentid());
                    haveNewHomework=true;
                }
            }
            if (haveNewHomework){
                pool.play(poolMap.get("homework"), 1.0f, 1.0f, 0, 0, 1.0f);
            }
        }
        adapter_childinfo.notifyDataSetChanged();

    }
    private void alterStudentStatus(int studentid){
        for (int i=0;i<publicParam.children.size();i++){
            if (publicParam.children.get(i).getChildid().equals(studentid+"")
                    &&publicParam.children.get(i).getType().equals("N")){
                publicParam.children.get(i).setType("H");
                return;
            }
        }
    }
    private int isOldHomework(int homeworkid){
        if (homeworkoutlineList.size()>0){
            for (int i=0;i<homeworkoutlineList.size();i++){
                if (homeworkoutlineList.get(i).getHomeworkid()==homeworkid){
                    return i;
                }
            }
            return -1;
        }else{
            return -1;
        }
    }
    @Override
    public void onHomeworkResult(GethomeworkBean gethomeworkBean){
        taskes.clear();
        if (gethomeworkBean.getCode()==1000){

            if(gethomeworkBean.getData()!=null){
                if (taskes==null){
                    taskes=new ArrayList<>();
                }

                for (int i=0;i<gethomeworkBean.getData().size();i++){
                    List<TaskBean> tasks=new ArrayList<>();
                    tasks.add(new TaskBean(gethomeworkBean.getData().get(i).getContent()
                            ,gethomeworkBean.getData().get(i).getImage1()
                            ,gethomeworkBean.getData().get(i).getImage2()));
                    taskes.add(new TaskitemBean(gethomeworkBean.getData().get(i).getChildid(),tasks));
                }
            }
        }
        adapter_mh.notifyDataSetChanged();
        showMH();
    }
    @Override
    public void onGetdutyResult(DutyBean dutyBean){
        if (dutyBean.getData()!=null){
            for (int i=0;i<dutyBean.getData().size();i++){
                addDutyInfo(dutyBean.getData().get(i));
            }
        }
        adapter_childinfo.notifyDataSetChanged();
    }
    private void addDutyInfo(String childid){
        for (int i=0;i<publicParam.children.size();i++){
            if (publicParam.children.get(i).getChildid().equals(childid)){
                //如果学生已到，或者请假状态，就忽略值日
                if (!publicParam.children.get(i).getType().equals("N")&&!publicParam.children.get(i).getType().equals("J")){
                    publicParam.children.get(i).setType("Z");
                }
                return;
            }
        }
    }
    @Override
    public void onGetbroadcastResult(GetbroadcastBean getbroadcastBean){
        if (getbroadcastBean.getData()!=null){
            for (int i=0;i<getbroadcastBean.getData().size();i++){
                curBroadcast=new BroadcastBean(getbroadcastBean.getData().get(i).getTimes(),getbroadcastBean.getData().get(i).getClassid(),getbroadcastBean.getData().get(i).getContent()+"到了");
                broadcastList.add(curBroadcast);
            }
            if (speakId.equals("2000")) {
                Message MSG = Message.obtain();
                MSG.what = START;
                MSG.obj = "start";
                myHandler.sendMessage(MSG);
            }
        }
    }
    @Override
    public void onGetchildResult(GetchildBean getchildBean){
        ;
    }
    @Override
    public void onFailure(int code){
        switch (code){
            case 10007:
                if (!flagclose){
                    ToastUtils.toast(mContext,"请求失败");
                }
                break;
            case 10008:
                ToastUtils.toast(mContext,"通知请求失败");
                break;
            case 10010:
                myPresenter.Getdutylist();
                break;
            case 10011:
                adapter_childinfo.notifyDataSetChanged();
                break;
        }
    }
    @Override
    public void success(GetversionBean getversionBean){
        ;
    }

    @Override
    public void login(LoginBean loginBean){
        ;
    }
    @Override
    public void checkin(CheckBean checkBean){
        if (checkBean.getCode()==1000){
            ToastUtils.toast(mContext,checkBean.getData().getUsername());
        }else{
            ToastUtils.toast(mContext,checkBean.getMsg());
        }
    }
    @Override
    public  void getcheckinfo(GetcheckinfoBean getcheckinfoBean){
        if (getcheckinfoBean.getCode()==1000){
            if (getcheckinfoBean.getData().getCheckdetail()!=null){
                int preChildid=0;
                Iterator<CheckinfoBean> it = getcheckinfoBean.getData().getCheckdetail().iterator();
                while(it.hasNext()){
                    CheckinfoBean x = it.next();
                    //对每个考勤时间进行判断筛选
                    if(x.getChildid()==preChildid){
                        it.remove();
                    }else{
                        preChildid=x.getChildid();
                       setChildType(preChildid);
                    }
                }
            }
        }
        adapter_childinfo.notifyDataSetChanged();
    }
    private void setChildType(int childid){
        for (int i=0;i<publicParam.children.size();i++){
            if (publicParam.children.get(i).getChildid().equals(""+childid)){
                publicParam.children.get(i).setType("N");
                return;
            }
        }
    }
    @Override
    public void failed(int code){
        ;
    }
    private String getDate(String str){
        return str.substring(0,10);
    }
    private String getTime(String str){
        return str.substring(11,19);
    }
    private String getWeek(String str){
        return str.substring(20,23);
    }

    private boolean Allcheckin(){
        for (int i=0;i<publicParam.children.size();i++){
            if (publicParam.children.get(i).getType().equals("")){
                return false;
            }
        }
        return true;
    }
}
