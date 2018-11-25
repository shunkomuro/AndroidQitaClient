package com.example.komuroshun.androidqitaclient.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ArticleViewPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        // TODO 毎回作り直さないように
        return ArticleTabFragment.getInstance(position)
    }

    override fun getCount(): Int {
        return sTabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return sTabs[position]
    }

    companion object {
        // TODO 動的に変更
        // タブの項目
        private val sTabs =
                arrayOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus",
                        "Neptune", "Pluto")
    }
}