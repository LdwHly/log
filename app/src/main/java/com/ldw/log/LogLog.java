package com.ldw.log;


import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.ldw.log.service.ILogRemoteAidlInterface;


public class LogLog {
    private static final String TAG = LogLog.class.getSimpleName();
    public static final String REQUEST = "Request";
    private static ILogRemoteAidlInterface logRemote;

    public static void init(Application context) {
        if (logRemote == null) {
            ServiceConnection conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    logRemote = ILogRemoteAidlInterface.Stub.asInterface(iBinder);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    logRemote = null;
                }
            };
            Intent intent = new Intent();
            intent.setAction("com.dx.log.logapplication.service.MyRemoteService");
            intent.setPackage("com.dx.log.logapplication");
            context.bindService(intent, conn, Context.BIND_AUTO_CREATE);//开启Service
        }
    }

    public static void println(CharSequence tag, CharSequence url, CharSequence parms, CharSequence response) {
        try {
            logRemote.println(tag, url, parms, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String tag, CharSequence msg) {
        d(tag, "", msg);
    }

    public static void d(String tag, CharSequence childTag, CharSequence msg) {
        printlnTag(tag, childTag, msg);
    }

    private static void printlnTag(String tag, CharSequence childTag, CharSequence msg) {
        try {
            if (msg == null) {
                msg = "";
            }
            if (logRemote != null) {
                logRemote.printlnTag("Remote", tag, childTag, msg);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        if (TextUtils.isEmpty(tag)) {
            Log.d(TAG, childTag + ":" + msg.toString());
        } else {
            Log.d(tag, childTag + ":" + msg.toString());
        }
    }

    public static void printlnThread() {
        LogLog.d("printlnThread", "当前是否是主线程==" + (Thread.currentThread() == Looper.getMainLooper().getThread()));
    }
}
