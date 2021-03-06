package com.simios.workboxchallenge.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simios.workboxchallenge.domain.GetUsersUseCase
import com.simios.workboxchallenge.domain.model.User
import kotlinx.coroutines.launch

class UsersViewModel (
    private val usecase: GetUsersUseCase,
) : ViewModel() {
    val listUsers: MutableLiveData<List<User>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = usecase()
            if (!result.isNullOrEmpty()) {
                listUsers.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

}