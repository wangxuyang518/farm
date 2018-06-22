package project.buyer.ui.fragment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.view.*
import project.buyer.ui.fragment.adapter.MainItemAdapter
import project.buyer.ui.fragment.adapter.MainPagerAdapter
import project.buyer.ui.fragment.adapter.MainTabAdapter
import project.buyer.ui.fragment.model.MainItem
import project.buyer.ui.fragment.presenter.HomePresenter
import project.farm.R
import project.helper.GlideImageLoader
import project.helper.TabLayoutHelper
import project.mvp.base.BaseMvpFragment


public class HomeFragment : BaseMvpFragment<HomePresenter>() {

    public lateinit var mMainItemAdapter: MainItemAdapter //item的adapter
    public lateinit var mMainPagerAdapter: MainPagerAdapter  //viewpager的adapter
    public var mainItemList: ArrayList<MainItem> = ArrayList<MainItem>();

    public lateinit var mMainTab1Adapter: MainItemAdapter //item的adapter
    public lateinit var mMainTab2Adapter: MainItemAdapter //item的adapter
    public var mainTab1List: ArrayList<MainItem> = ArrayList<MainItem>();
    public var mainTab2List: ArrayList<MainItem> = ArrayList<MainItem>();



    public lateinit var tab1RecycleView: RecyclerView
    public lateinit var tab2RecycleView: RecyclerView
    var images = arrayOf<Int>(R.mipmap.main_banner)
    var titles = arrayOf<String>("")
    override fun initView() {
        mRootView.mRecyclerView.layoutManager = GridLayoutManager(activity, 5);
        mMainItemAdapter = MainItemAdapter(R.layout.item_main_item, mPresenter.getItemData());
        mRootView.mRecyclerView.adapter = mMainItemAdapter;
        mRootView.mBanner.setImageLoader(GlideImageLoader())
        mRootView.mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR)
        mRootView.mBanner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        mRootView.mBanner.setDelayTime(2000);//设置轮播时间
      //  mRootView.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
        mRootView.mBanner.setImages(images.toMutableList());//设置图片源
        mRootView.mBanner.setBannerTitles(titles.toMutableList());//设置标题源
        mRootView.mBanner.start();

        //tabLayout设置
        var v1: View = View.inflate(activity, R.layout.fragment_recycle, null);
        var v2: View = View.inflate(activity, R.layout.fragment_recycle, null);
        tab1RecycleView = v1.findViewById(R.id.mTabRecyclerView)
        tab2RecycleView = v2.findViewById(R.id.mTabRecyclerView)
        tab1RecycleView.layoutManager=LinearLayoutManager(activity);
        tab2RecycleView.layoutManager=LinearLayoutManager(activity);
        var views: Array<View?> = arrayOfNulls<View>(2);
        views[0] = v1
        views[1] = v2

        for (i in 0..40){
            var v=MainItem(""+1,R.mipmap.ic_launcher);
            mainTab1List.add(v)
        }

        for (i in 0..40){
            var v=MainItem(""+1,R.mipmap.ic_launcher);
            mainTab2List.add(v)
        }
        tab1RecycleView.adapter=MainTabAdapter(R.layout.item_main_item,mainTab1List);
        tab2RecycleView.adapter=MainTabAdapter(R.layout.item_main_item,mainTab2List);

        mMainPagerAdapter = MainPagerAdapter(views)
        mRootView.mviewpager.adapter = mMainPagerAdapter
        mRootView.mTabLayout.setupWithViewPager(mRootView.mviewpager);
        TabLayoutHelper.setIndicator(mRootView.mTabLayout, 20, 20)

    }

    override fun inject() {
        getDaggerFragmentComponent().inject(this)
    }

    override fun inflaterView(): View {
        return View.inflate(activity, R.layout.fragment_home, null)
    }

}