package com.yuanchuangli.mreader.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.yuanchuangli.mreader.R;

import java.lang.reflect.InvocationTargetException;

public class DocInfoActivity extends BaseActivity implements View.OnClickListener {
    private WebView wvDoc;
    private Toolbar mToolbar;
    private ProgressBar pbWeb;
    private FloatingActionButton fabButton;
    private NestedScrollView nest;
    String url;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewtest);
        initData();
        findView();
        mToolbar = (Toolbar) findViewById(R.id.id_doc_toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocInfoActivity.this.onBackPressed();
            }
        });
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }

        setWebView();
        wvDoc.loadUrl(url);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        //setToolBar();
        //浮动按钮事件，暂时不添加，
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nest.smoothScrollTo(0, 0);
            }
        });
        //nest 与webview有冲突，暂时不做添加
//        nest.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                wvDoc.onTouchEvent(event);
//                return false;
//            }
//        });


        //用于解决滑动冲突，但是没有作用
//        wvDoc.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP)
//                    nest.requestDisallowInterceptTouchEvent(false);
//                else
//                    nest.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
    }

    private void setWebView() {
        WebSettings webSettings = wvDoc.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setTextZoom(110);
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua.replace("Android", "YCL_USER Android"));
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadsImagesAutomatically(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //webSettings.
        webSettings.setLoadWithOverviewMode(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        title = bundle.getString("title");
    }


    private void setToolBar() {
    }

    private void findView() {
        wvDoc = (WebView) findViewById(R.id.wv_doc);
        pbWeb = (ProgressBar) findViewById(R.id.pb_web);
        fabButton = (FloatingActionButton) findViewById(R.id.fabButton);
        nest = (NestedScrollView) findViewById(R.id.nest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvDoc.canGoBack()) {
            wvDoc.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, title + " " + url + getString(R.string.share_tail));
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share)));
                break;
            case R.id.action_use_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            wvDoc.getClass().getMethod("onResume").invoke(wvDoc, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            wvDoc.getClass().getMethod("onPause").invoke(wvDoc, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wvDoc != null) {
            ((ViewGroup) wvDoc.getParent()).removeView(wvDoc);
            wvDoc.destroy();
            wvDoc = null;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
