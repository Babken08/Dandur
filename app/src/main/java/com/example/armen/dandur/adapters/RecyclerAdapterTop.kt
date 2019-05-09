package com.example.armen.dandur.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.armen.dandur.R
import com.example.armen.dandur.screen.ScreenRecyclerBottomItem
import com.example.armen.dandur.screen.ScreenRecyclerTopItem
import com.example.armen.dandur.util.DandurConstants
import java.util.*
import java.nio.file.Files.size
import kotlin.collections.LinkedHashSet


class RecyclerAdapterTop(val context:Context) :RecyclerView.Adapter<RecyclerAdapterTop.RecyclerAdapterTopViewHolder>(){

  interface RecyclerAdapterTopDelegate{
    fun topBarItemClick(position:Int)
  }

  var delegate:RecyclerAdapterTopDelegate? = null
  var listIcons : ArrayList<Int>? = null
  lateinit var itemsList:MutableSet<ScreenRecyclerTopItem>
  var changeItemPosition:Int = 0


  init {
    itemsList = LinkedHashSet<ScreenRecyclerTopItem>()
    listIcons = ArrayList()
    createIcons(DandurConstants.isDarkBackground)
  }

  fun changeMode(){
    listIcons?.clear()
    itemsList.clear()
    createIcons(DandurConstants.isDarkBackground)
    notifyDataSetChanged()
  }

  fun createIcons(darkMode:Boolean) {
    if(darkMode) {
//      listIcons = ArrayList()
      listIcons!!.add(R.drawable.random_ligth)
      listIcons!!.add(R.drawable.trending_ligth)
      listIcons!!.add(R.drawable.hot_ligth)
      listIcons!!.add(R.drawable.celebrities_ligth)
//    listIcons!!.add(R.drawable.profile)
      listIcons!!.add(R.drawable.ic_more_vert_white_24dp)
    } else {
//      listIcons = ArrayList()
      listIcons!!.add(R.drawable.random)
      listIcons!!.add(R.drawable.trending)
      listIcons!!.add(R.drawable.hot_72)
      listIcons!!.add(R.drawable.celebrities)
//    listIcons!!.add(R.drawable.profile)
      listIcons!!.add(R.drawable.ic_more_vert_black_24dp)
    }
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
    val item  = holder.itemView as ScreenRecyclerTopItem
    holder.imgIcon.setImageResource(listIcons?.get(position)!!)
    if(itemsList.size!= listIcons?.size) {
      itemsList.add(item)
    }

    if(changeItemPosition != -1 && changeItemPosition == position) {
      setColorsItems(item)
    }

//    holder.imgIcon.setImageResource(listIcons?.get(position)!!)
    holder.itemView.setOnClickListener {
      if (changeItemPosition == position) {
        return@setOnClickListener
      }
      if (position != 4) {
        changeItemPosition = position
        setColorsItems(item)
      }
      delegate?.topBarItemClick(position)
    }
  }

  fun iconColorChange() {
    val iterator = itemsList.iterator()
    while (iterator.hasNext()) {
      val notClickedItem  = iterator.next()
        val colorBlack  =  ContextCompat.getColor(context, R.color.colorBlack)
        val colorWhite  =  ContextCompat.getColor(context, R.color.colorWhite)
        if(DandurConstants.isDarkBackground) {
          notClickedItem.imgIcon.colorFilter = PorterDuffColorFilter(colorWhite, PorterDuff.Mode.SRC_IN)
        }else {
          notClickedItem.imgIcon.colorFilter = PorterDuffColorFilter(colorBlack, PorterDuff.Mode.SRC_IN)
        }
    }
  }

    fun setColorsItems(item: ScreenRecyclerTopItem) {
    val color  =  ContextCompat.getColor(context, R.color.colorYellow)
    item.imgIcon.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)

    val iterator = itemsList.iterator()

    while (iterator.hasNext()) {
      val notClickedItem  = iterator.next()
      if (!notClickedItem.equals(item)){
        val colorBlack  =  ContextCompat.getColor(context, R.color.colorBlack)
        val colorWhite  =  ContextCompat.getColor(context, R.color.colorWhite)
        if(DandurConstants.isDarkBackground) {
          notClickedItem.imgIcon.colorFilter = PorterDuffColorFilter(colorWhite, PorterDuff.Mode.SRC_IN)
        }else {
          notClickedItem.imgIcon.colorFilter = PorterDuffColorFilter(colorBlack, PorterDuff.Mode.SRC_IN)
        }
      }
    }
  }
  class RecyclerAdapterTopViewHolder(itemView:ScreenRecyclerTopItem):RecyclerView.ViewHolder(itemView){
    var imgIcon  = itemView.imgIcon
  }
}