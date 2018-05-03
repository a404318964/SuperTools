package com.zwj.supertools;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.squareup.leakcanary.LeakCanary;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.zwj.supertools.bean.xs.XsContent;
import com.zwj.supertools.constant.NotifyConstant;
import com.zwj.supertools.constant.UrlConstant;
import com.zwj.supertools.constant.XSConstant;
import com.zwj.supertools.greendao.XsContentDaoOpe;
import com.zwj.supertools.ui.activity.fperson.FPersonBirthdayListActivity;
import com.zwj.supertools.ui.activity.fund.CurFundInfoListActivity;
import com.zwj.supertools.ui.activity.xs.XsContentActivity;
import com.zwj.zwjutils.DateUtil;
import com.zwj.zwjutils.FileUtils;
import com.zwj.zwjutils.LogUtils;
import com.zwj.zwjutils.ToastUtil;
import com.zwj.zwjutils.image.ImageBuilder;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.bean.ResponseBean;
import com.zwj.zwjutils.net.callback.ParseBeanCallBack;
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

        initUMeng();


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

        mPushAgent.setDisplayNotificationNumber(0);

        // 处理推送自定义行为
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                LogUtils.i("notificationClickHandler", "msg.custom -----> "+msg.custom);

                Intent intent = null;
                switch (msg.custom) {
                    case NotifyConstant.PUSH_TYPE_OPEN_XS_CONTENT:
                        intent = new Intent(MyApplication.getGlobalContext(), XsContentActivity.class);
                        intent.putExtra(XSConstant.CONTENT_ID, msg.extra.get(XSConstant.CONTENT_ID));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;

                    case NotifyConstant.PUSH_TYPE_FUND:
                        intent = new Intent(MyApplication.getGlobalContext(), CurFundInfoListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;

                    case NotifyConstant.PUSH_TYPE_BIRTHDAY:
                        String content = msg.extra.get("content");
                        intent = new Intent(MyApplication.getGlobalContext(), FPersonBirthdayListActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("content", content);
                        startActivity(intent);
                        break;
                }
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {

                switch (msg.custom) {
                    case NotifyConstant.PUSH_TYPE_OPEN_XS_CONTENT:
                        // 收到段落推送后将其添加到数据库
                        String contentId = msg.extra.get(XSConstant.CONTENT_ID);
                        String pushTime = msg.extra.get(NotifyConstant.PUSH_TIME);
                        if(!TextUtils.isEmpty(contentId)) {
                            new RequestBean(UrlConstant.URL_GET_XS_CONTENT_BY_ID, RequestBean.METHOD_GET)
                                    .addParam("id", contentId)
                                    .setCallback(new ParseBeanCallBack<XsContent>(XsContent.class) {
                                        @Override
                                        public void onSuccess(ResponseBean responseBean, XsContent xsContent) {
                                            xsContent.setCreateTime(DateUtil.str2Date(pushTime));
                                            XsContentDaoOpe.insertData(xsContent);
                                        }
                                    }).request(MyApplication.getGlobalContext());
                        }
                        break;
                }

//                switch (msg.builder_id) {
//                    case 1:
//                        Notification.Builder builder = new Notification.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
//                                R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
//                                getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
//                                getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView)
//                                .setSmallIcon(getSmallIconId(context, msg))
//                                .setTicker(msg.ticker)
//                                .setAutoCancel(true);
//
//                        return builder.getNotification();
//                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
//                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);
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

    private void initUMeng() {
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "3e9af2616cf3e4e348af0be173ac12ab");
        UMConfigure.init(this, "59dccfb7310c930e99000bbd", "zwj", UMConfigure.DEVICE_TYPE_PHONE,
                "3e9af2616cf3e4e348af0be173ac12ab");

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);


        // 禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);

        // 日志加密
        UMConfigure.setEncryptEnabled(true);
    }
}
