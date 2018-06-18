package project.login

import com.blankj.utilcode.util.ActivityUtils
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import project.farm.R
import project.mvp.base.BaseActivity
import java.util.concurrent.TimeUnit


public class  SplashActivity : BaseActivity(){
    override fun getLayoutResource(): Int {
       return R.layout.activity_splash
    }

    override fun initView() {
        Observable.timer(2,TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    ActivityUtils.startActivity(GuideActivity::class.java)
                    finish()
                })
    }

}