package com.example.news.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.BaseFragment
import com.example.news.NewsDetail
import com.example.news.databinding.FragmentMainBinding
import com.example.news.model.Article
import com.example.news.model.Source
import com.example.news.util.showToast
import com.example.news.util.viewLifecycle
import com.momentsnap.android.EventObserver

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : BaseFragment(), NewsListAdapter.OnNewsClickListener {

    private val pageViewModel: PageViewModel by viewModels()
    private lateinit var newsAdapter: NewsListAdapter
    private var binding: FragmentMainBinding by viewLifecycle()
    private lateinit var newsList: RecyclerView


    private var listener: NewsListAdapter.OnNewsClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        listener = this
        Log.d(
            TAG, "onCreateView #${arguments?.getInt(
                ARG_SECTION_NUMBER
            ) ?: 1}"
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(
            TAG, "onViewCreated #${arguments?.getInt(
                ARG_SECTION_NUMBER
            ) ?: 1}"
        )

        pageViewModel.setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        Log.d(
            TAG, "onCreate #${arguments?.getInt(
                ARG_SECTION_NUMBER
            ) ?: 1}"
        )
        pageViewModel.setSource(
            arguments?.getParcelable(ARG_NEW_SOURCE) ?: Source(
                "abc-general",
                "us",
            )
        )

        newsList = binding.newsList
        pageViewModel.text.observe(viewLifecycleOwner, EventObserver {
//            sectionLabel.text = it
        })
    }

    override fun onResume() {
        super.onResume()

        Log.d(
            TAG, "onResume #${arguments?.getInt(
                ARG_SECTION_NUMBER
            ) ?: 1}"
        )

        pageViewModel.articles.observe(viewLifecycleOwner, EventObserver { articleList ->
            articleList?.let {

                if (!::newsAdapter.isInitialized)
                    newsAdapter = NewsListAdapter(it, listener)

                newsList.apply {
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, RecyclerView.HORIZONTAL))
                    adapter = newsAdapter
                }
            }
        })
    }

    override fun onDestroy() {
        listener = null
        super.onDestroy()
    }

    override fun onNewsClick(article: Article?) {
        val intent = Intent(requireActivity(), NewsDetail::class.java)
        intent.putExtra(NewsDetail.URL, article?.url)
        startActivity(intent)
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