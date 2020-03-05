package com.example.news.util

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, length).show()
}

fun Activity.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

