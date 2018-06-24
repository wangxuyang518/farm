package project.login.presenter

import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class test {

    internal var imageView: ImageView? = null
    fun test() {
        imageView!!.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {

            } else if (motionEvent.action == MotionEvent.ACTION_UP) {

            }
            false
        }

    }

}
