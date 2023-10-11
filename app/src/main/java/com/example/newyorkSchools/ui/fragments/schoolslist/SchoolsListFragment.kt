package com.example.newyorkSchools.ui.fragments.schoolslist

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.a20230314_ramyashanbhag_nycschools.R
import com.example.newyorkSchools.adapter.SchoolsRecyclerViewAdapter
import com.example.newyorkSchools.ui.fragments.BaseFragment
import com.example.a20230314_ramyashanbhag_nycschools.databinding.FragmentSchoolsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolsListFragment : BaseFragment(), SchoolsRecyclerViewAdapter.OnItemClickListener {

    private val TAG: String = SchoolsListFragment::class.java.canonicalName as String
    private val viewModel by viewModels<SchoolsListViewModel>()
    private var binding: FragmentSchoolsListBinding? = null

    private val schoolsRecyclerViewAdapter = SchoolsRecyclerViewAdapter(this@SchoolsListFragment)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolsListBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        fetchSchoolsList()
    }

    override fun onResume() {
        super.onResume()
        with((activity as AppCompatActivity).supportActionBar) {
            this?.title = getString(R.string.app_name)
            this?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun fetchSchoolsList(){
        viewModel.fetchSchoolsList()
    }

    private fun initObservers() {
        binding?.rvSchools?.adapter = schoolsRecyclerViewAdapter
        with(viewModel) {
            schoolsList.observe(viewLifecycleOwner) {
                if (it?.isNotEmpty() == true) {
                    schoolsRecyclerViewAdapter.setData(it)
                } else {
                    showErrorAlertDialog(getString(R.string.not_found))
                }
            }

            loading.observe(viewLifecycleOwner) { isLoading ->
                with(binding) {
                    this?.progress?.isVisible = isLoading
                }
            }
        }


    }

    private fun showErrorAlertDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


    companion object {
        fun newInstance() = SchoolsListFragment()
    }

    override fun onItemClick(dbn: String?) {
        Log.d(TAG, "ramya_data onItemClick: dbn value is $dbn")
       listener?.navigateToSchoolsDetailsScreen(dbn)
    }


}