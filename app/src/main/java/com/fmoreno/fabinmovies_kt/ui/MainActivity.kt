package com.fmoreno.fabinmovies_kt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fmoreno.fabinmovies_kt.R
import com.fmoreno.fabinmovies_kt.interfaces.TabsFragments
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        renderViewPager()
        renderTabLayer()
    }

    private fun renderViewPager() {

        viewpager.adapter = object : FragmentStateAdapter(this) {

            override fun createFragment(position: Int): Fragment {
                return TabsFragments.pagerFragments[position] as Fragment
            }

            override fun getItemCount(): Int {
                return TabsFragments.tabList.size
            }
        }
    }

    private fun renderTabLayer() {
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            tab.text = getString(TabsFragments.tabList[position])
        }.attach()
    }
}