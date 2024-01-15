package dev.sunnat629.fetchapi.apis

import dev.sunnat629.fetchapi.models.GitHubUser
import dev.sunnat629.fetchapi.network.AppResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): Result<GitHubUser>
}

interface GitHubRepository {

    suspend fun getUser(username: String): AppResult<GitHubUser>
}

class GitHubRepositoryImpl(private val gitHubService: GitHubService) : GitHubRepository {

    override suspend fun getUser(username: String): AppResult<GitHubUser> {
        return withContext(Dispatchers.IO) {
            try {
                val response = gitHubService.getUser(username)
                if (response.isSuccess) {
                    // If response is successful, wrap the data in AppResult.Success
                    response.getOrNull()?.let {
                        AppResult.Success(it)
                    } ?: AppResult.Failure(NullPointerException("Response body is null"))
                } else {
                    // Handle other exceptions like http errors
                    response.exceptionOrNull()?.let { AppResult.Failure(it) }
                        ?: AppResult.Failure(NullPointerException("Exception body is null"))
                }
            } catch (e: Exception) {
                // Handle other exceptions like network errors
                AppResult.Failure(e)
            }
        }
    }
}