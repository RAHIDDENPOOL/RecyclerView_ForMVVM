package com.example.mvvm

import com.example.mvvm.model.User

interface Navigator {
    fun showDetails(user: User)

    fun goBack()

    fun toast(messageRes: Int)
}