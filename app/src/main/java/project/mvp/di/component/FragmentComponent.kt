package xinyi.com.appmanager.di.component;


import dagger.Component
import project.buyer.ui.fragment.HomeFragment
import project.buyer.ui.fragment.MineFragment
import project.buyer.ui.fragment.OrderFragment
import project.buyer.ui.fragment.PublishFragment
import project.mvp.base.BaseMvpFragment
import xinyi.com.appmanager.di.module.FragmentModule
import xinyi.com.appmanager.di.scope.FragmentScope


/**
 * Created by wxy on 2017/9/4.
 */

@FragmentScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    val activity: BaseMvpFragment<*>

    fun inject(mHomeFragment: HomeFragment);
    fun inject(mPublishFragment: PublishFragment);
    fun inject(mOrderFragment: OrderFragment);
    fun inject(mMineFragment: MineFragment);
}

