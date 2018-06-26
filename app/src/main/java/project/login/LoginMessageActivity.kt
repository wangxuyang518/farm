package project.login

import android.graphics.Color
import com.blankj.utilcode.util.ActivityUtils
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_login.*

import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.application.Constant
import project.mvp.base.BaseMvpActivity
import java.util.concurrent.TimeUnit

public class LoginMessageActivity : BaseMvpActivity<LoginPresenter>(){

    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        setStateBar(Color.TRANSPARENT)
        return R.layout.activity_login
    }

    override fun initView() {
        tvPassword.setOnClickListener {
            ActivityUtils.startActivity(LoginPassWordActivity::class.java)
        }
        tvRegister.setOnClickListener {
            ActivityUtils.startActivity(RegisterActivity::class.java)
        }
        tvForgetPassWord.setOnClickListener {
            ActivityUtils.startActivity(FindPassWordActivity::class.java)
        }
        mPresenter.countdown(tvMessage, etPhone)//获取短信验证码

        //登录
        RxView.clicks(btLogin)
                .throttleFirst(Constant.DURATION,TimeUnit.MICROSECONDS)
                .subscribe {
                    if (checkViews(etPhone,etMessage)) {
                        mPresenter.loginMessage(this,etPhone.text,etMessage.text)
                    }
                }

    }

}