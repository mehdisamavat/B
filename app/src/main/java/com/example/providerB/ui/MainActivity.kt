package com.example.providerB.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.providerB.R
import com.example.providerB.databinding.ActivityMainBinding
import com.example.providerB.ui.adapter.UserAdapter
import com.example.providerB.ui.util.checkNotificationPermission
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var userAdapter: UserAdapter
    private val permissionNotificationLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        checkNotificationPermission(permissionNotificationLauncher)

        mainViewModel.stateResponse.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        userAdapter = UserAdapter(mainViewModel, this)

        binding.itemRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = userAdapter
        }

        mainViewModel.allUsers.observe(this) { userList ->
            userAdapter.differ.submitList(userList)
        }

    }


}