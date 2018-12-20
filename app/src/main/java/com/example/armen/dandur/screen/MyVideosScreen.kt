package com.example.armen.dandur.screen

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.armen.dandur.R

class MyVideosScreen(context: Context) : LinearLayout(context){


    lateinit var title:TextView

    init {
        val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this.layoutParams = param
        this.orientation = VERTICAL

        createTile()

    }

    fun createTile() {
        title = TextView(context)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.topMargin = 10.dp
        params.bottomMargin = 10.dp
        params.leftMargin = 10.dp
        params.rightMargin = 10.dp
        title.layoutParams = params
        title.setTypeface(title.typeface, Typeface.BOLD)
        title.setTextColor(Color.parseColor("#000000"))
        title.textSize = 20f
        this.addView(title)
    }

}

private val Int.dp: Int
    get()=(this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()