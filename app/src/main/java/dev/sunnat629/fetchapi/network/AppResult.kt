package dev.sunnat629.fetchapi.network

sealed class AppResult<out T> {
    data class Success<out T>(val data: T) : AppResult<T>()
    data class Failure(val exception: Throwable) : AppResult<Nothing>()
}

inline fun <T> AppResult<T>.onSuccess(action: (T) -> Unit): AppResult<T> {
    if (this is AppResult.Success) action(data)
    return this
}

inline fun <T> AppResult<T>.onFailure(action: (Throwable) -> Unit): AppResult<T> {
    if (this is AppResult.Failure) action(exception)
    return this
}