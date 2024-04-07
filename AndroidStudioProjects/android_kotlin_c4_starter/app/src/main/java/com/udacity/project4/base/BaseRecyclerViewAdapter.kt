package com.udacity.project4.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(private val callback: ((item: T) -> Unit)? = null) :
    RecyclerView.Adapter<DataBindingViewHolder<T>>() {

    private var _items: MutableList<T> = mutableListOf()

    /**
     * Returns the _items data
     */
    private val items: List<T>?
        //a public getter
        get() = this._items

    override fun getItemCount() = _items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil
            //The ViewGroup is the view group into which the new view will be added after it is bound to
            //the adapter position
            //The viewType if the view type of the new view, i.e. TextView, ImageView, FragmentContainerView, etc
            .inflate<ViewDataBinding>(layoutInflater, getLayoutRes(viewType), parent, false)
        //Sets the binding class's lifecycle equal to the lifecycle of BaseRecyclerViewAdapter
        binding.lifecycleOwner = getLifecycleOwner()

        //Here we pass our ViewDataBinding into our DataBindingViewHolder. This allows us to
        //our binding class from inside our DataBindingViewHolder and execute the bind() function.
        //**This is equivalent to retrieving view using findViewById when not using binding since all
        //of the views are accessing through the binding classes.
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
        val item = getItem(position)

        //Instead of moving the code that sets the view in the ViewHolder to currently displayed view
        //in the RecyclerView to the ViewHolder class, it is kept here in onBindViewHolder by bringing
        //the ViewHolder into this function.
        //**This bind() method sets the item to
        holder.bind(item)
        holder.itemView.setOnClickListener {

            //invoke() allows us to run the lambda function with a safety check. Remember, we won't know
            //exacly what the lambda does until the function is passed into BaseRecyclerViewAdapter constructor
            //We can tell that the method is whatever action needs to be triggered when the user clicks on the
            //view that is displayed. I think in this case, this will trigger navigation to the screen that displays
            //the LocationReminder details and the details of the reminder that corresponds to the item at this position
            //in the list will get filled onto this screen
            callback?.invoke(item)
        }
    }

    fun getItem(position: Int) = _items[position]

    /**
     * Adds data to the actual Dataset
     *
     * @param items to be merged
     */
    fun addData(items: List<T>) {
        _items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Clears the _items data
     */
    fun clear() {
        _items.clear()
        notifyDataSetChanged()
    }

    //This method is defined in ReminderListAdapter.kt
    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    open fun getLifecycleOwner(): LifecycleOwner? {
        return null
    }
}