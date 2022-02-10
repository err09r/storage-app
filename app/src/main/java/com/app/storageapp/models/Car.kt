package com.app.storageapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "producer")
    val producer: String,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "model")
    val model: String
) {
    fun BuildDataString(): String {
        return "$producer/$year/$model"
    }
}