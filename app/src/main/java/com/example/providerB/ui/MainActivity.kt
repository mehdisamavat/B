package com.example.providerB.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.providerA.ui.adapter.UserAdapter
import com.example.providerA.ui.dialog.OnSubmitDialogClick
import com.example.providerB.R
import com.example.providerB.databinding.ActivityMainBinding
import com.example.providerB.ui.dialog.AddDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        mainViewModel.stateResponse.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
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