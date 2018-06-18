package project.mvp.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import project.farm.R;

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
        toolBar=findViewById(R.id.mtoolBar);
        initView();
    }
    public void initToolBar(String title){
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tvTitle=toolBar.findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        toolBar.setTitle("");
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

}
