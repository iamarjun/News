package com.example.news.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentLocationDialogListDialogBinding
import com.example.news.model.Country
import com.example.news.model.countries
import com.example.news.ui.master.NewsViewModel
import com.example.news.util.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    LocationDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class LocationDialogFragment : BottomSheetDialogFragment() {

    private val binding: FragmentLocationDialogListDialogBinding by viewBinding(
        FragmentLocationDialogListDialogBinding::bind
    )

    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_dialog_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.list.apply {
            layoutManager =
                LinearLayoutManager(context)
            adapter = LocationAdapter(countries, object : LocationAdapter.Interaction {
                override fun interact(country: Country) {
                    viewModel.setCountry(country = country.code)
                }

            })
        }
    }


    companion object {

        // TODO: Customize parameters
        fun newInstance(): LocationDialogFragment =
            LocationDialogFragment()

    }
}