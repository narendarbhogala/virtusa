package com.example.newyorkSchools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a20230314_ramyashanbhag_nycschools.data.model.SchoolsResponse
import com.example.a20230314_ramyashanbhag_nycschools.databinding.RecyclerViewSchoolItemLayoutBinding

class SchoolsRecyclerViewAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<SchoolsRecyclerViewAdapter.MeaningViewHolder>() {

    private var schoolItems = mutableListOf<SchoolsResponse>()

    inner class MeaningViewHolder(val binding: RecyclerViewSchoolItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            RecyclerViewSchoolItemLayoutBinding.inflate(inflater, parent, false)
        return MeaningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.binding.tvSchoolName.text = schoolItems[position].schoolName

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(schoolItems[position].dbn)
        }
    }

    override fun getItemCount() = schoolItems.size

    fun setData(schoolItems: List<SchoolsResponse>) {
        this.schoolItems.clear()
        this.schoolItems.addAll(schoolItems)
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(dbn: String?)
    }
}