package com.example.armen.dandur.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.armen.dandur.R
import com.example.armen.dandur.http.APIService
import com.example.armen.dandur.http.APIUtil
import com.example.armen.dandur.screen.ScreenRecyclerBottomItem
import java.util.*
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.graphics.PorterDuffColorFilter
import android.R.drawable
import com.example.armen.dandur.util.DandurConstants
import kotlin.collections.LinkedHashSet


class RecyclerAdapterBottom(val context: Context):RecyclerView.Adapter<RecyclerAdapterBottom.RecyclerAdapterBottomViewHolder>() {
    interface RecyclerAdapterBottomDelegate{
        fun bottomBarItemClick(position:Int)
    }
    var delegate : RecyclerAdapterBottomDelegate? = null
    lateinit var listIcons:ArrayList<Int>
    var apiService :APIService? = null
    lateinit var titles:Array<String>
    lateinit var itemsList:MutableSet<ScreenRecyclerBottomItem>
    var changeItemPosition:Int = -1

    init {
        itemsList = LinkedHashSet<ScreenRecyclerBottomItem>()
        apiService = APIUtil.getAPIService()
        listIcons = ArrayList()
        titles = context.resources.getStringArray(R.array.bottom_bar_titles)
        createIcons(DandurConstants.isDarkBackground)
    }

    fun changeMode() {
        listIcons.clear()
        itemsList.clear()
        createIcons(DandurConstants.isDarkBackground)
        notifyDataSetChanged()
    }
    fun createIcons(darkMode:Boolean){
        if(darkMode) {
            listIcons.add(R.drawable.funny_ligth)
//        listIcons.add(R.drawable.animals_and_pets)
            listIcons.add(R.drawable.anime_ligth)
            listIcons.add(R.drawable.music_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.gaming_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.cartoons_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.cats_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.dogs_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.dance_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.extreme_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.nsfw_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.art_and_design_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.nature_and_travel_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.news_and_politics_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.fashion_and_beauty_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.cars_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.science_and_technology_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.sports_ligth)
            listIcons.add(com.example.armen.dandur.R.drawable.movies_and_tv_ligth)
        }else {
            listIcons.add(R.drawable.funny)
//        listIcons.add(R.drawable.animals_and_pets)
            listIcons.add(R.drawable.anime)
            listIcons.add(R.drawable.music)
            listIcons.add(com.example.armen.dandur.R.drawable.gaming)
            listIcons.add(com.example.armen.dandur.R.drawable.cartoons)
            listIcons.add(com.example.armen.dandur.R.drawable.cats)
            listIcons.add(com.example.armen.dandur.R.drawable.dogs)
            listIcons.add(com.example.armen.dandur.R.drawable.dance)
            listIcons.add(com.example.armen.dandur.R.drawable.extreme)
            listIcons.add(com.example.armen.dandur.R.drawable.nsfw)
            listIcons.add(com.example.armen.dandur.R.drawable.art_and_design)
            listIcons.add(com.example.armen.dandur.R.drawable.nature_and_travel)
            listIcons.add(com.example.armen.dandur.R.drawable.news_and_politics)
            listIcons.add(com.example.armen.dandur.R.drawable.fashion_and_beauty)
            listIcons.add(com.example.armen.dandur.R.drawable.cars)
            listIcons.add(com.example.armen.dandur.R.drawable.science_and_technology)
            listIcons.add(com.example.armen.dandur.R.drawable.sports)
            listIcons.add(com.example.armen.dandur.R.drawable.movies_and_tv)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterBottomViewHolder {
        return RecyclerAdapterBottomViewHolder(ScreenRecyclerBottomItem(context))
    }

    override fun getItemCount(): Int {
        return listIcons.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapterBottomViewHolder, position: Int) {

        val item = holder.itemView as ScreenRecyclerBottomItem
        if(itemsList.size != listIcons.size) {
            itemsList.add(item)
        }


        item.configeItem(listIcons.get(position), titles[position])
        if(changeItemPosition != -1 && changeItemPosition == position) {
            setColorItem(position)
        }
        item.setOnClickListener(View.OnClickListener {
            if (changeItemPosition == position) {
                return@OnClickListener
            } else {
                changeItemPosition = position
                item.isClicked = true
                setColorsItems(position, false)
                delegate?.bottomBarItemClick(position)
            }

        })
    }

    fun setColorItem(position: Int) {
        val colorYellow  =  ContextCompat.getColor(context, R.color.colorYellow)

        for (i in 0..listIcons.size-1) {
            if(i == position) {
                val img = ContextCompat.getDrawable(context, listIcons.get(i))
                img?.colorFilter = PorterDuffColorFilter(colorYellow, PorterDuff.Mode.SRC_IN)
            }
        }
    }

    fun setColorsItems(position: Int, isTopClick:Boolean) {
        val colorBlack  = ContextCompat.getColor(context, R.color.colorBlack)
        val colorWhite  = ContextCompat.getColor(context, R.color.colorWhite)
        val colorYellow  =  ContextCompat.getColor(context, R.color.colorYellow)
//        val img = ContextCompat.getDrawable(context, listIcons.get(position))
//
//        img?.colorFilter = PorterDuffColorFilter(colorYellow, PorterDuff.Mode.SRC_IN)
//        item.imgTitle.setTextColor(colorYellow)

            for (i in 0..listIcons.size-1) {
                val img = ContextCompat.getDrawable(context, listIcons.get(i))

                if(i != position) {
                    if(DandurConstants.isDarkBackground) {
                        img?.colorFilter = PorterDuffColorFilter(colorWhite, PorterDuff.Mode.SRC_IN)
                    } else {
                        img?.colorFilter = PorterDuffColorFilter(colorBlack, PorterDuff.Mode.SRC_IN)
                    }
                } else {
                    if(!isTopClick) {
                        img?.colorFilter = PorterDuffColorFilter(colorYellow, PorterDuff.Mode.SRC_IN)
                    } else{
                        if(DandurConstants.isDarkBackground) {
                            img?.colorFilter = PorterDuffColorFilter(colorWhite, PorterDuff.Mode.SRC_IN)
                        } else {
                            img?.colorFilter = PorterDuffColorFilter(colorBlack, PorterDuff.Mode.SRC_IN)
                        }
                    }
                }
            }

        notifyDataSetChanged()
//        while (iterator.hasNext()) {
//            val notClickedItem  = iterator.next()
//            if (!notClickedItem.equals(item)){
//                notClickedItem.isClicked = false
//                for (drawable:Drawable? in notClickedItem.imgTitle.getCompoundDrawables()){
//
//                    if(drawable != null) {
//                        if (DandurConstants.isDarkBackground) {
//                            drawable.colorFilter = PorterDuffColorFilter(colorWhite, PorterDuff.Mode.SRC_IN)
//                            notClickedItem.imgTitle.setTextColor(colorWhite)
//                        } else {
//                            drawable.colorFilter = PorterDuffColorFilter(colorBlack, PorterDuff.Mode.SRC_IN)
//                            notClickedItem.imgTitle.setTextColor(colorBlack)
//                        }
//                    } else {
//                        if (DandurConstants.isDarkBackground) {
//                            notClickedItem.imgTitle.setTextColor(colorWhite)
//                        } else {
//                            notClickedItem.imgTitle.setTextColor(colorBlack)
//                        }
//                    }
//                }
//            } else {
//                for (drawable:Drawable? in item.imgTitle.getCompoundDrawables()){
//                    if(drawable != null) {
//                        drawable.colorFilter = PorterDuffColorFilter(colorYellow, PorterDuff.Mode.SRC_IN)
//                        item.imgTitle.setTextColor(colorYellow)
//                    } else {
//                        item.imgTitle.setTextColor(colorYellow)
//                    }
//                }
//            }
//        }
    }

    class RecyclerAdapterBottomViewHolder(itemView:ScreenRecyclerBottomItem):RecyclerView.ViewHolder(itemView){
        var imgTitle  = itemView.imgTitle
   }
}