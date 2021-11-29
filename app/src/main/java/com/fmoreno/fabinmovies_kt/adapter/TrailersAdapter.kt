package com.fmoreno.fabinmovies_kt.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.internet.WebServicesConstant.Constant.YOUTUBE_WEB_URL
import com.fmoreno.fabinmovies_kt.model.DetailMovie

class TrailersAdapter(videos: MutableList<DetailMovie.Video>): RecyclerView.Adapter<TrailersAdapter.ViewHolder>() {
    var mContext: Context? = null

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        var card_trailer: CardView
        var image_trailer: ImageView
        var trailer_name: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            card_trailer = itemView.findViewById(R.id.card_trailer)
            image_trailer = itemView.findViewById(R.id.image_trailer)
            trailer_name = itemView.findViewById(R.id.trailer_name)
        }
    }

    // Store a member variable for the contacts
    private var mVideos: MutableList<DetailMovie.Video>? = videos


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        mContext = context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView: View = inflater.inflate(R.layout.item_trailer, parent, false)

        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val startAnimation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_up)
        // Get the data model based on position
        val video: DetailMovie.Video = mVideos!![position]
        val card_trailer = holder.card_trailer
        card_trailer.setOnClickListener { onClickYoutube(video, holder.itemView.context) }

        // Set item views based on your views and data model
        val image_trailer = holder.image_trailer
        val trailer_name = holder.trailer_name
        val thumbnail = "https://img.youtube.com/vi/" + video.key.toString() + "/hqdefault.jpg"
        Glide.with(holder.itemView.context)
            .load(thumbnail)
            .into(holder.image_trailer)
        trailer_name.setText(video.name)
        holder.itemView.startAnimation(startAnimation)
    }

    fun addMovies(videos: List<DetailMovie.Video>?) {
        mVideos?.addAll(videos!!)
        //Log.e(TAG, "size of movie list==" + moviesList.size());
        notifyDataSetChanged()
    }

    fun submitList(videos: MutableList<DetailMovie.Video>?) {
        mVideos = videos
        notifyDataSetChanged()
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mVideos!!.size
    }

    private fun onClickYoutube(video: DetailMovie.Video, context: Context) {
        val appIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("vnd.youtube:" + video.key)
        )
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(YOUTUBE_WEB_URL + video.key)
        )
        if (appIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(appIntent)
        } else {
            context.startActivity(webIntent)
        }
    }
}