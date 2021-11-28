package com.fmoreno.fabinmovies_kt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fmoreno.fabinmovies_kt.R

class TopRatedFragment: Fragment(){
    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toprated, container)
    }

    companion object {
        fun create(): TopRatedFragment {
            return TopRatedFragment()
        }
    }
}