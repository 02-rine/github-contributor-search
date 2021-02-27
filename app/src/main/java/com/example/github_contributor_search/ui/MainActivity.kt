package com.example.github_contributor_search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github_contributor_search.R
import com.example.github_contributor_search.service.Contributor

// 表示するFragmentを切り替える
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Contributorのリストを表示
            val contributorListFragment = ContributorListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, contributorListFragment, TAG_CONTRIBUTOR_LIST)
                .commit()
        }
    }

    // Contributorの詳細を表示
    fun showContributorDetail(contributor: Contributor) {
        val bundle = Bundle()
        // contributorのLogin名を渡す
        bundle.putString("LOGIN_NAME", contributor.login)

        val contributorDetailFragment = ContributorDetailFragment()
        contributorDetailFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, contributorDetailFragment)
            .commit()
    }
}