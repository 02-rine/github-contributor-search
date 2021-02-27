package com.example.github_contributor_search.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// GithubAPIからデータを取得するために、Retrofit2の設定を行う
class ContributorRepository {

    // 受け取ったJSON形式のデータをGsonを用いて、パースする
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("https://api.github.com/")
        .build()
    private var githubService: GithubService = retrofit.create(GithubService::class.java)

    // GithubAPIからContributorの一覧を受け取る
    suspend fun getContributorsList(): Response<List<Contributor>> =
        githubService.getContributorsList()

    // GithubAPIからContributorの詳細を受け取る
    suspend fun getContributorDetail(loginName: String): Response<ContributorDetail> =
        githubService.getContributorDetail(loginName)

    // singletonでContributorRepositoryインスタンスを返すFactory
    companion object Factory {
        val instance: ContributorRepository
            @Synchronized get() {
                return ContributorRepository()
            }
    }
}