package com.example.github_contributor_search.service

import java.io.Serializable

// GithubAPIから受け取るContributorの詳細
data class ContributorDetail(
    val login: String,          // Login名
    val company: String?,       // 会社名
    val blog: String?,          // ブログ（URL)
    val location: String?,      // 所在地
    val email: String?,         // email
    val public_repos: String?,  // 公開リポジトリ数
    val followers: String?,     // フォロワー数
    val following: String?,     // フォロー数
) : Serializable