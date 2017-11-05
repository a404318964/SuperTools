package com.zwj.supertools;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.zwj.supertools.constant.NotifyConstant;
import com.zwj.supertools.constant.XSConstant;
import com.zwj.supertools.ui.activity.xs.XsContentActivity;
import com.zwj.zwjutils.FileUtils;
import com.zwj.zwjutils.LogUtils;
import com.zwj.zwjutils.ToastUtil;
import com.zwj.zwjutils.image.ImageBuilder;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.constant.Constant;
import com.zwj.zwjutils.net.constant.ResponseConstant;

import org.xutils.x;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static MyApplication gApp;

    // true - 需要再mainActivty的onResume更新
    public static boolean needUpdate;

    /**
     * true,已经弹出过检测更新的弹窗
     */
    public static boolean isCheckUpdate = false;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        //注册全局一场捕获
//        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler(getApplicationContext()));

        // 禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);
        // 日志加密
        MobclickAgent.enableEncrypt(true);
        MobclickAgent.setCatchUncaughtExceptions(true);

        ImageBuilder.globalDefaultImgId = R.drawable.ic_default_device_image;
        ImageBuilder.globalDefaultErrorImgId = R.drawable.ic_default_device_image;

        gApp = (MyApplication) getApplicationContext();
        // 初始化Xutils
        initNetConfig();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

        ToastUtil.useCustomeLayout = true;

//        LogUtils.logLevel = 0;

        //请勿在调用register方法时做进程判断处理（主进程和channel进程均需要调用register方法才能保证长连接的正确建立）。
        PushAgent mPushAgent = PushAgent.getInstance(this);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                //注册推送服务，每次调用register方法都会回调该接口
                mPushAgent.register(new IUmengRegisterCallback() {

                    @Override
                    public void onSuccess(String deviceToken) {
                        //注册成功会返回device token
                        LogUtils.i("App", "deviceToken --> " + deviceToken);
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        LogUtils.i("App", "s --> " + s);
                        LogUtils.i("App", "s1 --> " + s1);
                    }
                });
//            }
//        }).start();

        // 关闭推送打印的日志
//        mPushAgent.setDebugMode(false);


        // 处理推送自定义行为
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                LogUtils.i("notificationClickHandler", "msg.custom -----> "+msg.custom);
                LogUtils.i("notificationClickHandler", "msg.extra -----> "+ JSON.toJSONString(msg.extra));

                switch (msg.custom) {
                    case NotifyConstant.TYPE_OPEN_XS_CONTENT:
                        Intent intent = new Intent(MyApplication.getGlobalContext(), XsContentActivity.class);
                        intent.putExtra(XSConstant.CONTENT_ID, msg.extra.get(XSConstant.CONTENT_ID));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                }
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    public static MyApplication getGlobalContext() {
        return gApp;
    }

    /**
     * 设置全局需要传给后台的参数和header
     */
    public static void setGlobalParamAndHeader() {
        RequestBean.clearGlobalMap();
        // 添加token
        if (!TextUtils.isEmpty(getToken())) {
            RequestBean.addGlobalHead(Constant.TOKEN, getToken());
        }
    }

    private static String token;

    public static String getToken() {
        if (token == null) {
            token = FileUtils.loadContentFromExternalFilesDir(gApp, Constant.FILE_TOKEN);
        }
        return token;
    }

    public static void setToken(String token) {
        MyApplication.token = token;
        if (TextUtils.isEmpty(token)) {
            FileUtils.deleteFileFromExternalFilesDir(gApp, Constant.FILE_TOKEN);
        } else {
            FileUtils.saveFile2ExternalFilesDir(gApp, Constant.FILE_TOKEN, token.getBytes());
        }

        setGlobalParamAndHeader();
    }

    /**
     * 初始化网络框架配置
     */
    private void initNetConfig() {
        RequestBean.callbackUnlogin = true;
        setGlobalParamAndHeader();
        ResponseConstant.TAG_CODE = "code";
    }
}
