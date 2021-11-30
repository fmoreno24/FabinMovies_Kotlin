package com.fmoreno.fabinmovies_kt.adapter

import android.widget.Filter
import android.widget.Filter.FilterResults
import com.fmoreno.fabinmovies_kt.model.Movie
import java.util.ArrayList

class MovieFilter(myAdapter: RecyclerViewAdapter, originalList: MutableList<Movie> ) : Filter() {
    private var recyclerViewAdapter: RecyclerViewAdapter? = myAdapter
    private var originalList: List<Movie>? = originalList
    private var filteredList: MutableList<Movie>? = listOf<Movie>().toMutableList()

    fun PopularFilter(myAdapter: RecyclerViewAdapter?, originalList: List<Movie>?) {
        recyclerViewAdapter = myAdapter
        this.originalList = originalList
        filteredList = ArrayList<Movie>()
    }

    override fun performFiltering(charSequence: CharSequence): FilterResults? {
        filteredList!!.clear()
        val results = FilterResults()
        if (charSequence.length == 0) {
            filteredList?.addAll(originalList!!)
        } else {
            val filterPattern = charSequence.toString().toLowerCase().trim { it <= ' ' }
            for (movie in originalList!!) {
                if (movie.title?.lowercase()!!.contains(filterPattern)) {
                    filteredList?.add(movie)
                }
            }
        }
        results.values = filteredList
        results.count = filteredList?.size!!
        return results
    }

    override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
        recyclerViewAdapter?.mMovies?.clear()
        var movies:ArrayList<Movie> = filterResults.values as ArrayList<Movie>
        if(movies!= null && movies.size > 0)
        for (movie in movies){
            recyclerViewAdapter?.mMovies?.add(movie)
        }
        //recyclerViewAdapter?.mMovies?.addAll(filterResults.values as MutableList<Movie?>)
        recyclerViewAdapter?.notifyDataSetChanged()
    }
}