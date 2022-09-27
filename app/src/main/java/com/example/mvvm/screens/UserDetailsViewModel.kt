package com.example.mvvm.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.UserNotFoundException
import com.example.mvvm.model.User
import com.example.mvvm.model.UserDetails
import com.example.mvvm.model.UsersService

class UserDetailsViewModel(
    private val usersService: UsersService
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    fun loadUser(userId: Long) {
        if (_userDetails.value != null) return
        try {
            _userDetails.value = usersService.getById(userId)
        } catch (e: UserNotFoundException) {
            e.printStackTrace()
        }
    }

    fun deleteUser() {
        val userDetails = this.userDetails.value ?: return
        usersService.deleteUser(userDetails.user)

    }
}