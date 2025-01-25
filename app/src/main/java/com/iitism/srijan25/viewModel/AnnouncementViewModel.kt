package com.iitism.srijan25.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.iitism.srijan25.model.Announcement
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AnnouncementViewModel : ViewModel() {
    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("announcements")

    private val _announcements = MutableLiveData<List<Announcement>>()
    val announcements: LiveData<List<Announcement>> = _announcements

    private val _fetchError = MutableLiveData<String>()
    val fetchError: LiveData<String> = _fetchError

    fun fetchAnnouncements() {
        viewModelScope.launch {
            val result = try {
                val snapshot = database.get().await()
                val announcements =
                    snapshot.children.mapNotNull { it.getValue(Announcement::class.java) }
                Result.success(announcements)
            } catch (e: Exception) {
                Result.failure(e)
            }
            result.fold(
                onSuccess = { announcements ->
                    _announcements.postValue(announcements)
                },
                onFailure = { exception ->
                    _fetchError.postValue("Failed to fetch announcements: ${exception.message}")
                }
            )
        }
    }
}