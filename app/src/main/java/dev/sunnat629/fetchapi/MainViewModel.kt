package dev.sunnat629.fetchapi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.sunnat629.fetchapi.apis.RetrofitBuilder.gitHubRepository
import dev.sunnat629.fetchapi.models.GitHubUser
import dev.sunnat629.fetchapi.network.onFailure
import dev.sunnat629.fetchapi.network.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var userData = mutableStateOf(GitHubUser())
    var error = mutableStateOf(false)
    var loading = mutableStateOf(false)
    private var job: Job? = null

    fun fetchUserData(username: String) {
        job = viewModelScope.launch {
            loading.value = true
            val response = gitHubRepository.getUser(username)
            response
                .onSuccess { data ->
                    userData.value = data
                    loading.value = false
                    error.value = false
                }
                .onFailure { exception ->
                    error.value = true
                    loading.value = false
                    exception.printStackTrace()
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}