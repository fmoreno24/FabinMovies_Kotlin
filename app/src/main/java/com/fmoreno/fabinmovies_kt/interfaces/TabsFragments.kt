package com.fmoreno.fabinmovies_kt.interfaces

import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.ui.fragments.PopularFragment
import com.fmoreno.fabinmovies_kt.ui.fragments.TopRatedFragment

interface TabsFragments {
    companion object {
        val tabList = listOf(
            R.string.tabPopular, R.string.tabTopRated
        )
        val pagerFragments = listOf(
            PopularFragment.create(), TopRatedFragment.create())
    }
}