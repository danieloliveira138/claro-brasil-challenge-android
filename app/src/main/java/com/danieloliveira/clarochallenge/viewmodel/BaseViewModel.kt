package com.danieloliveira.clarochallenge.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel() {
    private val job = Job()
    private val coroutineContext : CoroutineContext get() = job + Dispatchers.Default
    val scope = CoroutineScope(coroutineContext)
}