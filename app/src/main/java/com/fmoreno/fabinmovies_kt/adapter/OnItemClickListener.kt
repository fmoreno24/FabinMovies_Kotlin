package com.fmoreno.fabinmovies_kt.adapter

import android.view.View
import com.fmoreno.fabinmovies_kt.model.Movie

interface OnItemClickListener {
    fun onItemClick(user: Movie?, view: View)
}