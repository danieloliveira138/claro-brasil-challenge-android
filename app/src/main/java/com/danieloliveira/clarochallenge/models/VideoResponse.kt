package com.danieloliveira.clarochallenge.models

data class VideoResponse(
    var id: Int = 0,
    val results: List<Video> = listOf()
)