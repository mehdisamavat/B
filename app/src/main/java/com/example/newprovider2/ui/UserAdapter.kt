package com.example.newprovider2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newprovider2.databinding.DataItemBinding
import com.example.domain.model.User

class UserAdapter(
    private val mainViewModel: MainActivityViewModel,
    private val lifecycleOwner: LifecycleOwner,
) :
    RecyclerView.Adapter<UserAdapter.DataViewHolder>() {


    class DataViewHolder(private var binding: DataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, mainViewModel: MainActivityViewModel, lifecycleOwner: LifecycleOwner,) {
            binding.user = user
            binding.viewModel=mainViewModel
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            DataItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(differ.currentList[position]!!,mainViewModel,lifecycleOwner)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

}



