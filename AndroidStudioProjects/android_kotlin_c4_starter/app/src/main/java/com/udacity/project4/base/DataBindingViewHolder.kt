package com.udacity.project4.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * View Holder for the Recycler View to bind the data item to the UI
 */
//Generics has been used to abstact this function so that any item of type T is set
//as the item variable for the binding passinto into the class constructor
class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {

        //BR is the class in data binding library that containts the IDs of the resources used for
        //data binding. Here we set a variable, BR.item, on the binding class for the item of type T
        //passed into bind()
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}