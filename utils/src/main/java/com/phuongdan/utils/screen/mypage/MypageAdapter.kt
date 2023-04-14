package com.locationvalue.ma2.view.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phuongdan.utils.databinding.ItemMypageBinding
import com.phuongdan.utils.databinding.SpaceBinding

class MypageAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listItem = arrayListOf<ItemMyPageWithIcon>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    class ItemViewHolder(var binding: ItemMypageBinding): RecyclerView.ViewHolder(binding.root) {
        fun onbind(item: ItemMyPageWithIcon) {
            binding.apply {
                tvItemMypageTitle.text = item.triple.first
                ivItemMypageIcon.setImageDrawable(item.triple.second)
                llMypageItem.setOnClickListener {
                    item.triple.third.invoke()
                }
            }
        }
    }

    class SpaceViewholder(var binding: SpaceBinding): RecyclerView.ViewHolder(binding.root) {
        fun onbind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == MypageType.TYPE_ITEM) {
            var binding = ItemMypageBinding.inflate(layoutInflater)
            return ItemViewHolder(binding)
        }

        if (viewType == MypageType.TYPE_SPACE) {
            var binding = SpaceBinding.inflate(layoutInflater)
            return SpaceViewholder(binding)
        }

        var binding = ItemMypageBinding.inflate(layoutInflater)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = listItem[position]
        if (holder is ItemViewHolder) {
            holder.onbind(item)
        } else if (holder is SpaceViewholder) {
            holder.onbind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listItem[position].type
    }

    override fun getItemCount(): Int = listItem.size
}