package com.zwj.supertools.ui.activity.xs;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.zwj.customview.progress.ProgressBean;
import com.zwj.customview.progress.ProgressUtil;
import com.zwj.customview.titleview.CommonTitleView;
import com.zwj.customview.titleview.SimpleTitleMenuClickListener;
import com.zwj.supertools.MyApplication;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.xs.XsContent;
import com.zwj.supertools.constant.UrlConstant;
import com.zwj.supertools.constant.XSConstant;
import com.zwj.supertools.ui.activity.base.BaseAutoLayoutCommonActivity;
import com.zwj.zwjutils.LogUtils;
import com.zwj.zwjutils.net.bean.RequestBean;
import com.zwj.zwjutils.net.bean.ResponseBean;
import com.zwj.zwjutils.net.callback.ParseBeanCallBack;

public class XsContentActivity extends BaseAutoLayoutCommonActivity {
    private CommonTitleView titleView;
    private TextView tvContent, tvContentTypeName, tvBookName, tvBookTypeName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_xs_content;
    }

    @Override
    protected void findViews() {
        titleView = getView(R.id.id_title);
        tvContent = getView(R.id.tv_content);
        tvContentTypeName = getView(R.id.tv_content_type_name);
        tvBookName = getView(R.id.tv_book_name);
        tvBookTypeName = getView(R.id.tv_book_type_name);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        String contentId = getIntent().getStringExtra(XSConstant.CONTENT_ID);
        if(TextUtils.isEmpty(contentId)) {
            XsContent xsContent = getIntent().getParcelableExtra(XSConstant.XS_CONTENT);
            refreshUI(xsContent);
        }else {
            new ProgressBean()
                    .setLoadingTip("获取内容中")
                    .startProgress(mContext);

            new RequestBean(UrlConstant.URL_GET_XS_CONTENT_BY_ID, RequestBean.METHOD_GET)
                    .addParam("id", contentId)
                    .setCallback(new ParseBeanCallBack<XsContent>(XsContent.class) {
                        @Override
                        public void onSuccess(ResponseBean responseBean, XsContent xsContent) {
                            refreshUI(xsContent);
                        }

                        @Override
                        public void onFinished(ResponseBean responseBean) {
                            LogUtils.i(TAG, "onFinished");
                            ProgressUtil.hideProgress();
                        }
                    }).request(MyApplication.getGlobalContext());
        }
    }

    @Override
    protected void setListener() {
        titleView.setOnTitleMenuClickListener(new SimpleTitleMenuClickListener(){
            @Override
            public void onClickImLeftListener() {
                finish();
            }
        });
    }

    private void refreshUI(XsContent xsContent) {
        tvContent.setText(xsContent.getContent());
        tvContentTypeName.setText(xsContent.getContentTypeName());
        tvBookName.setText(xsContent.getFromBookName());
        tvBookTypeName.setText(xsContent.getBookTypeName());
    }
}
