package com.example.providerB.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.User
import com.example.providerB.databinding.DataItemBinding
import com.example.providerB.ui.MainViewModel


class UserAdapter(
    private val mainViewModel: MainViewModel,
    private val lifecycleOwner: LifecycleOwner,
) :
    RecyclerView.Adapter<UserAdapter.DataViewHolder>() {


    class DataViewHolder(private var binding: DataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            user: User,
            mainViewModel: MainViewModel,
            lifecycleOwner: LifecycleOwner,
        ) {
            binding.user = user
            binding.viewModel = mainViewModel
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
        holder.bind(differ.currentList[position]!!, mainViewModel, lifecycleOwner)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}



