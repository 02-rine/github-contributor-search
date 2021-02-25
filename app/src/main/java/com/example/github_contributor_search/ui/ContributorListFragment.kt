package com.example.github_contributor_search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.example.github_contributor_search.R
import com.example.github_contributor_search.adapter.ContributorListAdapter
import com.example.github_contributor_search.callback.ContributorClickCallback
import com.example.github_contributor_search.databinding.FragmentContributorListBinding
import com.example.github_contributor_search.service.Contributor
import com.example.github_contributor_search.viewModel.ContributorListViewModel

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class ContributorListFragment : Fragment() {

    private val viewModel: ContributorListViewModel by viewModels()

    private lateinit var binding: FragmentContributorListBinding

    private val contributorListAdapter = ContributorListAdapter(object : ContributorClickCallback {
        override fun onClick(contributor: Contributor) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(contributor)
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_contributor_list, container, false)
        binding.apply {
            contributorList.adapter = contributorListAdapter
            isLoading = true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.contributorList.observe(viewLifecycleOwner, { projects ->
            projects?.let {
                binding.isLoading = false
                contributorListAdapter.setContributorList(it)
            }
        })
    }
}