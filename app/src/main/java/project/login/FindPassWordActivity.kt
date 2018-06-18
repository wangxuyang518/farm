package project.login

import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpActivity

public class FindPassWordActivity : BaseMvpActivity<LoginPresenter>() {


    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_findpassword
    }

    override fun initView() {
        initToolBar("修改密码")
    }

}