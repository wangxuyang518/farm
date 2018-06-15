package xinyi.com.appmanager.di.module;

import dagger.Module
import dagger.Provides
import project.mvp.application.FarmApplication
import javax.inject.Singleton


/**
 * Created by wxy on 2017/9/4.
 */
@Module
class ApplicationModule(private val application: FarmApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): FarmApplication {
        return application
    }

}

