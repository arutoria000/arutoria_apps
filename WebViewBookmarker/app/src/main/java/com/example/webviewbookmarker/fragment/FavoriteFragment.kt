package com.example.webviewbookmarker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.databinding.FragmentFavoriteTabBinding

/**
 * お気に入りフラグメント.
 */
class FavoriteFragment: Fragment() {

    /**
     * Viewバインディング.
     */
    private lateinit var mBinding: FragmentFavoriteTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteTabBinding.inflate(inflater, container, false)
        val view = mBinding.root
        return view
    }

}