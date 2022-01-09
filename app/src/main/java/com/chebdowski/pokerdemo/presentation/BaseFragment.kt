package com.chebdowski.pokerdemo.presentation

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected fun setTitle(@StringRes resourceId: Int) {
        requireActivity().setTitle(resourceId)
    }

    protected fun setTitle(title: String) {
        requireActivity().title = title
    }

    protected fun enableBackButton(enabled: Boolean) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(enabled)
    }
}