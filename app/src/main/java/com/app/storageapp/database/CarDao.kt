package com.app.storageapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.storageapp.models.Car

@Dao
interface CarDao {
    @Update
    fun Update(text: String)

    @Insert
    fun Insert(car: Car)

    @Delete
    fun Delete(car: Car)

    @Query("SELECT * FROM cars")
    fun getAll(): LiveData<List<Car>>
}