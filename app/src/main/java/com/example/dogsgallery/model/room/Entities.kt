package com.example.dogsgallery.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class Dog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "urlImage") val urlImage: String = "",
    @ColumnInfo(name = "favotito") val favorito: Boolean = false,
)