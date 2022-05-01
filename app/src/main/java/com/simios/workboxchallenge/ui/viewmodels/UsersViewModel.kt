package com.simios.workboxchallenge.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simios.workboxchallenge.domain.GetUsersUseCase
import com.simios.workboxchallenge.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
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