package com.iitism.srijan25.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iitism.srijan25.R
import com.iitism.srijan25.ViewModel.ScheduleViewModel
import com.iitism.srijan25.adapter.ScheduleAdapter

class ScheduleFragment : Fragment() {

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView= view.findViewById(R.id.rv_Schedule) ?: recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        viewModel= ScheduleViewModel(requireContext())
        viewModel.getScheduleList()

        val itemAdapter = ScheduleAdapter(viewModel.ScheduleList)
        recyclerView.adapter= itemAdapter


        val spinner: Spinner = view.findViewById(R.id.spinner)
        val dates = resources.getStringArray(R.array.date)
        if(spinner!= null){
            val adapter = ArrayAdapter(requireContext(),R.layout.spinner_ui,dates)
            spinner.adapter= adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                    val selectedDate = dates[position]
                    viewModel.filterDataByDate(selectedDate)
                    itemAdapter.notifyDataSetChanged()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
    }
}