package project.utils

import com.blankj.utilcode.util.NetworkUtils
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import project.mvp.base.IBaseView
import project.mvp.http.ResultEntity

class ComposeUtils {


    companion object {
        public fun <T> ComposeUtil( mIBaseView: IBaseView): ObservableTransformer<ResultEntity<T>, T> {
            return ObservableTransformer { upstream ->
                upstream
                        .filter {
                            if (!NetworkUtils.isConnected()) {
                                throw Exception("网络异常")
                            } else {
                                true
                            }
                        }
                        .doOnSubscribe { mIBaseView.showLoading() }
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .flatMap { tResultEntity ->
                            Observable.just(tResultEntity.data!!)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally { mIBaseView.hideLoading() }
            }
        }
    }
}
