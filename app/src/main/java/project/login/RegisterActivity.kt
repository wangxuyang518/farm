package project.login

import android.graphics.Color
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_registere.*
import project.farm.R
import project.login.presenter.LoginPresenter
import project.login.presenter.RegisterPresenter
import project.mvp.base.BaseMvpActivity
import java.util.concurrent.TimeUnit
import android.widget.Toast
import android.R.attr.button

import project.farm.R.id.*

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(){

    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        setStateBar(Color.TRANSPARENT)
        return R.layout.activity_registere
    }

    override fun initView() {
        mPresenter.countdown(tvMessage, etPhone)
        RxView.clicks(btLogin)
                .throttleFirst(1, TimeUnit.SECONDS)   //两秒钟之内只取一个点击事件，防抖操作
                .subscribe {
                    if (checkViews(etName,etPhone,etPassWord,etMessage))
                    {
                        mPresenter.regist(this,etName.text,etPhone.text,etPassWord.text,etMessage.text)
                    }
                }

        RxView.clicks(ivBack)
                .throttleFirst(500,TimeUnit.MICROSECONDS)
                .subscribe {
                    finish()
                }
    }

}