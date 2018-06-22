package project.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import project.mvp.application.FarmApplication;
import xinyi.com.appmanager.di.component.DaggerFragmentComponent;
import xinyi.com.appmanager.di.component.FragmentComponent;
import xinyi.com.appmanager.di.module.FragmentModule;

public abstract class BaseMvpFragment <T extends RxPresenter>extends BaseFragment implements IBaseView {

    @Inject
    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
        if (mPresenter != null)
            mPresenter.attachView(this);

    }
    public abstract void inject();
    public FragmentComponent getDaggerFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(FarmApplication.mFarmApplication.mApplicationComponent)
                .fragmentModule(new FragmentModule(this))
                .build();
    }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }
}
