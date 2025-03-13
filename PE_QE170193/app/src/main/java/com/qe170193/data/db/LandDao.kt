package com.qe170193.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.qe170193.data.model.Land

class LandDao(context: Context) {
    private val dbHelper = LandDatabase(context)

    fun insertLand(land: Land): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", land.name)
            put("price", land.price)
            put("address", land.address)
            put("latitude", land.latitude)
            put("longitude", land.longitude)
            put("details", land.details)
        }
        val success = db.insert("Land", null, values)
        db.close()
        return success != -1L
    }

    fun updateLand(land: Land): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", land.name)
            put("price", land.price)
            put("address", land.address)
            put("latitude", land.latitude)
            put("longitude", land.longitude)
            put("details", land.details)
        }
        val success = db.update("Land", values, "id = ?", arrayOf(land.id.toString()))
        db.close()
        return success > 0
    }

    fun deleteLand(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        val success = db.delete("Land", "id = ?", arrayOf(id.toString()))
        db.close()
        return success > 0
    }

    fun getAllLands(): List<Land> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Land", null)
        val lands = mutableListOf<Land>()

        while (cursor.moveToNext()) {
            lands.add(
                Land(
                    id = cursor.getInt(0),
                    name = cursor.getString(1),
                    price = cursor.getDouble(2),
                    address = cursor.getString(3),
                    latitude = cursor.getDouble(4),
                    longitude = cursor.getDouble(5),
                    details = cursor.getString(6)
                )
            )
        }
        cursor.close()
        db.close()
        return lands
    }
}
