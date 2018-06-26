package xinyi.com.appmanager.di.component;


import dagger.Component
import project.mvp.base.BaseMvpFragment
import project.seller.ui.fragment.HomeFragment
import project.seller.ui.fragment.MineFragment
import project.seller.ui.fragment.OrderFragment
import project.seller.ui.fragment.PulishFragment
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
    fun inject(mPublishFragment: PulishFragment);
    fun inject(mOrderFragment: OrderFragment);
    fun inject(mMineFragment: MineFragment);
}

