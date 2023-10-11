package com.example.newyorkSchools.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a20230314_ramyashanbhag_nycschools.R
import com.example.newyorkSchools.callback.NavigationListener
import com.example.newyorkSchools.ui.fragments.schooldetails.SchoolDetailsFragment
import com.example.newyorkSchools.ui.fragments.schoolslist.SchoolsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToSchoolsListScreen()
    }

    private fun navigateToSchoolsListScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, SchoolsListFragment.newInstance())
            .commit()
    }

    override fun navigateToSchoolsDetailsScreen(dbn: String?) {
        val schoolDetailsFragment = SchoolDetailsFragment()
        val bundle = Bundle()
        bundle.putString("dbn", dbn)
        schoolDetailsFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainContainer, schoolDetailsFragment)
            .commit()
    }

}