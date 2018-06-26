package project.seller.ui.fragment.presenter

import project.farm.R
import project.mvp.base.IBaseView
import project.mvp.base.RxPresenter
import project.seller.ui.fragment.model.MainItem
import javax.inject.Inject

public class HomePresenter @Inject constructor(): RxPresenter<IBaseView>() {

    public fun getItemData():ArrayList<MainItem>{
        var mainItemList : ArrayList<MainItem> = ArrayList<MainItem>();
        mainItemList.add(MainItem("叶菜", R.mipmap.main_item1))
        mainItemList.add(MainItem("葱蒜", R.mipmap.main_item2))
        mainItemList.add(MainItem("茄果", R.mipmap.main_item3))
        mainItemList.add(MainItem("豆类", R.mipmap.main_item4))
        mainItemList.add(MainItem("菜根", R.mipmap.main_item5))
        mainItemList.add(MainItem("果类", R.mipmap.main_item6))
        mainItemList.add(MainItem("桃类", R.mipmap.main_item7))
        mainItemList.add(MainItem("瓜类", R.mipmap.main_item8))
        mainItemList.add(MainItem("橘橙", R.mipmap.main_item9))
        mainItemList.add(MainItem("干果", R.mipmap.main_item10))
        return mainItemList;
    }
}