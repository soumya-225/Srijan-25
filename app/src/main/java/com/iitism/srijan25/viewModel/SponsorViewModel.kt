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

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    fun fetchSponsorData() {
        viewModelScope.launch {
            try {
                _showLoading.value = true

                val assetManager = getApplication<Application>().assets
                val inputStream: InputStream = assetManager.open("sponsorData.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()
                val sponsors = gson.fromJson(json, Array<SponsorData>::class.java)
                _sponsorData.value = sponsors.toList()
                _showLoading.value = false
            } catch (e: IOException) {
                Log.d("SponsorViewModel", e.toString())
                _showLoading.value = false
            }
        }
    }
}
