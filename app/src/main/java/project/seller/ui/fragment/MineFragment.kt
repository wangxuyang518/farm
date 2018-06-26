package project.seller.ui.fragment

import android.view.View
import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpFragment

public class MineFragment : BaseMvpFragment<LoginPresenter>() {

    override fun initView() {

    }

    override fun inject() {
        getDaggerFragmentComponent().inject(this)
    }

    override fun inflaterView(): View {
        return View.inflate(activity, R.layout.fragment_mine, null)
    }

}