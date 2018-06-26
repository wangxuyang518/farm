package project.login.presenter

import project.login.FindPassWordActivity
import project.mvp.base.IBaseView
import project.mvp.base.RxPresenter
import javax.inject.Inject


public class FindBackPresenter @Inject constructor() : RxPresenter<IBaseView>() {



    public fun setNewPassWord(activity:FindPassWordActivity,vararg string: CharSequence){

    }
}

