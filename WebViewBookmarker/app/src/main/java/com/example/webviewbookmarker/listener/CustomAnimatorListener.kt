package com.example.webviewbookmarker.listener

import android.animation.Animator
import android.animation.Animator.AnimatorListener

/**
 * AnimatorListener継承IF.
 */
interface CustomAnimatorListener: AnimatorListener {

    override fun onAnimationStart(animation: Animator) {
    }

    override fun onAnimationEnd(animation: Animator) {
    }

    override fun onAnimationCancel(animation: Animator) {
    }

    override fun onAnimationRepeat(animation: Animator) {
    }
}