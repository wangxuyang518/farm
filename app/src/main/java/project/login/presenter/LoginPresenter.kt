package project.login.presenter

import android.content.Intent
import com.alibaba.fastjson.TypeReference
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPUtils

import io.reactivex.functions.Consumer
import project.farm.R
import project.login.LoginMessageActivity
import project.login.LoginPassWordActivity
import project.login.MainActivity
import project.login.RegisterActivity

import project.mvp.base.IBaseView
import project.mvp.base.RxPresenter
import project.mvp.http.Api
import project.mvp.repertory.BaseRepertory
import project.utils.ComposeUtils
import java.util.*
import javax.inject.Inject

/**
 * 注册/登录/修改密码
 */
public class LoginPresenter @Inject constructor() : RxPresenter<IBaseView>() {


    //密码
    public fun login(activity: LoginPassWordActivity,vararg  string: CharSequence) {
        var s = HashMap<String, Any>();
        s.put("userCode", string[0])
        s.put("passWord",string[1])
        BaseRepertory.okgoPost<String>(aesEncode(s), Api.loginUrl, object : TypeReference<String>() {})
                .compose(ComposeUtils.ComposeUtil(mView))
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String) {
                        SPUtils.getInstance().put("token", t);
                        activity.startActivity(Intent(activity,MainActivity::class.java))
                        activity.finish()
                    }
                },error)
    }

    //验证码登录
    public fun loginMessage(activity:LoginMessageActivity ,vararg  string: CharSequence){
        var s = HashMap<String, Any>();
        s.put("userCode", string[0])
        s.put("code",string[1])
        BaseRepertory.okgoPost<String>(aesEncode(s), Api.codeUrl, object : TypeReference<String>() {})
                .compose(ComposeUtils.ComposeUtil(mView))
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String) {
                        SPUtils.getInstance().put("token", t);
                        activity.startActivity(Intent(activity,MainActivity::class.java))
                        activity.finish()
                    }
                },error)
    }
}

