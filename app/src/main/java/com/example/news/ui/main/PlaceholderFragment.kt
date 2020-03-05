package com.example.news.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.BaseFragment
import com.example.news.databinding.FragmentMainBinding
import com.example.news.model.Source
import com.example.news.ui.util.showToast
import com.momentsnap.android.EventObserver

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : BaseFragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var newsAdapter: NewsListAdapter
    private lateinit var binding: FragmentMainBinding
    private lateinit var newsList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        Log.d(TAG, "onCreateView #${arguments?.getInt(ARG_SECTION_NUMBER) ?: 1}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated #${arguments?.getInt(ARG_SECTION_NUMBER) ?: 1}")

        pageViewModel =
            ViewModelProvider(this, viewModelFactory).get(PageViewModel::class.java).apply {
                setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
                Log.d(TAG, "onCreate #${arguments?.getInt(ARG_SECTION_NUMBER) ?: 1}")
                setSource(
                    arguments?.getParcelable(ARG_NEW_SOURCE) ?: Source(
                        "abc-general",
                        "us",
                        "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                        "abc-news",
                        "en",
                        "ABC News", "https://abcnews.go.com"
                    )
                )
            }

        newsList = binding.newsList
        pageViewModel.text.observe(viewLifecycleOwner, EventObserver {
//            sectionLabel.text = it
        })
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume #${arguments?.getInt(ARG_SECTION_NUMBER) ?: 1}")

        pageViewModel.articles.observe(viewLifecycleOwner, EventObserver { articleList ->
            articleList?.let {

                if (!::newsAdapter.isInitialized)
                    newsAdapter = NewsListAdapter(it)

                newsList.apply {
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, RecyclerView.HORIZONTAL))
                    adapter = newsAdapter
                }
            }
        })

        pageViewModel.error.observe(viewLifecycleOwner, EventObserver {
            showToast(it)
        })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_NEW_SOURCE = "news_source"
        private const val TAG = "PlaceholderFragment"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, source: Source?): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putParcelable(ARG_NEW_SOURCE, source)
                }
            }
        }
    }
}