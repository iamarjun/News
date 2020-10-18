package com.example.news.ui.newsSource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.model.Source
import com.example.news.ui.master.NewsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_source_list_dialog_list_dialog.*
import timber.log.Timber


class SourceListDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by activityViewModels<NewsViewModel>()
    private val selectedSources = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source_list_dialog_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SourceAdapter(viewModel.sourceList, object : SourceAdapter.Interaction {
                override fun interact(source: Source) {
                    Timber.d("$source")
                    if(source.isSelected)
                        selectedSources.add(source.id!!)
                    else
                        selectedSources.remove(source.id!!)
                }
            })
        }

        apply_source_filter.setOnClickListener {
            viewModel.setSources(selectedSources.joinToString(separator = ","))
            dismiss()
        }
    }

    companion object {

        fun newInstance(): SourceListDialogFragment = SourceListDialogFragment()
    }
}