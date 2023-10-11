package com.example.newyorkSchools.ui.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.newyorkSchools.callback.NavigationListener

open class BaseFragment: Fragment(){
    var listener : NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener){
            listener = context
        }
    }
}
