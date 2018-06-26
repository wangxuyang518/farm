package project.login


import android.graphics.Color
import android.text.InputType
import android.view.MotionEvent
import com.blankj.utilcode.util.ActivityUtils
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_login_password.*
import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.application.Constant
import project.mvp.base.BaseMvpActivity
import java.util.concurrent.TimeUnit


public class LoginPassWordActivity : BaseMvpActivity<LoginPresenter>(){



    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        setStateBar(Color.TRANSPARENT)
        return R.layout.activity_login_password
    }

    override fun initView() {
        etPhoneNumber.requestFocus()
        RxView.clicks(btLogin)
                .throttleFirst(Constant.DURATION, TimeUnit.MILLISECONDS)
                .subscribe {
                    if (checkViews(etPhoneNumber,etPassWord!!)){
                        mPresenter.login(this,etPhoneNumber.text,etPassWord.text)
                    }
                }
        tvRegister.setOnClickListener {
            ActivityUtils.startActivity(RegisterActivity::class.java)
        }
        tvForgetPassWord.setOnClickListener {
            ActivityUtils.startActivity(FindPassWordActivity::class.java)
        }
        tvLoginMessage.setOnClickListener {
            ActivityUtils.startActivity(LoginMessageActivity::class.java)
            finish()
        }


        ivPassWord!!.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                etPassWord.inputType=InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else if (motionEvent.action == MotionEvent.ACTION_UP) {
                etPassWord.inputType=InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPassWord.setSelection(etPassWord.text.length)
            true
        }
    }

}