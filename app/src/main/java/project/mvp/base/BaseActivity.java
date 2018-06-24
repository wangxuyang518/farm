package project.mvp.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;

import project.farm.R;
import project.login.LoginPassWordActivity;
import project.mvp.application.Constant;
import project.mvp.application.FarmApplication;
import project.mvp.http.FarmException;

/**
 * Created by jiajun.wang on 2018/3/19.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @Nullable
    public TextView centerTitle = null;

    @Nullable
    public Toolbar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutResource());
        initView();
    }

    public void initToolBar(String s) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolBar.setTitle("");
        TextView title=toolBar.findViewById(R.id.centerTitle);
        title.setText(s);
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public abstract int getLayoutResource();

    public abstract void initView();

    @Override
    public void showNoNetWork() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoadFail() {

    }

    @Override
    public void showContentView() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmptyView() {

    }


    //统一处理异常捕获
    @Override
    public void handleException(Throwable throwable) {
        if (throwable instanceof FarmException) {
            FarmException mFarmException = (FarmException) throwable;
            if (mFarmException.getCode()!=5){
                ToastUtils.showShort(mFarmException.getMsg());
            }
            switch (mFarmException.getCode()) {
                case 6:
                    ActivityUtils.startActivity(new Intent(this, LoginPassWordActivity.class));
                    this.finish();
                    break;
            }
        } else {
            ToastUtils.showShort(throwable.getMessage());
        }
    }

    //设置状态栏
    public void setStateBar(int color) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
            window.setNavigationBarColor(color);
        }
    }
}
