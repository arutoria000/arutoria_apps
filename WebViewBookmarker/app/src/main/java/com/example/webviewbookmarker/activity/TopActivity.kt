package com.example.webviewbookmarker.activity

import android.animation.Animator
import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import com.example.webviewbookmarker.adapter.TopViewPagerAdapter
import com.example.webviewbookmarker.databinding.ActivityTopBinding
import com.example.webviewbookmarker.enum.FloatingActionButtonResource
import com.example.webviewbookmarker.enum.TabLayoutResource
import com.example.webviewbookmarker.listener.CustomAnimatorListener
import com.example.webviewbookmarker.listener.CustomTabSelectedListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * トップ画面.
 */
class TopActivity: AppCompatActivity() {

    /**
     * Viewバインディング.
     */
    private lateinit var mBinding: ActivityTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View設定
        mBinding = ActivityTopBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)

        val viewPager = mBinding.topViewpager
        val tabLayout = mBinding.topTablayout
        val floatingActionButton = mBinding.topFloatingActionButton

        // ViewPagerにAdapterを設定
        viewPager.adapter = TopViewPagerAdapter(supportFragmentManager, lifecycle)

        // TabLayoutとViewPagerの紐づけ
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // タブアイコンとテキストの設定
            tab.icon = TabLayoutResource.values()[position].getTabIcon(this)
            tab.text = TabLayoutResource.values()[position].getTabTitle(this)
        }.attach()

        // タブ選択リスナー
        tabLayout.addOnTabSelectedListener(object: CustomTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (floatingActionButton.visibility == GONE) {
                    // フローティングアクションボタンに画像を設定する
                    floatingActionButton.setImageDrawable(
                        FloatingActionButtonResource.values()[tab!!.position].getIconDrawable(baseContext))
                    // フローティングアクションボタンを表示する
                    floatingActionButton.show()
                } else {
                    // 他タブ選択時にフローティングアクションボタンを非表示にする
                    floatingActionButton.hide()
                }
            }
        })

        // フローティングアクションボタンの非表示アニメーション検知リスナー
        floatingActionButton.addOnHideAnimationListener(object: CustomAnimatorListener{
            override fun onAnimationEnd(animation: Animator) {
                // フローティングアクションボタンのリソースを取得する
                val resource = FloatingActionButtonResource.values()[tabLayout.selectedTabPosition]
                // フローティングアクションボタンに画像を設定する
                floatingActionButton.setImageDrawable(resource.getIconDrawable(baseContext))
                // フローティングアクションボタンを表示するタブかどうか判定する
                if (resource.isShowFloatingActionButton()) {
                    floatingActionButton.show()
                }
            }
        })
    }
}