package xinyi.com.appmanager.di.component;


import dagger.Component
import project.login.FindPassWordActivity

import project.login.LoginMessageActivity
import project.login.LoginPassWordActivity
import project.login.RegisterActivity
import project.mvp.base.BaseMvpActivity
import xinyi.com.appmanager.di.module.ActivityModule
import xinyi.com.appmanager.di.scope.ActivityScope


/**
 * Created by wxy on 2017/9/4.
 */

@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    val activity: BaseMvpActivity<*>


    fun inject(login: LoginMessageActivity);
    fun inject(register: RegisterActivity);
    fun inject(login: LoginPassWordActivity);
    fun inject(findPassWordActivity: FindPassWordActivity);
}

