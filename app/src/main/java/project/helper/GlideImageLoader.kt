package project.helper

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader


/**
 * Binner 图片加载器
 */
public class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView!!.scaleType=ImageView.ScaleType.FIT_XY
        Glide.with(context)
                .load(path)
                .into(imageView)
    }

    override fun createImageView(context: Context?): ImageView {
        return super.createImageView(context)
    }

}