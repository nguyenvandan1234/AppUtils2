package com.locationvalue.ma2.view.mypage

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.danphuong.utils.screen.BaseFragmentWithoutViewmodel
import com.phuongdan.utils.databinding.FragmentMypageBinding

class MyPageFragment(var titleHeader: String = "", var itemsMypage : ArrayList<ItemMyPageWithIcon> = arrayListOf()): BaseFragmentWithoutViewmodel<FragmentMypageBinding>() {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMypageBinding = FragmentMypageBinding.inflate(layoutInflater)
    var mMypageAdapter = MypageAdapter()

    override fun init() {

        binding.apply {
            rvMypageMenu.layoutManager = LinearLayoutManager(context)
            rvMypageMenu.adapter = mMypageAdapter
            mMypageAdapter.listItem = itemsMypage

            mtMypageHeader.apply {
                title = titleHeader
                isTitleCentered = true
            }
        }
    }

}