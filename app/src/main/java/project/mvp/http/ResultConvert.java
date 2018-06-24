package project.mvp.http;


import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.convert.Converter;

import okhttp3.Response;
import okhttp3.ResponseBody;
import project.mvp.application.Constant;

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
    public T convertResponse(Response response) throws Throwable
    {
        if (typeReference == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;
        if (response.code() == 200) {
            String d = body.string();
            Log.d("123456", d);
            com.alibaba.fastjson.JSONObject i = com.alibaba.fastjson.JSONObject.parseObject(d);
            if (i != null && i.getIntValue("code") == Constant.Companion.getSUCCESS()) {
                return (T) JSONObject.parseObject(JSONObject.toJSONString(i.get("data")), typeReference);
            } else if (i != null && i.getIntValue("code") == Constant.Companion.getFAIl()) {
                throw new FarmException(i.getString("msg"),Constant.Companion.getFAIl());
            } else if (i != null && i.getIntValue("code") == Constant.Companion.getUSERSTOP()) {
                throw new FarmException(i.getString("msg"),Constant.Companion.getUSERSTOP());
            } else if (i != null && i.getIntValue("code") == Constant.Companion.getUSERFAILURE()) {
                throw new FarmException(i.getString("msg"),Constant.Companion.getUSERFAILURE());
            } else if (i != null && i.getIntValue("code") == Constant.Companion.getCODEFAILURE()) {
                throw new FarmException(i.getString("msg"),Constant.Companion.getCODEFAILURE());
            } else if (i != null && i.getIntValue("code") == Constant.Companion.getSMSSENDFAILURE()) {
                throw new FarmException(i.getString("msg"),Constant.Companion.getSMSSENDFAILURE());
            }else if (i != null && i.getIntValue("code") == Constant.Companion.getERRORTOKEN()) {
                throw new FarmException(Constant.Companion.getERRORTOKENTXT(),Constant.Companion.getERRORTOKEN());
            }
        } else if (response.code() == 401) {
            throw new FarmException(Constant.Companion.getNOPESSPERMISSION(), 401);
        } else if (response.code() == 403) {
            throw new FarmException(Constant.Companion.getFORBIDDEN(), 403);
        } else if (response.code() == 404) {
            throw new FarmException(Constant.Companion.getNOTFOUND(), 404);
        } else {
            throw new FarmException(Constant.Companion.getUNKOWN(), 405);
        }
        return null;
    }

}
