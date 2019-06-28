package com.upu.classbrand.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.upu.classbrand.common.publicParam;

public class ToastUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void toast(final Context context, final String text) {
        if (publicParam.isdebug){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public static void toast(final Context context, final int resId) {
        if (publicParam.isdebug){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
