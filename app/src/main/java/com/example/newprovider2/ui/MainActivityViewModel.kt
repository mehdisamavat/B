package com.example.newprovider2.ui

import androidx.lifecycle.*
import com.example.domain.model.User
import com.example.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    getUsersUseCase: GetUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val scheduleUseCase: ScheduleUseCase,
) : ViewModel() {


    fun insertUser( name: String, checked: Boolean)  {
        viewModelScope.launch(Dispatchers.IO) {
            insertUserUseCase.invoke(User(name = name, checked = checked))
        }
    }

    fun getUser(id: Int) = liveData {
        emit(getUserUseCase.invoke(id).asLiveData())
    }

    val allUsers: LiveData<List<User?>> = getUsersUseCase().asLiveData()



    fun deleteUser(id: Int) {
        viewModelScope.launch {
            deleteUserUseCase.invoke(id)
        }
    }

    fun updateUser(id: Int, name: String, checked: Boolean) {
        viewModelScope.launch {
            updateUserUseCase.invoke(User(id, name, checked))
        }
    }

    fun startSchedule(){
        viewModelScope.launch {
            scheduleUseCase.invoke()
        }
    }



}