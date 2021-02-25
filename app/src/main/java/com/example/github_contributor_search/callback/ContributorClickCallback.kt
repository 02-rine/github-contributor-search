package com.example.github_contributor_search.callback

import com.example.github_contributor_search.service.Contributor

interface ContributorClickCallback {
    fun onClick(contributor: Contributor)
}