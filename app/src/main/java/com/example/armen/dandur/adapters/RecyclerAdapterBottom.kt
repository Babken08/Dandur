package com.example.armen.dandur.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.armen.dandur.R
import com.example.armen.dandur.http.APIService
import com.example.armen.dandur.http.APIUtil
import com.example.armen.dandur.screen.ScreenRecyclerBottomItem
import java.util.*

class RecyclerAdapterBottom(val context: Context):RecyclerView.Adapter<RecyclerAdapterBottom.RecyclerAdapterBottomViewHolder>() {
    interface RecyclerAdapterBottomDelegate{
        fun bottomBarItemClick(position:Int)
    }
    var delegate : RecyclerAdapterBottomDelegate? = null
    lateinit var listIcons:ArrayList<Int>
    var apiService :APIService? = null
    lateinit var titles:Array<String>

    init {
        apiService = APIUtil.getAPIService()
        listIcons = ArrayList()
        titles = context.resources.getStringArray(R.array.bottom_bar_titles)
        listIcons.add(R.drawable.funny)
        listIcons.add(R.drawable.animals_and_pets)
        listIcons.add(R.drawable.anime)
        listIcons.add(R.drawable.music)
        listIcons.add(R.drawable.gaming)
        listIcons.add(R.drawable.cartoons)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterBottomViewHolder {
        return RecyclerAdapterBottomViewHolder(ScreenRecyclerBottomItem(context))
    }

    override fun getItemCount(): Int {
        return listIcons.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapterBottomViewHolder, position: Int) {
        val item = holder.itemView as ScreenRecyclerBottomItem
        item.configeItem(listIcons.get(position), titles[position])
        item.setOnClickListener(View.OnClickListener {
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

            delegate?.bottomBarItemClick(position)
        })
    }

    class RecyclerAdapterBottomViewHolder(itemView:ScreenRecyclerBottomItem):RecyclerView.ViewHolder(itemView){
        var imgTitle  = itemView.imgTitle
   }
}