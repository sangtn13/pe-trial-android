package com.qe170193.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.qe170193.data.model.Land
import com.qe170193.repository.LandRepository

class LandViewModel(context: Context) : ViewModel() {

    private val repository = LandRepository(context)

    fun addLand(land: Land) = repository.insertLand(land)

    fun updateLand(land: Land) = repository.updateLand(land)

    fun deleteLand(id: Int) = repository.deleteLand(id)

    fun getLands(): List<Land> = repository.getAllLands()
}
