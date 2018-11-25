package com.example.komuroshun.androidqitaclient.ui


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.komuroshun.androidqitaclient.R
import kotlinx.android.synthetic.main.fragment_article_tab.*

class ArticleTabFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments!!.getInt("position")
        val color = Color.HSVToColor(floatArrayOf((30 * position).toFloat(), 0.1f, 0.9f))
        view.setBackgroundColor(color)
        testPositionNumber.text = "Here is page $position"
    }

    companion object {
        fun getInstance(position: Int): ArticleTabFragment {
            val articleTabFragment = ArticleTabFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            articleTabFragment.arguments = bundle
            return articleTabFragment
        }
    }
}
