package com.app.storageapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.app.storageapp.database.CarDatabase
import com.app.storageapp.database.CarRepository
import com.app.storageapp.models.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarRepository

    val order = MutableLiveData(0)

    val carList: LiveData<List<Car>>

    init {
        val carDao = CarDatabase.getDatabase(application).carDao()
        repository = CarRepository(carDao)
        carList = Transformations.switchMap(order) {
            repository.getCarList(it)
        }
    }

    fun insert(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(car)
        }
    }

    fun update(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(car)
        }
    }

    fun delete(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(car)
        }
    }

    suspend fun getCarById(id: Int): Car {
        return withContext(Dispatchers.IO) {
            repository.getCarById(id)
        }
    }
}