package com.phuongdan.utils.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseRecyclerAdapter<T, VB : ViewBinding?>() :
		  RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<VB>>() {
	var mDataList : List<T> = listOf()
	override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder<VB> {
		val inflater = LayoutInflater.from(parent.context)
		val binding = createBinding(inflater, parent, viewType)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder : ViewHolder<VB>, position : Int) {
		val item = mDataList[position]
		bindData(holder.binding, item, position)
	}

	override fun getItemCount() : Int {
		return mDataList.size
	}
	abstract fun createBinding(inflater : LayoutInflater?, parent : ViewGroup?, viewType : Int) : VB
	abstract fun bindData(binding : VB, item : T, position : Int)
	class ViewHolder<VB : ViewBinding?>(val binding : VB) : RecyclerView.ViewHolder(binding !!.root)
}
