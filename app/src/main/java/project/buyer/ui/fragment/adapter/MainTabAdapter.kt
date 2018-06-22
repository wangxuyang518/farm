package project.buyer.ui.fragment.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import project.buyer.ui.fragment.model.MainItem
import project.farm.R
import project.utils.GlideUtils

public class MainTabAdapter(layoutResId: Int, data: MutableList<MainItem>?) : BaseQuickAdapter<MainItem, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder?, item: MainItem?) {
        var name = helper!!.getView<TextView>(R.id.tvName) as TextView;
        var image = helper!!.getView<ImageView>(R.id.ivItem);
        name.text=item!!.name;
        GlideUtils.showImage(item.icon,image)
    }

}