package project.mvp.http;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.convert.Converter;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by jiajun.wang on 2018/2/25.
 * T为 要转换的数据类型
 */

public class ResultConvert<T> implements Converter<T> {
    private TypeReference typeReference;
    public ResultConvert(TypeReference typeReference) {
        this.typeReference = typeReference;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        if (typeReference == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        String data = body.string();
        if (response.code() == 200) {
            com.alibaba.fastjson.JSONObject i=com.alibaba.fastjson.JSONObject.parseObject(data);
            if (i.getIntValue("code")==0){
                return (T) JSONObject.parseObject(JSONObject.toJSONString(i.get("data")),typeReference);
            }else {
                throw  new Exception(i.getString("msg"));
            }
        } else if (response.code() == 401) {
            throw new Exception("没有权限");
        } else if (response.code() == 403) {
            throw new Exception("权限禁止");
        } else if (response.code() == 404) {
            throw new Exception("404 Not Found");
        } else {
            throw new Exception("未知错误");
        }
    }

}
