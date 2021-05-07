package com.example.mynewsapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapplication.util.showToast
import com.example.newsapp.model.DataModel
import com.example.newsapp.model.SavedNews


class ItemAdapter(private val context: Context, private val dataList: List<DataModel>,
//                  private val onItemClick: (DataModel) -> Unit
                  //private val listener: OnRecyclerViewItemClickListener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

   inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) /*,View.OnClickListener*/ {
//
//        val title: TextView = view.findViewById(R.id.newsTitleTV)
//        val desc: TextView = view.findViewById(R.id.newsDescriptionTV)
//        val image: ImageView = view.findViewById(R.id.imageView)
//        val urlNews: TextView = view.findViewById(R.id.newsURL)
//        val newsTime: TextView = view.findViewById(R.id.publishedTimeTV)

         val image: ImageView = view.findViewById(R.id.newsImageView)
         val title: TextView = view.findViewById(R.id.heading_textView)
         val desc: TextView = view.findViewById(R.id.description_textView)
         val source: TextView = view.findViewById(R.id.source_textView)
         val time: TextView = view.findViewById(R.id.time_textView)
//
//       init {
//           view.setOnClickListener(this)
//       }

//       override fun onClick(v: View?) {
//           val position : Int = adapterPosition
//           if (position != RecyclerView.NO_POSITION){
//               listener.onItemClicked(position , url_adapter = dataList[position].url ,
//                   title_adapter = dataList[position].title , desc_adapter = dataList[position].description ,
//                   time_adapter = dataList[position].time)
//           }
//       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.news_data,parent,false)
        return ViewHolder(inflater)
    }

    private var onItemClickListener: ((DataModel) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
//            title.text = dataList[position].title
//            desc.text = dataList[position].description
//
//            Glide.with(context)
//                .load(dataList[position].image)
//                .placeholder(R.drawable.no_image_available)
//                .into(image)
//
//            urlNews.text = dataList[position].url
//            newsTime.text = dataList[position].time

            title.text = dataList[position].title
            desc.text = dataList[position].description

            Glide.with(context)
                .load(dataList[position].image)
                .placeholder(R.drawable.placeholder_news)
                .into(image)

            source.text = dataList[position].source
            time.text = dataList[position].time

            itemView.setOnClickListener {
                onItemClickListener?.let { it(dataList[position]) }
            }

            image.setOnClickListener {
                val intent = Intent(holder.itemView.context , WebActivity::class.java)
                intent.putExtra("url" , dataList[position].url)
                holder.itemView.context.startActivity(intent)
                //val bundle = Bundle()
//                bundle.putString("url", dataList[position].url)
//                val objects = WebNewsFragment()
//                objects.arguments = bundle
            }
        }
    }

    fun setOnItemClickListener(listener: (DataModel) -> Unit){
        onItemClickListener = listener
    }

//    interface OnRecyclerViewItemClickListener{
//        fun onItemClicked(position: Int )
//    }

    override fun getItemCount(): Int = dataList.size
}