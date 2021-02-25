package com.example.github_contributor_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.github_contributor_search.R
import com.example.github_contributor_search.callback.ContributorClickCallback
import com.example.github_contributor_search.databinding.ContributorListItemBinding
import com.example.github_contributor_search.service.Contributor


class ContributorListAdapter(private val contributorClickCallback: ContributorClickCallback?) :
    RecyclerView.Adapter<ContributorListAdapter.ContributorViewHolder>() {

    private var contributorList: List<Contributor>? = null

    fun setContributorList(contributorList: List<Contributor>) {

        if (this.contributorList == null) {
            this.contributorList = contributorList
            notifyItemRangeInserted(0, contributorList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@ContributorListAdapter.contributorList).size
                }

                override fun getNewListSize(): Int {
                    return contributorList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldList = this@ContributorListAdapter.contributorList
                    return oldList?.get(oldItemPosition)?.id == contributorList[newItemPosition].id
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val contributor = contributorList[newItemPosition]
                    val old = contributorList[oldItemPosition]
                    return contributor.id == old.id
                }
            })
            this.contributorList = contributorList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ContributorViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.contributor_list_item, parent,
            false
        ) as ContributorListItemBinding
        binding.callback = contributorClickCallback
        return ContributorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.binding.contributor = contributorList?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return contributorList?.size ?: 0
    }

    open class ContributorViewHolder(val binding: ContributorListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}