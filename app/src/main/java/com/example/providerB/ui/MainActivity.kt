package com.example.providerB.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private val addDialog: AddDialog by lazy {
        AddDialog(this, object : OnSubmitDialogClick {
            override fun onSubmit(name: String, checked: Boolean) {
                mainViewModel.insertUser(name, checked)
                addDialog.dismiss()
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        userAdapter = UserAdapter(mainViewModel, this)

        binding.itemRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = userAdapter
        }

        mainViewModel.allUsers.observe(this) { userList ->
            userAdapter.differ.submitList(userList)
        }

        mainViewModel.startSchedule()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                addDialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}