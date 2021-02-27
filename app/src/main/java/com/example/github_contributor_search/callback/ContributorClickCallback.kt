package com.example.github_contributor_search.callback

import com.example.github_contributor_search.service.Contributor

// Contributorの一覧をクリックした時に動作する
interface ContributorClickCallback {
    fun onClick(contributor: Contributor)
}