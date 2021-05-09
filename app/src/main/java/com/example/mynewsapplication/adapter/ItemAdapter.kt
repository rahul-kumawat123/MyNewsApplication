package com.example.mynewsapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapplication.R
import com.example.mynewsapplication.activity.WebActivity
import com.example.mynewsapplication.showToast
import com.example.newsapp.model.DataModel


class ItemAdapter(private val context: Context, private val dataList: List<DataModel>,
                  private val onItemClick: (DataModel) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

   inner class ViewHolder(view : View): RecyclerView.ViewHolder(view){

       //assigning variables to views
         val image: ImageView = view.findViewById(R.id.newsImageView)
         val title: TextView = view.findViewById(R.id.heading_textView)
         val desc: TextView = view.findViewById(R.id.description_textView)
         val source: TextView = view.findViewById(R.id.source_textView)
         val time: TextView = view.findViewById(R.id.time_textView)
        val bookmark: ImageView = view.findViewById(R.id.bookmarksImageView)

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflating layout to adapter
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.news_data,parent,false)
        return ViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = dataList[position].title
            desc.text = dataList[position].description

            Glide.with(context)
                .load(dataList[position].image)
                .placeholder(R.drawable.placeholder_news)
                .into(image)

            source.text = dataList[position].source
            time.text = dataList[position].time

            //assigning images to BookmarkImageview whenever data item is added or removed from database
            if (dataList[position].isFav){
              holder.bookmark.setImageResource(R.drawable.ic_bookmark_added)
            }else
            {
                holder.bookmark.setImageResource(R.drawable.ic_not_bookmarked)
            }

            //opening webView upon clicking of image of a news item
            image.setOnClickListener {
                val intent = Intent(holder.itemView.context , WebActivity::class.java)
                intent.putExtra("url" , dataList[position].url)
                holder.itemView.context.startActivity(intent)
            }

            holder.bookmark.setOnClickListener {
                dataList[position].isFav = !dataList[position].isFav
                onItemClick(dataList[position])
                notifyDataSetChanged()

                if (dataList[position].isFav){
                    context.showToast("Bookmark Added" )
                }else{
                    context.showToast("Bookmark Removed")
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}