package com.example.mvvm.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.UserActionListener
import com.example.mvvm.UsersAdapter
import com.example.mvvm.databinding.FragmentUsersListBinding
import com.example.mvvm.model.User

class UsersListFragment : Fragment() {
    private lateinit var binding: FragmentUsersListBinding
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                TODO("Not yet implemented")
            }

            override fun onUserDelete(user: User) {
                TODO("Not yet implemented")
            }

            override fun onUserDetails(user: User) {
                TODO("Not yet implemented")
            }
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}