package project.login

import android.graphics.Color
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_registere.*
import project.farm.R
import project.login.presenter.RegisterPresenter
import project.mvp.application.Constant
import project.mvp.base.BaseMvpActivity
import java.util.concurrent.TimeUnit

class RegisterActivity : BaseMvpActivity<RegisterPresenter>() {

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
                .throttleFirst(Constant.DURATION, TimeUnit.SECONDS)   //两秒钟之内只取一个点击事件，防抖操作
                .subscribe {
                    if (checkViews(etName, etPhone, etPassWord, etMessage)) {
                        mPresenter.regist(this, etName.text, etPhone.text, etPassWord.text, etMessage.text)
                    }
                }

        RxView.clicks(ivBack)
                .throttleFirst(Constant.DURATION, TimeUnit.MICROSECONDS)
                .subscribe {
                    finish()
                }
    }

}