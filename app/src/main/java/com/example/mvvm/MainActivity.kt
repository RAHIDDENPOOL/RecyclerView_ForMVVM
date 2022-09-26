package com.example.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.model.User
import com.example.mvvm.model.UsersListener
import com.example.mvvm.model.UsersService

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UsersAdapter
    private lateinit var binding: ActivityMainBinding

    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                usersService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                Toast.makeText(this@MainActivity, "User: ${user.name}", Toast.LENGTH_SHORT).show()

            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.fragmentContainer.layoutManager = layoutManager
        binding.fragmentContainer.adapter = adapter

        usersService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}