package com.yuanchuangli.mreader.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuanchuangli.mreader.R;
import com.yuanchuangli.mreader.ui.fragment.MyDocFragment;
import com.yuanchuangli.mreader.ui.fragment.MyMessageFragment;
import com.yuanchuangli.mreader.ui.fragment.RechargeFragment;
import com.yuanchuangli.mreader.ui.fragment.SelectedDocFragment;
import com.yuanchuangli.mreader.utils.ActivityCollector;
import com.yuanchuangli.mreader.utils.BackHandledFragment;
import com.yuanchuangli.mreader.utils.LogUtils;
import com.yuanchuangli.mreader.utils.NetUtils;

/**
 *
 */
public class MainActivity extends BaseActivity implements BackHandledFragment.BackHandlerInterface, View.OnClickListener {

    private RelativeLayout net_view_rl;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private BackHandledFragment selectedFragment;
    private NavigationView mNavigationView;
    //保存当前的Acticity
    public static MainActivity mainActivity;
    //记录是否网络通畅
    public static boolean netIsWork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.navigation_doc_seclected_title);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.getMenu().getItem(0).setChecked(true);
        mNavigationView.setItemTextAppearance(R.style.MenuTextStyle);
        setupDrawerContent(mNavigationView);
        setUpProfileImage();
        findView();
        init();
        switchToBook();
    }

    private void init() {
        mainActivity = this;
        netIsWork = NetUtils.isNetworkConnected(this);
        updateNetWorkText();
    }

    private void findView() {
        net_view_rl = (RelativeLayout) findViewById(R.id.net_view_rl);
        net_view_rl.setOnClickListener(this);
    }

    private void switchToBook() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new SelectedDocFragment()).commit();
        mToolbar.setTitle(R.string.navigation_doc_seclected_title);
    }

    private void switchToExample() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new RechargeFragment()).commit();
        mToolbar.setTitle(R.string.navigation_mydoc);
    }

    private void switchToBlog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MyMessageFragment()).commit();
        mToolbar.setTitle(R.string.navigation_mymessage_title);
    }


    private void switchToAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MyDocFragment()).commit();
        mToolbar.setTitle(R.string.navigation_recharge_title);
    }

    private void setUpProfileImage() {
        View headerView = mNavigationView.inflateHeaderView(R.layout.navigation_header);
        View profileView = headerView.findViewById(R.id.profile_image);
        TextView textName = (TextView) headerView.findViewById(R.id.id_nav_username);
        textName.setText("Blank");
        if (profileView != null) {
            profileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchToBlog();
                    mDrawerLayout.closeDrawers();
                    mNavigationView.getMenu().getItem(2).setChecked(true);
                }
            });
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.navigation_item_book:
                                switchToBook();
                                break;
                            case R.id.navigation_item_example:
                                switchToExample();
                                break;
                            case R.id.navigation_item_blog:
                                switchToBlog();
                                break;
                            case R.id.navigation_item_about:
                                switchToAbout();
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
        if (mainActivity == null) {
            return;
        }
        if (netIsWork) {
            mainActivity.net_view_rl.setVisibility(View.GONE);
            LogUtils.i("NetWork1", "网络可用");
        } else {
            mainActivity.net_view_rl.setVisibility(View.VISIBLE);
            LogUtils.i("NetWork1", "网络不可用");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.net_view_rl:
                NetUtils.startToSetting(mainActivity);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mainActivity = null;
        setContentView(R.layout.view_null);
        super.onDestroy();
    }
}
