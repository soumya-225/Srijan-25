package com.iitism.srijan25.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iitism.srijan25.model.Category

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val _eventsLiveData = MutableLiveData<List<Category>>()
    val eventsLiveData: LiveData<List<Category>> get() = _eventsLiveData

    fun loadEvents(category: String) {
        val json = loadJsonFromAssets()
        val categories = parseJsonToCategories(json)
        val categoryId = categories.filter { it.id == category.toInt() }
        _eventsLiveData.postValue(categoryId)
    }

    private fun loadJsonFromAssets(): String {
        val assetManager = getApplication<Application>().assets
        val inputStream = assetManager.open("event_details_new.json")
        return inputStream.bufferedReader().use { it.readText() }
    }

    private fun parseJsonToCategories(json: String): List<Category> {
        val gson = Gson()
        val type = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(json, type)
    }
}
