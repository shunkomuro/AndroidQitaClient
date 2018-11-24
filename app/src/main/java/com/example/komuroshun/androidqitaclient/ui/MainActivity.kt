package com.example.komuroshun.androidqitaclient.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.komuroshun.androidqitaclient.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, ArticleTabsFragment())
                .commit()
    }
}
