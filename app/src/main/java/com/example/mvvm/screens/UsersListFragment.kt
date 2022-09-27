package com.example.mvvm.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.UserActionListener
import com.example.mvvm.UsersAdapter
import com.example.mvvm.databinding.FragmentUsersListBinding
import com.example.mvvm.model.User

class UsersListFragment : Fragment() {
    private lateinit var binding: FragmentUsersListBinding
    private lateinit var adapter: UsersAdapter

    private val viewModel: UsersListViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                viewModel.moveUsers(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                viewModel.deleteUsers(user)
            }

            override fun onUserDetails(user: User) {
                navigator().showDetails(user)
            }
        })
        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.users = it
        })
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}