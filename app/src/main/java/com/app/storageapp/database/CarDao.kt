package com.app.storageapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.app.storageapp.models.Car

@Dao
interface CarDao {
    @Update
    fun update(car: Car)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(car: Car)

    @Delete
    fun delete(car: Car)

    @Query("SELECT * FROM cars ORDER BY " +
            "CASE WHEN :order = 0 THEN id END ASC, " +
            "CASE WHEN :order = 1 THEN producer END ASC, " +
            "CASE WHEN :order = 2 THEN year END ASC, " +
            "CASE WHEN :order = 3 THEN model END ASC "
    )
    fun getAll(order: Int): LiveData<List<Car>>

    @Query("SELECT * FROM cars WHERE id = :carId")
    fun getCarById(carId: Int): Car
}