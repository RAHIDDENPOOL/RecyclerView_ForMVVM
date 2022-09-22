package com.example.mvvm

import android.app.Application
import com.example.mvvm.model.UsersService

class App : Application() {
    val usersService = UsersService()

}