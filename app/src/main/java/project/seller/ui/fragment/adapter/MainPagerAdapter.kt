package project.seller.ui.fragment.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup



public class MainPagerAdapter constructor(images: Array<View?>) : PagerAdapter(){
    var titles= arrayListOf<String>("推荐商品","优质农场主")

    var images: Array<View?> = arrayOfNulls<View>(2);
    init {
        this.images=images;
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    override fun getCount(): Int {
        return images.size
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
        view.removeView(images.get(position))
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        view.addView(images.get(position))
        return images[position] as View
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.get(position)
    }
}