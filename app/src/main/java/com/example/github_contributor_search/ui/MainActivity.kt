package com.example.github_contributor_search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github_contributor_search.R
import com.example.github_contributor_search.service.Contributor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ContributorListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, TAG_OF_PROJECT_LIST_FRAGMENT)
                .commit()
        }
    }

    fun show(contributor: Contributor) {
    }
}