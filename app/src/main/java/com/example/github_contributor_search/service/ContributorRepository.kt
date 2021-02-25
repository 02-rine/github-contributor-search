package com.example.github_contributor_search.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContributorRepository {

    // 受け取ったJSON形式のデータをGsonにより、パースする
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://api.github.com/")
        .build()

    private var githubService: GithubService = retrofit.create(GithubService::class.java)

    //APIにリクエストし、レスポンスをコルーチンで受け取る(一覧)
    suspend fun getContributorsList(): Response<List<Contributor>> =
        githubService.getContributorsList()

    //singletonでRepositoryインスタンスを返すFactory
    companion object Factory {

        val instance: ContributorRepository
            @Synchronized get() {
                return ContributorRepository()
            }
    }
}