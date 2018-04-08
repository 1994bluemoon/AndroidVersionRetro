package vinova.henry.com.androidversionretro.activity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_android_version.view.*
import vinova.henry.com.androidversionretro.R
import vinova.henry.com.androidversionretro.R.id.*
import vinova.henry.com.androidversionretro.model.AndroidVersion

class AndroidVersionAdapter(var context: Context, var androidVersions: List<AndroidVersion>) : RecyclerView.Adapter<AndroidVersionAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(androidVersions!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_android_version, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return androidVersions!!.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(android: AndroidVersion) {
            itemView.tv_name.text = android.name
            itemView.tv_version.text = android.ver
            itemView.tv_api_level.text = android.api
        }
    }
}

