package project.login

import android.graphics.Color
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_findpassword.*

import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpActivity
import java.util.concurrent.TimeUnit

public class FindPassWordActivity : BaseMvpActivity<LoginPresenter>() {


    override fun inject() {
        getDaggerActivityComponent().inject(this)
    }

    override fun getLayoutResource(): Int {
        setStateBar(Color.TRANSPARENT)
        return R.layout.activity_findpassword
    }

    override fun initView() {
       RxView.clicks(ivBack)
               .throttleFirst(500,TimeUnit.MICROSECONDS)
               .subscribe {
                   finish()
               }
    }

}