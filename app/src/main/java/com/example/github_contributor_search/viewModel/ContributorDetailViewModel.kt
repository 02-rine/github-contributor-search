package com.example.github_contributor_search.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.github_contributor_search.service.ContributorDetail
import com.example.github_contributor_search.service.ContributorRepository
import kotlinx.coroutines.launch

// GithubAPIから受け取ったContributorの詳細を管理する
class ContributorDetailViewModel(application: Application) :
    AndroidViewModel(application) {

    private val repository = ContributorRepository.instance

    // Contributorの詳細（監視対象）
    private var _contributorDetail: MutableLiveData<ContributorDetail> = MutableLiveData()
    val contributorDetail: LiveData<ContributorDetail> = _contributorDetail

    // GithubAPIからContributorの詳細を受け取る
    fun loadContributorDetail(loginName: String) {
        viewModelScope.launch {
            val request = repository.getContributorDetail(loginName)
            if (request.isSuccessful) {
                // データを取得したら、_contributorDetailを更新
                _contributorDetail.postValue(request.body())
            }
        }
    }
}