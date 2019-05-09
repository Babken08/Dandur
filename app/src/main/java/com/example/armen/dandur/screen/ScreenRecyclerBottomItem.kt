package com.example.armen.dandur.screen

import android.content.Context
import android.graphics.Point
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import com.example.armen.dandur.R
import com.example.armen.dandur.util.DandurConstants
import com.example.armen.dandur.util.dp

class ScreenRecyclerBottomItem(context: Context) :FrameLayout(context) {
  lateinit var imgTitle:TextView
  var isClicked = false
  init {
//    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//    val display = wm.defaultDisplay
//    val size = Point()
//    display.getSize(size)
//    val width = size.x
//    val height = size.y
    val param  = ViewGroup.LayoutParams(DandurConstants.deviceWith/6, ViewGroup.LayoutParams.MATCH_PARENT)
    this.layoutParams = param
    createImgTitle()
  }

  fun createImgTitle() {
    imgTitle = TextView(context)
    val param  = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    param.gravity = (Gravity.CENTER)
    imgTitle.layoutParams = param
    if(DandurConstants.isDarkBackground) {
      imgTitle.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
    } else {
      imgTitle.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
    }
    imgTitle.gravity = Gravity.CENTER
    imgTitle.textSize = 10f
    imgTitle.maxLines = 1
    imgTitle.ellipsize = TextUtils.TruncateAt.END
    this.addView(imgTitle)
  }
  fun configeItem(drawable:Int, title:String){
    val img = ContextCompat.getDrawable(context, drawable)
    img!!.setBounds(0, 0, 24.dp, 24.dp)
    imgTitle.setCompoundDrawables(null, img, null, null);
    imgTitle.text = title
//    if (isClicked) {
//      imgTitle.setTextColor(ContextCompat.getColor(context, R.color.colorYellow))
//    } else {
//      if(DandurConstants.isDarkBackground) {
//        imgTitle.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
//      } else {
//        imgTitle.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
//
//      }
//    }
  }
}

