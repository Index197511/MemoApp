package com.index197511.memo.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MemoRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repository: MemoRepository): IMemoRepository
}