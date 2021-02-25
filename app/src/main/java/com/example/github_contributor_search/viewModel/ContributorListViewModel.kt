package com.example.github_contributor_search.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.github_contributor_search.service.Contributor
import com.example.github_contributor_search.service.ContributorRepository
import kotlinx.coroutines.launch

class ContributorListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContributorRepository.instance

    // Contributorのリスト（監視対象）
    private var _contributorList: MutableLiveData<List<Contributor>> = MutableLiveData()
    val contributorList: LiveData<List<Contributor>> = _contributorList

    init {
        loadContributorsList()
    }

    private fun loadContributorsList() {
        viewModelScope.launch {
            try {
                val request = repository.getContributorsList()
                if (request.isSuccessful) {
                    Log.i("sample", request.body().toString())

                    // データを取得したら、contributorsListを更新
                    _contributorList.postValue(request.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}