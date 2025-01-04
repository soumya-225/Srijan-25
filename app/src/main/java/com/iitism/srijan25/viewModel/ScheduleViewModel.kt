package com.iitism.srijan25.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitism.srijan25.model.ScheduleModel
import kotlinx.coroutines.launch
import java.io.InputStream

class ScheduleViewModel(private val context : Context) : ViewModel() {

    private val _scheduleList = mutableListOf<ScheduleModel>()
    val scheduleList : List<ScheduleModel> get()= _scheduleList

    var error: String? = null
    private lateinit var schedule: Array<ScheduleModel>

    fun getScheduleList(){
        viewModelScope.launch {
            try{
                _scheduleList.clear()
                val inputStream: InputStream = context.assets.open("concetto_events.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                schedule = gson.fromJson(json,Array<ScheduleModel>::class.java)

                _scheduleList.addAll(schedule)
            }
            catch (e: Exception){
                error= e.toString()
            }

        }
    }

    fun filterDataByDate(selectedDate: String) {
        val filteredList = schedule.filter {
            if(it.eventTime.length >=2) it.eventTime.substring(0,8) == selectedDate
            else false
        }
        Log.i("data1",_scheduleList.size.toString())
        _scheduleList.clear()
        _scheduleList.addAll(filteredList)
        Log.i("data",filteredList.toString())
    }
}