package com.example.newyorkSchools.ui.fragments.schooldetails

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
import com.example.a20230314_ramyashanbhag_nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.newyorkSchools.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailsFragment : BaseFragment() {
    private val TAG: String = SchoolDetailsFragment::class.java.canonicalName as String
    private val viewModel by viewModels<SchoolDetailsViewModel>()
    private var binding: FragmentSchoolDetailsBinding? = null
    private var dbn: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolDetailsBinding.inflate(inflater)
        dbn = arguments?.getString("dbn")
        Log.d(TAG, "ramya_data onCreateView: dbn $dbn")
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        dbn?.let { fetchSchoolDetails(it) }
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

    private fun fetchSchoolDetails(dbn: String){
        viewModel.fetchSchoolDetails(dbn)
    }

    private fun initObservers() {
        with(viewModel) {
            schoolsDetailsList.observe(viewLifecycleOwner) {
                if (it?.isNotEmpty() == true) {
                    val school = it[0]
                    binding?.tvSchoolName?.text = school.schoolName
                    binding?.tvReadingAvrg?.text = school.satCriticalReadingAvgScore
                    binding?.tvMathAvrg?.text = school.satMathAvgScore
                    binding?.tvWritingAvrg?.text = school.satWritingAvgScore
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

}