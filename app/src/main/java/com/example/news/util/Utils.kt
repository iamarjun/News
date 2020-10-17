package com.example.news.util

import android.app.Activity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Fragment.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, length).show()
}

fun Activity.showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, length).show()
}

fun <T> Fragment.viewLifecycle(bindUntilEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, LifecycleObserver {

        // A backing property to hold our value
        private var binding: T? = null

        private var viewLifecycleOwner: LifecycleOwner? = null

        init {
            // Observe the View Lifecycle of the Fragment
            this@viewLifecycle
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifecycle, Observer { newLifecycleOwner ->
                    viewLifecycleOwner
                        ?.lifecycle
                        ?.removeObserver(this)

                    viewLifecycleOwner = newLifecycleOwner
                })
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onDestroy(event: Lifecycle.Event) {
            if (event == bindUntilEvent) {
                // Clear out backing property just before onDestroyView
                binding = null
            }
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            // Return the backing property if it's set
            return this.binding!!
        }

        override fun setValue(
            thisRef: Fragment,
            property: KProperty<*>,
            value: T
        ) {
            // Set the backing property
            this.binding = value
        }
    }
