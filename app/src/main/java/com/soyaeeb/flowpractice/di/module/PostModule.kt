package com.soyaeeb.flowpractice.di.module
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.soyaeeb.flowpractice.service.PostApi
import com.soyaeeb.flowpractice.ui.PostRepository
import com.soyaeeb.flowpractice.ui.PostViewModel
import com.soyaeeb.flowpractice.utility.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PostModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providePostService(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Singleton
    @Provides
    fun providePostRepository(postApi: PostApi) : PostRepository{
        return PostRepository(postApi)
    }

    @Singleton
    @Provides
    fun providePostViewModel(postRepository: PostRepository) : PostViewModel {
        return PostViewModel(postRepository)
    }
}