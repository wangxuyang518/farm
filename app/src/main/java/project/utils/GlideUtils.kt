package project.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

public class GlideUtils{

    companion object {
        public fun showImage(rec:Int,iv:ImageView){
            Glide.with(iv.context)
                    .load(rec)
                    .into(iv)
        }
    }
}