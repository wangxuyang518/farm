package xinyi.com.appmanager.di.module;

import dagger.Module
import dagger.Provides
import project.mvp.base.BaseMvpFragment
import xinyi.com.appmanager.di.scope.FragmentScope


/**
 * Created by wxy on 2017/9/4.
 */

@Module
class FragmentModule(public val mFragment: BaseMvpFragment<*>) {
    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @FragmentScope
    internal fun provideBaseMvpActivity(): BaseMvpFragment<*> {
        return this.mFragment
    }



}
