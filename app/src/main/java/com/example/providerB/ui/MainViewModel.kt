package com.example.providerB.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.onError
import com.example.data.onException
import com.example.data.onSuccess
import com.example.domain.model.User
import com.example.domain.usecase.*
import com.example.providerB.ui.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val scheduleUseCase: ScheduleUseCase,
    private val uploadDataUseCase: UploadDataUseCase,
) : ViewModel() {

    val stateResponse = SingleLiveEvent<String>()

    val allUsers: LiveData<List<User?>> = getUsersUseCase().asLiveData()

    fun deleteUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteUserUseCase.invoke(id)
            } catch (e: Exception) {
                stateResponse.postValue(e.message)
            }

        }
    }

    fun updateUser(id: Int, name: String, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateUserUseCase.invoke(id, name, checked)
            } catch (e: Exception) {
                stateResponse.postValue(e.message)
            }

        }
    }





}