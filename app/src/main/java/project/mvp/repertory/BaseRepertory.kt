package project.mvp.repertory

import com.alibaba.fastjson.TypeReference
import com.lzy.okgo.OkGo
import com.lzy.okrx2.adapter.ObservableBody
import io.reactivex.Observable
import project.mvp.http.ResultConvert

/**
 * 封装网络请求
 */
public class BaseRepertory {
    /**
     * okgo网络请求的二次封装
     */
    companion object {
        public fun <T> okgoPost(params: String, url: String, t: TypeReference<T>): Observable<T> {
            return OkGo.post<T>(url)
                    .params("p", params)
                    .converter(ResultConvert<T>(t))
                    .adapt(ObservableBody<T>())


        }
    }
}
