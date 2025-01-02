package com.iitism.srijan25.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitism.srijan25.Data.CoreTeamDataModel
import kotlinx.coroutines.launch
import java.io.InputStream


class CoreTeamViewModel(private val context: Context) : ViewModel() {

    private val _coreTeamList = mutableListOf<CoreTeamDataModel>()
    val coreTeamList: List<CoreTeamDataModel>
        get() = _coreTeamList

    private var error: String? = null

    fun getCoreTeamList() {
        viewModelScope.launch {
            try {
                _coreTeamList.clear()
                val inputStream: InputStream = context.assets.open("coreTeam.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                val coreTeam = gson.fromJson(json, Array<CoreTeamDataModel>::class.java)
                _coreTeamList.addAll(coreTeam)
            } catch (e: Exception) {
                error = e.toString()
            }
        }
    }
}