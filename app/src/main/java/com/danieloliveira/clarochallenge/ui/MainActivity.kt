package com.danieloliveira.clarochallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import com.danieloliveira.clarochallenge.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_view.*

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioOptions.setOnCheckedChangeListener(this)

        setupSearchBar()
    }

    private fun setupSearchBar() {
        searchBar.attachNavigationDrawerToMenuButton(drawerLayout)
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

    }
}
