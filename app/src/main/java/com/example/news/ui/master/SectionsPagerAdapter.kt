//package com.example.news.ui.master
//
//import android.content.Context
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import com.example.news.model.Source
//import com.example.news.ui.detail.PlaceholderFragment
//
///**
// * A [FragmentPagerAdapter] that returns a fragment corresponding to
// * one of the sections/tabs/pages.
// */
//class SectionsPagerAdapter(
//    private val newsSource: List<Source?>,
//    private val context: Context,
//    fm: FragmentManager
//) :
//    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    override fun getItem(position: Int): Fragment {
//        // getItem is called to instantiate the fragment for the given page.
//        // Return a PlaceholderFragment (defined as a static inner class below).
//        return PlaceholderFragment.newInstance(
//            position + 1,
//            newsSource[position]
//        )
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return newsSource[position]?.name
//    }
//
//    override fun getCount(): Int {
//        return newsSource.size
//    }
//}