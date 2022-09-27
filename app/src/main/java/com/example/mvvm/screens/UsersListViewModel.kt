package com.example.mvvm.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User
import com.example.mvvm.model.UsersListener
import com.example.mvvm.model.UsersService

class UsersListViewModel(
    private val usersService: UsersService
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val listener: UsersListener = {
        _users.value = it
    }

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        usersService.removeListener(listener)
    }

    fun loadUsers() {
        usersService.addListener(listener)

    }

    fun moveUsers(user: User, moveBy: Int) {
        usersService.moveUser(user, moveBy)

    }

    fun deleteUsers(user: User) {
        usersService.deleteUser(user)

    }

}