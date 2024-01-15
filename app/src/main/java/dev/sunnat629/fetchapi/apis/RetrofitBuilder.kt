package dev.sunnat629.fetchapi.apis

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitBuilder {
    private const val BASE_URL = "https://api.github.com/"

    private val json = Json { ignoreUnknownKeys = true }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()
    }

    private val gitHubService: GitHubService = getRetrofit().create(GitHubService::class.java)
    val gitHubRepository: GitHubRepository = GitHubRepositoryImpl(gitHubService)
}