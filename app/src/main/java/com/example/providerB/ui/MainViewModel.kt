package com.example.providerB.ui

import android.util.Log
import androidx.lifecycle.*
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
    private val getUserUseCase: GetUserUseCase,
    getUsersUseCase: GetUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val scheduleUseCase: ScheduleUseCase,
    private val uploadDataUseCase: UploadDataUseCase,
) : ViewModel() {

    val uploadStateResponse = SingleLiveEvent<String>()


    fun insertUser( name: String, checked: Boolean)  {
        viewModelScope.launch(Dispatchers.IO) {
            insertUserUseCase.invoke(name = name, checked = checked)
        }
    }

    fun uploadData(id: Int) = liveData(Dispatchers.IO)  {
        emit(getUserUseCase.invoke(id).asLiveData())
    }

    val allUsers: LiveData<List<User?>> = getUsersUseCase().asLiveData()



    fun deleteUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO)  {
            deleteUserUseCase.invoke(id)
        }
    }

    fun updateUser(id: Int, name: String, checked: Boolean) {
        viewModelScope.launch(Dispatchers.IO)  {
            updateUserUseCase.invoke(id, name, checked)
        }
    }

    fun startSchedule(){
        viewModelScope.launch(Dispatchers.IO)  {
            scheduleUseCase.invoke()
        }
    }

    fun uploadData(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = uploadDataUseCase.invoke()
            response.onSuccess {
                uploadStateResponse.postValue("Success")
            }.onError { code, message ->
                uploadStateResponse.postValue("Error = $code  $message")
            }.onException {
                uploadStateResponse.postValue("Exception =  ${it.message}")
            }
        }
    }



}