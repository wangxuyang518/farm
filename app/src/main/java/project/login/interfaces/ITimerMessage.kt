package project.login.interfaces

import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

interface ITimerMessage {
    public var mDisposable: Disposable
    public var clickDisposable: Disposable
    public fun timer(tvMessage: TextView) {
        clickDisposable = RxView.clicks(tvMessage)
                .throttleFirst(60, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    tvMessage.isClickable = false
                    mDisposable = Observable
                            .interval(1, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                if (it.toInt() == 60) {
                                    tvMessage.setText("获取验证码")
                                    tvMessage.isClickable = true
                                    mDisposable.dispose()
                                } else {
                                    tvMessage.setText("剩余" + (60 - it.toInt()) + "秒");
                                }
                            }
                };
    }
}