package com.example.github_contributor_search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.github_contributor_search.R
import com.example.github_contributor_search.databinding.FragmentContributorDetailBinding
import com.example.github_contributor_search.viewModel.ContributorDetailViewModel

// Contributorの詳細を表示する画面
class ContributorDetailFragment : Fragment() {

    private val viewModel: ContributorDetailViewModel by viewModels()

    private lateinit var binding: FragmentContributorDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // DataBindingの設定
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_contributor_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Login名を受け取る
        val loginName = arguments?.getString("LOGIN_NAME")

        // Login名をviewModelに渡し、Contributorの詳細を受け取る
        loginName?.let { viewModel.loadContributorDetail(it) }

        // GithubAPIからデータを受け取っている間、ロード中と表示
        binding.isLoading = true

        // GithubAPIからContributorの詳細を受け取った時の処理
        viewModel.contributorDetail.observe(viewLifecycleOwner, {
            it?.let {
                binding.isLoading = false // Contributorの詳細を表示する
                binding.contributorDetail = it // DataBindingにContributorの詳細をセットする
            }
        })
    }
}