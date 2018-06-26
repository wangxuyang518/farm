package project.login

import android.graphics.Color
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_findpassword.*
import project.farm.R
import project.login.presenter.FindBackPresenter
import project.mvp.application.Constant
import project.mvp.base.BaseMvpActivity
import java.util.concurrent.TimeUnit

/**
 * 找回密码
 */
public class FindPassWordActivity : BaseMvpActivity<FindBackPresenter>() {


    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        setStateBar(Color.TRANSPARENT)
        return R.layout.activity_findpassword
    }

    override fun initView() {
       mPresenter.countdown(tvMessage,etPhone)
       RxView.clicks(ivBack)
               .throttleFirst(Constant.DURATION,TimeUnit.MICROSECONDS)
               .subscribe {
                   finish()
               }

        RxView.clicks(btSubmit)
                .throttleFirst(Constant.DURATION,TimeUnit.MICROSECONDS)
                .subscribe {
                    mPresenter.setNewPassWord(this,etPhone.text,etMessage.text,etPhone.text)
                }
    }

}