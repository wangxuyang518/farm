package project.login

import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpActivity

public class LoginMessageActivity : BaseMvpActivity<LoginPresenter>() {
    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
       return R.layout.activity_login
    }

    override fun initView() {

    }

}