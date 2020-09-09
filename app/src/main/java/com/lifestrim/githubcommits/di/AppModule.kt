package com.lifestrim.githubcommits.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lifestrim.githubcommits.data.RepoRepository
import com.lifestrim.githubcommits.data.local.AppDatabase
import com.lifestrim.githubcommits.data.local.RepoDao
import com.lifestrim.githubcommits.data.remote.RepoRemoteDataSource
import com.lifestrim.githubcommits.data.remote.RepoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRepoService(retrofit: Retrofit): RepoService =
        retrofit.create(RepoService::class.java)

    @Singleton
    @Provides
    fun provideRepoRemoteDataSource(repoService: RepoService) =
        RepoRemoteDataSource(repoService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRepoDao(db: AppDatabase) = db.repoDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RepoRemoteDataSource,
        localDataSource: RepoDao
    ) =
        RepoRepository(remoteDataSource, localDataSource)
}