package com.app.storageapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.storageapp.models.Car

@Database(entities = [Car::class], version = 1)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}