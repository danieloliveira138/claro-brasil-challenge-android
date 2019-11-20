package com.danieloliveira.clarochallenge.di

import androidx.recyclerview.widget.GridLayoutManager
import com.danieloliveira.clarochallenge.source.Repository
import com.danieloliveira.clarochallenge.source.RepositoryImpl
import com.danieloliveira.clarochallenge.source.prefs.SharedPrefs
import com.danieloliveira.clarochallenge.source.remote.RetrofitConfig
import com.danieloliveira.clarochallenge.ui.adapters.MovieListAdapter
import com.danieloliveira.clarochallenge.viewmodel.DetailViewModel
import com.danieloliveira.clarochallenge.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single<Repository> { RepositoryImpl(get(), get()) }

    single { RetrofitConfig().service() }

    single { SharedPrefs(get()) }

    factory { GridLayoutManager( get(), 2) }

    factory { (clickMovie: (Int) -> Unit) -> MovieListAdapter(clickMovie) }

}

val viewModelModule: Module = module {

    viewModel { MainActivityViewModel(get()) }

    viewModel { DetailViewModel(get()) }

}