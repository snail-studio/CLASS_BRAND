package com.upu.classbrand.util;

import android.content.Context;
import android.os.Handler;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.upu.classbrand.control.InitConfig;
import com.upu.classbrand.control.NonBlockSyntherizer;
import com.upu.classbrand.listener.UiMessageListener;
import com.upu.classbrand.manager.MyApp;
import com.upu.classbrand.tools.OfflineResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundsUtil {
    protected static String appId = "16356022";

    protected static  String appKey = "xaHKGAZqm5Cu897qBZXGr2cq";

    protected static String secretKey = "xmZynrbGOssPtzxWmt3Lu4mkqO3gzSL9";
    protected static TtsMode ttsMode = TtsMode.MIX;
    private static NonBlockSyntherizer synthesizer;

    private static Context mContext;

    public static int speak(final String text, final String s, Context context, Handler handler)
    {
        mContext= MyApp.getmContext();
        if(synthesizer!=null)
        {
            return synthesizer.speak(text,s);
        }else
        {
            final int[] i = new int[1];
            synthesizer=initialTts(mContext,handler);
            return i[0];
        }
    }
    public static boolean init(Context context,Handler handler) {
        mContext = context;

        synthesizer = initialTts(mContext, handler);
        if (synthesizer != null) {
            return true;
        } else {
            return false;
        }
    }
    protected static NonBlockSyntherizer initialTts(Context context, Handler handler) {
//    MyHandler2 handler2=new MyHandler2(l,activity);
        //?LoggerProxy.printable(true); // 日志打印在logcat中
        // 设置初始化参数
        // 此处可以改为 含有您业务逻辑的SpeechSynthesizerListener的实现类
        SpeechSynthesizerListener listener = new UiMessageListener(handler);

        Map<String, String> params = getParams(context);
//        UiMessageListener uiMessageListener = new UiMessageListener(new MyHandler2(new OnSuccessedListener() {
//            @Override
//            public void onSuccessed() {
//
//            }
//        },context));
        // appId appKey secretKey 网站上您申请的应用获取。注意使用离线合成功能的话，需要应用中填写您app的包名。包名在build.gradle中获取。
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);

        // 如果您集成中出错，请将下面一段代码放在和demo中相同的位置，并复制InitConfig 和 AutoCheck到您的项目中
        // 上线时请删除AutoCheck的调用
//        AutoCheck.getInstance(context).check(initConfig, new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                if (msg.what == 100) {
//                    AutoCheck autoCheck = (AutoCheck) msg.obj;
//                    synchronized (autoCheck) {
//                        String message = autoCheck.obtainDebugMessage();
//                        //Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
//                         // 可以用下面一行替代，在logcat中查看代码
//                         Log.w("AutoCheckMessage", message);
//                    }
//                }
//            }
//
//        });
        return new NonBlockSyntherizer(context, initConfig,handler); // 此处可以改为MySyntherizer 了解调用过程
    }
    protected  static Map<String, String> getParams(Context context) {
        Map<String, String> params = new HashMap<String, String>();
        // 以下参数均为选填
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        params.put(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置合成的音量，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_VOLUME, "3");
        // 设置合成的语速，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_SPEED, "5");
        // 设置合成的语调，0-9 ，默认 5
        params.put(SpeechSynthesizer.PARAM_PITCH, "4");

        params.put(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_SYNTHESIZE);
        // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        // 离线资源文件
        OfflineResource offlineResource = createOfflineResource(OfflineResource.VOICE_FEMALE,context);
        // 声学模型文件路径 (离线引擎使用), 请确认下面两个文件存在
        params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, offlineResource.getTextFilename());
        params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
                offlineResource.getModelFilename());
        return params;
    }
    protected static OfflineResource createOfflineResource(String voiceType, Context context) {
        OfflineResource offlineResource = null;
        try {
            offlineResource = new OfflineResource(context, voiceType);
        } catch (IOException e) {
            // IO 错误自行处理
            e.printStackTrace();
        }
        return offlineResource;
    }
    public static void release()
    {
        synthesizer.release();
        synthesizer=null;
    }
}
