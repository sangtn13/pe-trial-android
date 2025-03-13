package com.qe170193.repository

import android.content.Context
import com.qe170193.data.db.LandDao
import com.qe170193.data.model.Land

class LandRepository(context: Context) {
    private val landDao = LandDao(context)

    fun insertLand(land: Land) = landDao.insertLand(land)

    fun updateLand(land: Land) = landDao.updateLand(land)

    fun deleteLand(id: Int) = landDao.deleteLand(id)

    fun getAllLands(): List<Land> = landDao.getAllLands()

}
