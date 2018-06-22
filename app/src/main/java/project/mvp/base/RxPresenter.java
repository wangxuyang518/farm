package project.mvp.base;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import project.helper.EncryptHelper;

/**
 * Created by codeest on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 * <p>
 */
public class RxPresenter<T extends IBaseView> implements IBasePresenter{

    protected CompositeDisposable mCompositeDisposable;
    public T mView;


    public void attachView(T view) {
        this.mView = view;
    }


    public void detachView() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        mView = null;
    }

    public void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public String encode(HashMap<String, Object> map) {
        return EncryptHelper.Companion.encode(JSONObject.toJSONString(map));
    }
}


