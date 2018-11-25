package com.example.komuroshun.androidqitaclient.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView

import com.example.komuroshun.androidqitaclient.R
import kotlinx.android.synthetic.main.fragment_article_tabs.*
import kotlinx.android.synthetic.main.tab_track.*

class ArticleTabsFragment : Fragment() {

    private var mIndicatorOffset: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view
                = inflater.inflate(R.layout.fragment_article_tabs, container, false)

        val density = resources.displayMetrics.density
        mIndicatorOffset = (INDICATOR_OFFSET * density).toInt()

        val pager = view.findViewById<ViewPager>(R.id.articleViewPager)
        val articleViewPagerAdapter = ArticleViewPagerAdapter(childFragmentManager)
        pager.adapter = articleViewPagerAdapter
        pager.addOnPageChangeListener(PageChangeListener())

        var tabTrack = view.findViewById<ViewGroup>(R.id.tabTrack)
        val layoutInfater = LayoutInflater.from(activity)
        for (i in 0 until articleViewPagerAdapter.count) {
            val tv = layoutInfater.inflate(R.layout.tab_track_item,
                    tabTrack, false) as TextView
            tv.text = articleViewPagerAdapter.getPageTitle(i)
            tv.setOnClickListener { articleViewPager.currentItem = i }
            tabTrack.addView(tv)
        }

        return view
    }

    private inner class PageChangeListener: ViewPager.OnPageChangeListener {
        private var mScrollingState = ViewPager.SCROLL_STATE_IDLE

        override fun onPageSelected(position: Int) {
            if (mScrollingState == ViewPager.SCROLL_STATE_IDLE) {
                android.util.Log.d("MYTEST", "onPageSelected")
                updateIndicatorPosition(position, 0f)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            android.util.Log.d("MYTEST", "onPageScrollStateChanged")
            mScrollingState = state
        }

        override fun onPageScrolled(position: Int, positionOffset: Float,
                                    positionOffsetPixels: Int) {
            android.util.Log.d("MYTEST", "onPageScrolled")
            updateIndicatorPosition(position, positionOffset)
        }

        private fun updateIndicatorPosition(position: Int, positionOffset: Float) {

            // 現在位置のタブの View
            val view = tabTrack.getChildAt(position)
            // 現在位置のタブの次のタブの View
            val nextView =
                    if (position == tabTrack.childCount - 1) {
                        null
                    } else {
                        tabTrack.getChildAt(position + 1)
                    }

            val nextViewWidth = nextView?.width ?: view.width

            // インディケータの幅
            val indicatorWidth = (nextViewWidth * positionOffset
                    + view.width * (1 - positionOffset)).toInt()
            // インディケータの左端の位置
            val indicatorLeft = (view.left + positionOffset * view.width).toInt()
            // インディケータの幅と左端の位置をセット
            val layoutParams =
                    tabIndicator.layoutParams as FrameLayout.LayoutParams
            layoutParams.width = indicatorWidth
            layoutParams.setMargins(indicatorLeft, 0, 0, 0)
            tabIndicator.layoutParams = layoutParams

            // タブ部分のスクローラー
            // インディケータが画面に入るように、タブの領域をスクロール
            tabTrackScroller.scrollTo(indicatorLeft - mIndicatorOffset, 0)
        }
    }

    companion object {
        // インディケータのオフセット
        // TODO 調整する
        private val INDICATOR_OFFSET = 130 // 48dp
    }

}