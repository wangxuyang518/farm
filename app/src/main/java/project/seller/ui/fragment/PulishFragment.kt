package project.seller.ui.fragment

import android.content.Intent
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_title_head.view.*
import kotlinx.android.synthetic.main.fragment_publish.view.*
import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpFragment


public class PulishFragment : BaseMvpFragment<LoginPresenter>() {

    private lateinit var handler: Handler
    override fun initView() {
        handler = Handler() {
            mRootView.mPickImages.visibility = View.VISIBLE
            return@Handler true
        }

        mRootView.centerTitle.text = "发布"
        mRootView.tips.setOnClickListener {
            mRootView.tips.visibility = View.GONE
            mRootView.tvTips.visibility = View.GONE
            mRootView.mPickImages.performTakenPhoto()
            handler.sendEmptyMessageDelayed(1, 1000)
        }
    }

    override fun inject() {
        getDaggerFragmentComponent().inject(this)
    }

    override fun inflaterView(): View {
        return View.inflate(activity, R.layout.fragment_publish, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mRootView.mPickImages.onActivityResult(requestCode, resultCode, data)
    }
}