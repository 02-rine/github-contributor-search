package com.example.github_contributor_search.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_contributor_search.service.Contributor
import com.example.github_contributor_search.service.ContributorRepository
import kotlinx.coroutines.launch

// GithubAPIから受け取ったContributorの一覧を管理する
class ContributorListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContributorRepository.instance

    // Contributorの一覧（監視対象）
    private var _contributorList: MutableLiveData<List<Contributor>> = MutableLiveData()
    val contributorList: LiveData<List<Contributor>> = _contributorList

    init {
        // 初期化時に実行し、結果をリストとして表示する
        loadContributorList()
    }

    // GithubAPIからContributorの一覧を受け取る
    private fun loadContributorList() {
        viewModelScope.launch {
            val request = repository.getContributorsList()
            if (request.isSuccessful) {
                // データを取得したら、contributorsListを更新
                _contributorList.postValue(request.body())
            }
        }
    }
}