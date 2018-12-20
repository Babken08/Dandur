package com.example.armen.dandur.adapters

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import com.example.armen.dandur.R
import com.google.gson.internal.LinkedTreeMap
import java.util.ArrayList
import java.util.zip.Inflater

class ResyclerAdapter(val context:Context) : RecyclerView.Adapter<ResyclerAdapter.RescyclerAdapterViewHolder>(){

    var data: ArrayList<LinkedTreeMap<String, Any>>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var itemPosition:Int = 0
    var hd:RescyclerAdapterViewHolder? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RescyclerAdapterViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_item, parent, false)
        return RescyclerAdapterViewHolder(view)
    }


    override fun getItemCount(): Int {
        return if(data == null) 0 else data?.size!!
    }

    override fun onBindViewHolder(holder: RescyclerAdapterViewHolder, position: Int) {
        hd = holder
        itemPosition = position
    }

    fun getPosition():Int{
        return itemPosition
    }
    fun playVideo(position:Int) {
        if(data != null) {

            if(hd != null) {
                    val ob  = data?.get(position)
                    val mp4_url:Any? = ob?.get("mp4_url")
                    val videoerl = mp4_url.toString()
                    val uri = Uri.parse(videoerl)
                    hd?.video_view?.setVideoURI(uri)
                    hd?.video_view?.start()

                 }
            }

    }

    fun stopVideo() {
        hd?.video_view?.stopPlayback()
    }
    class RescyclerAdapterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var video_view :VideoView = itemView.findViewById(R.id.video_view)
        init {
            video_view.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus){

                }else {

                }
            }
        }
    }
}