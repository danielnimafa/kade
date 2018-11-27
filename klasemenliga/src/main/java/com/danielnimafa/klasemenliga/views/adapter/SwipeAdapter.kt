package com.danielnimafa.klasemenliga.views.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SwipeAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentList = ArrayList<Fragment>()
    private var titleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = titleList[position]

    fun addFragment(frag: Fragment, title: String) {
        fragmentList.add(frag)
        titleList.add(title)
        notifyDataSetChanged()
    }
}