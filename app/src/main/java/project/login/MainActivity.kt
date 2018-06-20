package project.login


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*
import project.buyer.ui.fragment.HomeFragment
import project.buyer.ui.fragment.MineFragment
import project.buyer.ui.fragment.OrderFragment
import project.buyer.ui.fragment.PublishFragment
import project.farm.R






class MainActivity : AppCompatActivity() {

    private lateinit var mFragmentTransaction: FragmentTransaction
    private var firstPressedTime=0L

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        mFragmentTransaction=supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                mFragmentTransaction.replace(R.id.mFrameLayout, HomeFragment(), "home")
                mFragmentTransaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_publish -> {
                mFragmentTransaction.replace(R.id.mFrameLayout, PublishFragment(), "pulish")
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation);

        mFragmentTransaction = supportFragmentManager.beginTransaction()
        mFragmentTransaction.add(R.id.mFrameLayout, HomeFragment(), "home")
        mFragmentTransaction.commit()

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


}
