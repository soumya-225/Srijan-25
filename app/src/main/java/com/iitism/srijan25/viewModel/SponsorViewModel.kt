package com.iitism.srijan25.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitism.srijan25.model.SponsorData
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream

class SponsorViewModel(application: Application) : AndroidViewModel(application) {
    private val _sponsorData = MutableLiveData<List<SponsorData>>()
    val sponsorData: LiveData<List<SponsorData>> get() = _sponsorData

    fun fetchSponsorData() {
        viewModelScope.launch {
            try {
                val assetManager = getApplication<Application>().assets
                val inputStream: InputStream = assetManager.open("sponsors_data.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()
                val sponsors = gson.fromJson(json, Array<SponsorData>::class.java)
                _sponsorData.value = sponsors.toList()
            } catch (e: IOException) {
                Log.d("SponsorViewModel", e.toString())
            }
        }
    }
}
