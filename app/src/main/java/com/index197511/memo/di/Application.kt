package com.index197511.memo.di

import android.app.Application
import com.index197511.memo.addmemo.AddMemoFragmentViewModel
import com.index197511.memo.home.HomeFragmentViewModel
import com.index197511.memo.memopage.MemoContentFragmentViewModel
import com.index197511.memo.repository.MemoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Application : Application() {
    private val module = module {
        single<MemoRepository> { MemoRepository.getInstance(applicationContext) }
        viewModel { AddMemoFragmentViewModel(get()) }
        viewModel { HomeFragmentViewModel(get()) }
        viewModel { MemoContentFragmentViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }
}