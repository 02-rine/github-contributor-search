package com.example.github_contributor_search.service

import retrofit2.Response
import retrofit2.http.GET

interface GithubService {

    @GET("repositories/90792131/contributors")
    suspend fun getContributorsList(): Response<List<Contributor>>
}