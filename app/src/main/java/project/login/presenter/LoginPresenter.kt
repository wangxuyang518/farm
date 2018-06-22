package project.login.presenter

import android.widget.TextView
import com.alibaba.fastjson.TypeReference
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import project.mvp.base.IBaseView
import project.mvp.base.RxPresenter
import project.mvp.http.Api
import project.mvp.repertory.BaseRepertory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * 注册/登录/修改密码
 */
public class LoginPresenter @Inject constructor() : RxPresenter<IBaseView>() {

    private var mDisable: Disposable? = null

    //倒计时
    public fun countdown(tvMessage: TextView) {
        addSubscribe(RxView.clicks(tvMessage)
                .throttleFirst(60, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    tvMessage.isClickable = false
                    mDisable = Observable
                            .interval(1, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                if (it.toInt() == 60) {
                                    tvMessage.setText("获取验证码")
                                    tvMessage.isClickable = true
                                    mDisable!!.dispose()
                                } else {
                                    tvMessage.setText("剩余" + (60 - it.toInt()) + "秒");
                                }
                            }
                    addSubscribe(mDisable)
                })
    }


    public fun getData() {
        var s = HashMap<String, Any>();
        s.put("userCode", "123")
        s.put("passWord ", "123")
        s.put("requestToken", "1231231231231212312312123")
        BaseRepertory.okgoPost(encode(s),Api.loginUrl, object : TypeReference<String>() {})
              /*  .compose(ComposeUtils.ComposeUtil(mView))
                .subscribe {

                }*/


    }
}