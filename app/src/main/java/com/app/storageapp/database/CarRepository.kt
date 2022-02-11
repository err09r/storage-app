package com.app.storageapp.database

import androidx.lifecycle.LiveData
import com.app.storageapp.models.Car

class CarRepository(private val carDao: CarDao) {
    fun getCarList(order: Int): LiveData<List<Car>> {
        return carDao.getAll(order)
    }

    fun insert(car: Car) {
        carDao.insert(car)
    }

    fun update(car: Car) {
        carDao.update(car)
    }

    fun delete(car: Car) {
        carDao.delete(car)
    }

    fun getCarById(id: Int): Car {
        return carDao.getCarById(id)
    }
}