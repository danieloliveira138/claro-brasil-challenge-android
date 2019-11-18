package com.danieloliveira.clarochallenge.di

import com.danieloliveira.clarochallenge.source.Repository
import com.danieloliveira.clarochallenge.source.RepositoryImpl
import com.danieloliveira.clarochallenge.source.remote.RetrofitConfig
import com.danieloliveira.clarochallenge.viewmodel.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    single<Repository> { RepositoryImpl(get()) }

    single { RetrofitConfig().service() }
}

val viewModelModule: Module = module {

    viewModel { MovieListViewModel() }

}