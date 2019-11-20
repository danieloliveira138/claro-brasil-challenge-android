package com.danieloliveira.clarochallenge.callbacks

interface CallBacks {

    fun callbackObserver(obj: Any)

    interface playerCallBack {
        fun onItemClickOnItem(albumId: Int?)

        fun onPlayingEnd()
    }
}