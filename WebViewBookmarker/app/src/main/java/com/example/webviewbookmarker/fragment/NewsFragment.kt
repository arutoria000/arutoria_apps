package com.example.webviewbookmarker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.databinding.FragmentNewsTabBinding

/**
 * 設定フラグメント.
 */
class NewsFragment: Fragment() {

    /**
     * Viewバインディング.
     */
    private lateinit var mBinding: FragmentNewsTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentNewsTabBinding.inflate(inflater, container, false)
        val view = mBinding.root
        return view
    }
}