package com.cuncisboss.moneymanager.di

import com.cuncisboss.moneymanager.repository.SpendingRepository
import com.cuncisboss.moneymanager.viewmodel.SpendingViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    factory { SpendingRepository(androidApplication()) }
    viewModel { SpendingViewModel(get()) }
}