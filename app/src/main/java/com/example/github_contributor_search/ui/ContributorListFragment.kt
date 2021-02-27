package com.example.github_contributor_search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.github_contributor_search.R
import com.example.github_contributor_search.adapter.ContributorListAdapter
import com.example.github_contributor_search.callback.ContributorClickCallback
import com.example.github_contributor_search.databinding.FragmentContributorListBinding
import com.example.github_contributor_search.service.Contributor
import com.example.github_contributor_search.viewModel.ContributorListViewModel

const val TAG_CONTRIBUTOR_LIST: String = "contributor_list"

// Contributorの一覧表示する画面
class ContributorListFragment : Fragment() {

    private val viewModel: ContributorListViewModel by viewModels()

    private lateinit var binding: FragmentContributorListBinding

    private val contributorListAdapter = ContributorListAdapter(object : ContributorClickCallback {
        // リストのItemをクリック時の処理
        override fun onClick(contributor: Contributor) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                // Contributorの詳細を表示する画面に切り替える
                (activity as MainActivity).showContributorDetail(contributor)
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // DataBindingの設定
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contributor_list, container, false)
        binding.apply {
            contributorList.adapter = contributorListAdapter
            isLoading = true // GithubAPIからデータを受け取っている間、ロード中と表示
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // GithubAPIからContributorの一覧を受け取った時の処理
        viewModel.contributorList.observe(viewLifecycleOwner, {
            it?.let {
                binding.isLoading = false // GithubAPIからデータを受け取った後、リストを表示
                contributorListAdapter.setContributorList(it) // Contributorの一覧をリストとして表示する
            }
        })
    }
}