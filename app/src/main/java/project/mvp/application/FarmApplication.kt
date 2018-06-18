package project.mvp.application

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.model.HttpHeaders
import okhttp3.OkHttpClient
import xinyi.com.appmanager.di.component.ApplicationComponent
import xinyi.com.appmanager.di.component.DaggerApplicationComponent
import xinyi.com.appmanager.di.module.ApplicationModule
import java.util.concurrent.TimeUnit

public class FarmApplication : Application() {
    public lateinit var mApplicationComponent: ApplicationComponent;
    companion object {
        public lateinit var mFarmApplication: FarmApplication
    }

    override fun onCreate() {
        super.onCreate()
        mFarmApplication = this
        Utils.init(this)
        initDagger2()
        initOkgo()
    }

    fun initDagger2() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun initOkgo() {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(5000, TimeUnit.MILLISECONDS)
        builder.writeTimeout(5000, TimeUnit.MILLISECONDS)
        builder.connectTimeout(5000, TimeUnit.MILLISECONDS)
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(1)
                .addCommonHeaders(HttpHeaders("Content-Type", "application/json"))
    }


    public fun getApplicationComponent():ApplicationComponent{
        if (mApplicationComponent==null)
            initDagger2()
        return mApplicationComponent
    }
}