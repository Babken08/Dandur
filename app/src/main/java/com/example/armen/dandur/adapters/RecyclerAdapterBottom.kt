package com.example.armen.dandur.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.armen.dandur.R
import com.example.armen.dandur.http.APIService
import com.example.armen.dandur.http.APIUtil
import com.example.armen.dandur.screen.ScreenRecyclerBottomItem
import com.example.armen.dandur.util.DandurConstants
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback

class RecyclerAdapterBottom(val context: Context):RecyclerView.Adapter<RecyclerAdapterBottom.RecyclerAdapterBottomViewHolder>() {
    var listIcons:ArrayList<Int>? = null
    var apiService :APIService? = null

    init {
        apiService = APIUtil.getAPIService()
        listIcons = ArrayList()
        listIcons!!.add(R.drawable.funny)
        listIcons!!.add(R.drawable.animals_and_pets)
        listIcons!!.add(R.drawable.anime)
        listIcons!!.add(R.drawable.music)
        listIcons!!.add(R.drawable.gaming)
        listIcons!!.add(R.drawable.cartoons)
//        for (i in 0..5) {
//            listIcons?.add(R.drawable.ic_launcher_background)
//        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterBottomViewHolder {
        return RecyclerAdapterBottomViewHolder(ScreenRecyclerBottomItem(context))
    }

    override fun getItemCount(): Int {
        return if (listIcons == null) 0 else listIcons?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerAdapterBottomViewHolder, position: Int) {
        holder.imgIcon.setImageResource(listIcons?.get(position)!!)
        holder.itemView.setOnClickListener(View.OnClickListener {
//            apiService?.getPostsInCategory("31f726b8-e7de-3f9d-4794-611e0a829939", "139")?.enqueue(object : retrofit2.Callback<Any> {
//                override fun onResponse(call: Call<Any>, response: Response<Any>) {
//
//                    if(response.isSuccessful){
//                        Log.i("sssssssssss", "sucsess = trrrrrrrrrruuuuuuuuuuu")
//                    }else {
//                        Log.i("sssssssssss", "sucsess = falsee eeeeeeeeeeee")
//
//                    }
//                }
//                override fun onFailure(call: Call<Any>, t: Throwable) {
//                    Log.i("sssssssssss", "failure: errror message  = " + t.message)
//
//                }
//            })
        })
    }

    class RecyclerAdapterBottomViewHolder(itemView:ScreenRecyclerBottomItem):RecyclerView.ViewHolder(itemView){
        var imgIcon  = itemView.imgIcon
   }
}