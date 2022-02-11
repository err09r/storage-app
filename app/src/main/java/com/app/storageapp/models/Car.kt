package com.app.storageapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "producer")
    val producer: String,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "model")
    val model: String
) : Serializable