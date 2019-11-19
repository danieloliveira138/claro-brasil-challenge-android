package com.danieloliveira.clarochallenge.enums

enum class PosterImages {

    W92{
        override fun getImageUrl(imgSrc: String?): String =
            if (imgSrc.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/w92/$imgSrc"
    },
    W200{
        override fun getImageUrl(imgSrc: String?): String =
            if (imgSrc.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/w200/$imgSrc"
    },
    W500 {
        override fun getImageUrl(imgSrc: String?) =
            if (imgSrc.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/w500/$imgSrc"
    },
    W300 {
        override fun getImageUrl(imgSrc: String?): String =
            if (imgSrc.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/w300/$imgSrc"
    },
    W1280{
        override fun getImageUrl(imgSrc: String?): String =
            if (imgSrc.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/w1280/$imgSrc"
    },
    ORIGINAL{
        override fun getImageUrl(imgSrc: String?): String =
            if (imgSrc.isNullOrEmpty()) "" else "https://image.tmdb.org/t/p/original/$imgSrc"
    };

    abstract fun getImageUrl(imgSrc: String?): String
}