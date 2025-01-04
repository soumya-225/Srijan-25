package com.iitism.srijan25.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitism.srijan25.model.ScheduleModel
import kotlinx.coroutines.launch
import java.io.InputStream

class ScheduleViewModel(private val application: Application) : ViewModel() {

    private val _scheduleList = mutableListOf<ScheduleModel>()
    val scheduleList: List<ScheduleModel> get() = _scheduleList

    private val _isDataLoaded = MutableLiveData<Boolean>()
    val isDataLoaded: LiveData<Boolean> get() = _isDataLoaded

    var error: String? = null
    private lateinit var schedule: Array<ScheduleModel>

    fun getScheduleList() {
        viewModelScope.launch {
            try {
                _scheduleList.clear()
                val inputStream: InputStream = application.applicationContext.assets.open("concetto_events.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                schedule = gson.fromJson(json, Array<ScheduleModel>::class.java)

                _scheduleList.addAll(schedule)
                _isDataLoaded.postValue(true)
            } catch (e: Exception) {
                error = e.toString()
                _isDataLoaded.postValue(false)
            }
        }
    }

    fun filterDataByDate(selectedDate: String) {
        if(!::schedule.isInitialized){
            Log.e("ScheduleViewModel", "schedule is not initialized")
            return
        }
        val filteredList = schedule.filter {
            if (it.eventTime.length >= 2) it.eventTime.substring(0, 8) == selectedDate
            else false
        }
        Log.i("data1", _scheduleList.size.toString())
        _scheduleList.clear()
        _scheduleList.addAll(filteredList)
        Log.i("data", filteredList.toString())
    }
}