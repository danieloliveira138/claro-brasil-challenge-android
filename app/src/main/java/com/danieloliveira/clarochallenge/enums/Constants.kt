package com.danieloliveira.clarochallenge.enums

enum class StringContants(val const: String) {
    MOVIE_ID("movie_id"),
    MOVIE_VIDEO_ID("video_id"),
    TOP_RATED("top_rated"),
    UPCOMING("upcoming"),
    POPULAR("popular"),
    PT_BR("pt-BR"),
}

enum class SharedContants {
    PREF_NAME,
    LANGUAGE_KEY,
    ADULT_CONTENT_KEY,
    TYPE_SEARCH
}

enum class IntegerConstants(val const: Int) {
    PREF_MODE(0)
}