package project.mvp.base;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

public interface IBasePresenter {

    public String aesEncode(HashMap<String,Object> map);
    public String aesDecode(String string);
}
