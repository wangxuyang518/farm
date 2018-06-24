package project.login.presenter

import android.content.Intent
import com.alibaba.fastjson.TypeReference
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPUtils
import io.reactivex.functions.Consumer
import project.farm.R
import project.login.MainActivity
import project.login.RegisterActivity
import project.mvp.base.IBaseView
import project.mvp.base.RxPresenter
import project.mvp.http.Api
import project.mvp.repertory.BaseRepertory
import project.utils.ComposeUtils
import javax.inject.Inject


public class RegisterPresenter @Inject constructor() : RxPresenter<IBaseView>() {


    //注册接口
    public fun regist(activity:RegisterActivity ,vararg sts: CharSequence) {
        var map = HashMap<String, Any>();
        map.put("userName", sts[0])
        map.put("userCode", sts[1])
        map.put("passWord", sts[2])
        map.put("matcherPassWord", sts[2])
        map.put("code", sts[3])
        map.put("type", SPUtils.getInstance().getInt("authority"))
        BaseRepertory.okgoPost<String>(aesEncode(map), Api.registerUrl, object : TypeReference<String>() {})
                .compose(ComposeUtils.ComposeUtil(mView))
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String) {
                        SPUtils.getInstance().put("token", t);
                        activity.startActivity(Intent(activity,MainActivity::class.java))
                        activity.finish()
                    }
                }, error)
    }
}

