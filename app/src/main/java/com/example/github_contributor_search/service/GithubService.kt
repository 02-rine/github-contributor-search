package com.example.github_contributor_search.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit2の検索クエリの設定
interface GithubService {

    // Contributorの一覧を受け取る
    @GET("repositories/90792131/contributors")
    suspend fun getContributorsList(): Response<List<Contributor>>

    // Contributorの詳細を受け取る
    @GET("users/{loginName}")
    suspend fun getContributorDetail(
        @Path("loginName") loginName: String
    ): Response<ContributorDetail>
}
