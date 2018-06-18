package project.login
import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpActivity

 class RegisterActivity : BaseMvpActivity<LoginPresenter>() {
    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_registere
    }

    override fun initView() {

    }


}