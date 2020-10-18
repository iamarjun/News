package com.example.news.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.model.Country
import com.example.news.model.countries
import com.example.news.ui.master.NewsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_location_dialog_list_dialog.*

class LocationDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: NewsViewModel by activityViewModels()

    private val locationAdapter by lazy {
        LocationAdapter(countries, object : LocationAdapter.Interaction {
            override fun interact(country: Country) {
                viewModel.setCountry(country = country.code)
                dismiss()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_dialog_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = locationAdapter
        }
    }


    companion object {
        fun newInstance(): LocationDialogFragment = LocationDialogFragment()
    }
}