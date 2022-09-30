package com.tugcearas.mytravelbook.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tugcearas.mytravelbook.model.PlaceModel

@Database(entities = [PlaceModel::class], version = 1)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao():PlaceDao
}