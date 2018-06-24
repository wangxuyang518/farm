package project.mvp.base;

import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import project.helper.EncryptHelper;

import project.mvp.application.Constant;
import project.mvp.http.Api;
import project.mvp.repertory.BaseRepertory;
import project.utils.ComposeUtils;

/**
 * Created by codeest on 2016/8/2.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 * <p>
 */
public class RxPresenter<T extends IBaseView> implements IBasePresenter{
    private Disposable mDisable=null;

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
    public String aesEncode(HashMap<String, Object> map) {
        map.put("requestToken", UUID.randomUUID());
        return EncryptHelper.encrypt(JSONObject.toJSONString(map));
    }

    @Override
    public String aesDecode(String string) {
        return EncryptHelper.decrypt(string);
    }


    //统一处理
     public Consumer<Throwable>error=new Consumer<Throwable>() {
         @Override
         public void accept(Throwable throwable) throws Exception {
            mView.handleException(throwable);
         }
     };

    //获取二维码
    public void getMessage(String phone){
        HashMap map= new HashMap<String, Object> ();
        map.put("phone", phone);
        BaseRepertory.Companion.<String>okgoPost(aesEncode(map), Api.Companion.getCheckMessageUrl(),new TypeReference<String>(){})
                .compose(ComposeUtils.Companion.<String>ComposeUtil(mView))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ToastUtils.showShort("验证码获取成功");
                    }
                });
    }

    //获取短信验证码信息
    public void countdown(final TextView tvMessage, final EditText phone){
        RxView.clicks(tvMessage)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(Object o) throws Exception {
                        if (!StringUtils.isEmpty(phone.getText().toString()) && phone.getText().toString().length() == 11) {
                            return  true;
                        } else {
                            ToastUtils.showShort("请检查"+phone.getHint()+"格式是否正确");
                            return  false;
                        }
                    }
                })
                .throttleFirst(60, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        getMessage(phone.getText().toString());//获取验证码
                        tvMessage.setClickable(false);
                        mDisable =Observable
                                .interval(1, TimeUnit.SECONDS)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        if (aLong.intValue() == 60) {
                                            tvMessage.setText("获取验证码");
                                            tvMessage.setClickable(true);
                                            mDisable.dispose();
                                        } else {
                                            tvMessage.setText("剩余" + (60 - aLong.intValue()) + "秒");
                                        }
                                    }
                                });
                        addSubscribe(mDisable);
                    }
                });
    }
}


