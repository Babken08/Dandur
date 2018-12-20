package com.example.armen.dandur.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.armen.dandur.R
import com.example.armen.dandur.screen.ScreenRecyclerTopItem
import java.util.*
import java.nio.file.Files.size



class RecyclerAdapterTop(val context:Context) :RecyclerView.Adapter<RecyclerAdapterTop.RecyclerAdapterTopViewHolder>(){

  var listIcons : ArrayList<Int>? = null

  init {
    listIcons = ArrayList()
    listIcons!!.add(R.drawable.random)
    listIcons!!.add(R.drawable.trending)
    listIcons!!.add(R.drawable.hot_72)
    listIcons!!.add(R.drawable.celebrities)
    listIcons!!.add(R.drawable.profile)
//        for (i in 0..4) {
//            listIcons?.add(R.drawable.ic_launcher_background)
//        }
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterTopViewHolder {
    return RecyclerAdapterTopViewHolder(ScreenRecyclerTopItem(context))
  }
  override fun getItemCount(): Int {
    if(listIcons == null) {
      return 0
    } else {
      return listIcons?.size!!
    }
  }
  override fun onBindViewHolder(holder: RecyclerAdapterTopViewHolder, position: Int) {
    holder.imgIcon.setImageResource(listIcons?.get(position)!!)
    holder.itemView.setOnClickListener {
      Toast.makeText(context, "TopBarClick", Toast.LENGTH_SHORT).show()

    }
  }
  class RecyclerAdapterTopViewHolder(itemView:ScreenRecyclerTopItem):RecyclerView.ViewHolder(itemView){
    var imgIcon  = itemView.imgIcon
  }
}