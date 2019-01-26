package com.example.armen.dandur.util

import android.content.res.Resources


public val Int.dp: Int
    get()=(this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

