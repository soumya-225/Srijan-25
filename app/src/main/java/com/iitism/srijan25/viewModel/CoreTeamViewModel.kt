package com.iitism.srijan25.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitism.srijan25.model.CoreTeam
import kotlinx.coroutines.launch
import java.io.InputStream

class CoreTeamViewModel(private val application: Context) : ViewModel() {

    private val _coreTeamList = mutableListOf<CoreTeam>()
    val coreTeamList: List<CoreTeam>
        get() = _coreTeamList

    private var error: String? = null

    fun getCoreTeamList() {
        viewModelScope.launch {
            try {
                _coreTeamList.clear()
                val inputStream: InputStream = application.assets.open("core_team_data.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                val json = String(buffer, Charsets.UTF_8)
                val gson = Gson()

                val coreTeamGet = gson.fromJson(json, Array<CoreTeam>::class.java)
                _coreTeamList.addAll(coreTeamGet)

            } catch (e: Exception) {
                error = e.toString()
                Log.d("error", error!!)
            }
        }
    }
}