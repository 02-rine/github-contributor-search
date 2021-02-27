package com.example.github_contributor_search.service

import java.io.Serializable

// GithubAPIから受け取るContributorの一覧
data class Contributor(
    val login: String,          // ContributorのLogin名
    val contributions: String,  // Contributionの数
) : Serializable