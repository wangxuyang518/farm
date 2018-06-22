package project.mvp.http

import java.io.Serializable

/**
 * Created by jiajun.wang on 2018/2/25.
 * 统一的返回结果
 */

class ResultEntity<T> : Serializable {

    var code: Int = 0
    var msg: String? = null
    var data: T? = null
}
