package project.buyer.ui.fragment

import android.view.View
import project.farm.R
import project.login.presenter.LoginPresenter
import project.mvp.base.BaseMvpFragment

public class OrderFragment : BaseMvpFragment<LoginPresenter>(){
    override fun handleException(throwable: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {

    }

    override fun inject() {
        getDaggerFragmentComponent().inject(this)
    }

    override fun inflaterView(): View {
        return View.inflate(activity, R.layout.fragment_order,null)
    }

}