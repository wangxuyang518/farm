package xinyi.com.appmanager.di.component;


import dagger.Component
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

}

