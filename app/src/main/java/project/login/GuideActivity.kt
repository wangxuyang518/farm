package project.login

import android.os.Build
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPUtils
import kotlinx.android.synthetic.main.activity_guide.*
import project.farm.R
import project.mvp.application.Constant.Companion.BUYER
import project.mvp.application.Constant.Companion.SELLER
import project.mvp.base.BaseActivity

public class GuideActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().statusBarColor =resources.getColor(R.color.colorAccent)
        }
        return R.layout.activity_guide
    }

    override fun initView() {
        toolBar=findViewById(R.id.toolBar);
        initToolBar("角色选择")
        btSeller.setOnClickListener {
            doClick(SELLER)
        }
        btBuyer.setOnClickListener {
            doClick(BUYER)
        }
    }

    fun doClick(type: Int) {
        if (type == SELLER) {
            SPUtils.getInstance().put("authority", SELLER)
        } else {
            SPUtils.getInstance().put("authority", BUYER)
        }
        ActivityUtils.startActivity(LoginPassWordActivity::class.java)
        finish()
    }

}