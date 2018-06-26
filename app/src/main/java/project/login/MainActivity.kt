package project.login


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.util.Log
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import project.farm.R
import project.mvp.base.BaseActivity
import project.seller.ui.fragment.HomeFragment
import project.seller.ui.fragment.MineFragment
import project.seller.ui.fragment.OrderFragment
import project.seller.ui.fragment.PulishFragment


class MainActivity : BaseActivity() {


    override fun initView() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation);

        mFragmentTransaction = supportFragmentManager.beginTransaction()
        mFragmentTransaction.add(R.id.mFrameLayout, HomeFragment(), "home")
        mFragmentTransaction.commit()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getLayoutResource(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor = resources.getColor(R.color.colorAccent)
        }
        return R.layout.activity_main
    }

    private lateinit var mFragmentTransaction: FragmentTransaction
    private var firstPressedTime = 0L

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        mFragmentTransaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                mFragmentTransaction.replace(R.id.mFrameLayout, HomeFragment(), "home")
                mFragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_publish -> {
                mFragmentTransaction.replace(R.id.mFrameLayout, PulishFragment(), "pulish")
                mFragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_order -> {
                mFragmentTransaction.replace(R.id.mFrameLayout, OrderFragment(), "order")
                mFragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                mFragmentTransaction.replace(R.id.mFrameLayout, MineFragment(), "mine")
                mFragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime > 2000) {
            ToastUtils.showShort("再按一次退出")
            firstPressedTime = System.currentTimeMillis()
        } else {
            ActivityUtils.finishAllActivities()
            System.exit(0);
        }
    }


    //内部类解决，BottomNavigationView 多于3个混乱问题
    object BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        fun disableShiftMode(view: BottomNavigationView) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (i in 0 until menuView.childCount) {
                    val item = menuView.getChildAt(i) as BottomNavigationItemView
                    item.setShiftingMode(false)
                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
                Log.e("BNVHelper", "Unable to get shift mode field", e)
            } catch (e: IllegalAccessException) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var  f=supportFragmentManager.findFragmentByTag("pulish")
        if (f!=null){
            f.onActivityResult(requestCode , resultCode , data )
        }
    }
}
