package com.example.webviewbookmarker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.databinding.FragmentMemoTabBinding

/**
 * メモフラグメント.
 */
class MemoFragment: Fragment() {

    /**
     * Viewバインディング.
     */
    private lateinit var mBinding: FragmentMemoTabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMemoTabBinding.inflate(inflater, container, false)
        val view = mBinding.root
        return view
    }
}