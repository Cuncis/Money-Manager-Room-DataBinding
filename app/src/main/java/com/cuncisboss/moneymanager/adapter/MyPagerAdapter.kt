package com.cuncisboss.moneymanager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cuncisboss.moneymanager.fragment.AltMoneyFragment
import com.cuncisboss.moneymanager.fragment.MainMoneyFragment

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf<Fragment>(
        MainMoneyFragment(),
        AltMoneyFragment()
    )

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Main"
            else -> "Etc"
        }
    }
}