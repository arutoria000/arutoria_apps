package com.example.webviewbookmarker.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.webviewbookmarker.enum.TabLayoutResource

/**
 * Top画面のViewPagerAdapter.
 */
class TopViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        // TabLayoutのタブ数
        return TabLayoutResource.values().size
    }

    override fun createFragment(position: Int): Fragment {
        // TabLayoutに表示するFragmentを返却
        return TabLayoutResource.values()[position].getFragment()
    }
}