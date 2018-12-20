package com.example.armen.dandur.screen

import android.content.Context
import android.content.res.Resources
import android.media.Image
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.view.Display
import android.view.WindowManager





class ScreenRecyclerTopItem(context: Context): RelativeLayout(context) {
  lateinit var imgIcon:ImageView
  init {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    val width = size.x
    val height = size.y
    val param  = ViewGroup.LayoutParams(width/5, ViewGroup.LayoutParams.MATCH_PARENT)
    this.layoutParams = param
    createImgIcon()
  }

  fun createImgIcon() {
    imgIcon = ImageView(context)
    imgIcon.scaleType = ImageView.ScaleType.CENTER_CROP
    val param  = RelativeLayout.LayoutParams(32.dp, 32.dp)
    param.addRule(RelativeLayout.CENTER_IN_PARENT)
    imgIcon.layoutParams = param
    this.addView(imgIcon)
  }

}

private val Int.dp: Int
  get()=(this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()