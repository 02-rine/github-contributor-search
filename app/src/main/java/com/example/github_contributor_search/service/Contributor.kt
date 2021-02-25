package com.example.github_contributor_search.service

import java.io.Serializable

data class Contributor(
    val login: String,
    val id: String,
) : Serializable
