package com.index197511.memo.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMemoDatabase(memoApp: Application): MemoDatabase {
        return Room.databaseBuilder(memoApp, MemoDatabase::class.java, "memo-database")
            .build()
    }

    @Provides
    @Singleton
    fun provideMemoDatabaseDao(database: MemoDatabase): MemoDatabaseDao {
        return database.memoDatabaseDao()
    }
}