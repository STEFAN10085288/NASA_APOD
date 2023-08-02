package com.example.nasa_apod

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ApodAdapter(private var apodList: List<APOD>) :
    RecyclerView.Adapter<ApodAdapter.ApodViewHolder>() {

    fun updateData(newData: List<APOD>) {
        apodList = newData
        // Notify data changes on the main (UI) thread
        Handler(Looper.getMainLooper()).post {
            notifyDataSetChanged()
        }
    }

    class ApodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val imageView: ImageView = itemView.findViewById(R.id.APOD_image)*/
        val titleTextView: TextView = itemView.findViewById(R.id.APOD_title)
        val dateTextView: TextView = itemView.findViewById(R.id.APOD_date)
        val explanationTextView: TextView = itemView.findViewById(R.id.APOD_Explanation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_apod, parent, false)
        return ApodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        val currentApod = apodList[position]
        holder.titleTextView.text = currentApod.title
        holder.dateTextView.text = currentApod.date
        holder.explanationTextView.text = currentApod.explanation

        /*Glide.with(holder.itemView)
            .load(currentApod.url)
            .into(holder.imageView)*/
    }

    override fun getItemCount() :Int{
        return apodList.size
    }
}
