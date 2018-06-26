package project.seller.ui.fragment.adapter

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import project.farm.R
import project.seller.ui.fragment.model.MainItem
import project.utils.GlideUtils

public class MainItemAdapter(layoutResId: Int, data: MutableList<MainItem>?) : BaseQuickAdapter<MainItem, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder?, item: MainItem?) {
        var name = helper!!.getView<TextView>(R.id.tvName) as TextView;
        var image = helper!!.getView<ImageView>(R.id.ivItem);
        name.text=item!!.name;
        GlideUtils.showImage(item.icon,image)
    }

}