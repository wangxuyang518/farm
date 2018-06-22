package project.login

import android.graphics.Color
import com.blankj.utilcode.util.ActivityUtils
import kotlinx.android.synthetic.main.activity_login.*
import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpActivity

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
        mPresenter.countdown(tvMessage);
    }

}