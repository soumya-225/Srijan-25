package com.iitism.srijan25.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitism.srijan25.models.ScheduleModel
import kotlinx.coroutines.launch
import java.io.InputStream

class ScheduleViewModel(private val context : Context) : ViewModel() {

    private val _ScheduleList = mutableListOf<ScheduleModel>()

    val ScheduleList : List<ScheduleModel>
        get()= _ScheduleList

    var error: String? = null

    lateinit var Schedule: Array<ScheduleModel>

    fun getScheduleList(){
        viewModelScope.launch {
            try{
                _ScheduleList.clear()
                val inputStream: InputStream = context.assets.open("concetto_events.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                Schedule = gson.fromJson(json,Array<ScheduleModel>::class.java)

//                val filteredData = data.filter { it.type == 1 }
//                Log.d("Data", data.toString())
//                ClubEventList.postValue(filteredData)
                _ScheduleList.addAll(Schedule)


            }
            catch (e: Exception){
                error= e.toString()
            }

        }
    }

    fun filterDataByDate(selectedDate: String) {
        var filteredList = Schedule.filter {
            if(it.eventTime.length >=2) it.eventTime.substring(0,8) == selectedDate
            else false
        }
        Log.i("data1",_ScheduleList.size.toString())
        _ScheduleList.clear()

        _ScheduleList.addAll(filteredList)
        Log.i("data",filteredList.toString())
    }
}