package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.model.bean.UpdateItem;
import com.yuanchuangli.mreader.model.bean.user.User;
import com.yuanchuangli.mreader.presenter.IHomePresenter;
import com.yuanchuangli.mreader.presenter.impl.HomePresenter;
import com.yuanchuangli.mreader.ui.fragment.MyDocFragment;
import com.yuanchuangli.mreader.ui.fragment.MyMessageFragment;
import com.yuanchuangli.mreader.ui.fragment.RechargeFragment;
import com.yuanchuangli.mreader.ui.fragment.SelectedDocFragment;
import com.yuanchuangli.mreader.ui.view.IHomeView;
import com.yuanchuangli.mreader.utils.ActivityCollector;
import com.yuanchuangli.mreader.utils.BackHandledFragment;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.NetUtils;
import com.yuanchuangli.mreader.utils.SharedPreferenceUtil;
import com.yuanchuangli.mreader.utils.init.BaseApplication;

/**
 * @author Blank
 * @description HomeActivity
 * @time 2016/12/15 15:08
 */
public class HomeActivity extends BaseActivity implements IHomeView, BackHandledFragment.BackHandlerInterface, View.OnClickListener {

    private RelativeLayout net_view_rl;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private BackHandledFragment selectedFragment;
    private NavigationView mNavigationView;
    //保存当前的Acticity
    public static HomeActivity homeActivity;
    //记录是否网络通畅
    public static boolean netIsWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
        switchToSelectedDoc();
    }

    /**
     * 初始化控件或对象
     */
    private void findView() {
        homeActivity = this;
        netIsWork = NetUtils.isNetworkConnected(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        net_view_rl = (RelativeLayout) findViewById(R.id.net_view_rl);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        net_view_rl.setOnClickListener(this);
    }

    private void switchToSelectedDoc() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SelectedDocFragment()).commit();
        mToolbar.setTitle(R.string.navigation_doc_seclected_title);
    }

    private void switchToMyDoc() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new RechargeFragment()).commit();
        mToolbar.setTitle(R.string.navigation_mydoc);
    }

    private void switchToMyMessage() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MyMessageFragment()).commit();
        mToolbar.setTitle(R.string.navigation_mymessage_title);
    }


    private void switchToRecharge() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MyDocFragment()).commit();
        mToolbar.setTitle(R.string.navigation_recharge_title);
    }

    /**
     * 设置用户的信息栏，在非第一次登录时，会发生崩溃，原因没有user对象
     */
    private void setUpProfileImage() {
        View headerView = mNavigationView.inflateHeaderView(R.layout.navigation_header);
        ImageView profileView = (ImageView) headerView.findViewById(R.id.profile_image);
        TextView username = (TextView) headerView.findViewById(R.id.id_nav_username);
        TextView coin = (TextView) headerView.findViewById(R.id.user_coin);
        User user = (User) getIntent().getSerializableExtra("user_data");
        LogUtils.i("USER", user.toString());
        if (!TextUtils.isEmpty(user.getImagePath())) {
            Glide.with(BaseApplication.getContext()).load(user.getImagePath()).into(profileView);
        }
        coin.setText(user.getCoin());
        username.setText(SharedPreferenceUtil.getUser(BaseApplication.getContext()).getString("username", null));
        if (profileView != null) {
            profileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToMyMessage();//后面会专门写一个界面管理个人资料
                    mDrawerLayout.closeDrawers();
                    mNavigationView.getMenu().getItem(2).setChecked(true);
                }
            });
        }
    }

    /**
     * 侧边栏的点击事件
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.navigation_item_selecteddoc:
                                switchToSelectedDoc();
                                break;
                            case R.id.navigation_item_mydoc:
                                switchToMyDoc();
                                break;
                            case R.id.navigation_item_mymessage:
                                switchToMyMessage();
                                break;
                            case R.id.navigation_item_recharge:
                                switchToRecharge();
                                break;
                            case R.id.navigation_item_setting:
                                switchToRecharge();
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        this.selectedFragment = backHandledFragment;
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
        }
    }

    @Override
    public void onBackPressed() {
        if (selectedFragment == null || !selectedFragment.onBackPressed()) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                doExitApp();
            }
        }

    }

    /**
     * 根据netIsWork的值来决定是显示网络提示
     */
    public static void updateNetWorkText() {
        if (homeActivity == null) {
            return;
        }
        if (netIsWork) {
            homeActivity.net_view_rl.setVisibility(View.GONE);
            LogUtils.i("NetWork1", "网络可用");
        } else {
            homeActivity.net_view_rl.setVisibility(View.VISIBLE);
            LogUtils.i("NetWork1", "网络不可用");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.net_view_rl:
                NetUtils.startToSetting(homeActivity);
                break;
        }
    }

    /**
     * 等待修改
     */
    @Override
    protected void onDestroy() {
        homeActivity = null;
        setContentView(R.layout.view_null);
        super.onDestroy();
    }

    /**
     * 显示更新，暂时未写
     *
     * @param updateItem
     */
    @Override
    public void showUpdate(UpdateItem updateItem) {

    }

    @Override
    public void initView() {
        findView();
        mToolbar.setTitle(R.string.navigation_doc_seclected_title);
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView.getMenu().getItem(0).setChecked(true);
        mNavigationView.setItemTextAppearance(R.style.MenuTextStyle);
        IHomePresenter iHomePresenter = new HomePresenter(this);//暂时未用
        setupDrawerContent(mNavigationView);
        updateNetWorkText();
        setUpProfileImage();
    }
}
