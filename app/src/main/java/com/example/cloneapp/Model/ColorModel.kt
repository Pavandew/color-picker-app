package com.example.cloneapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class ColorModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated ID for each color
    @ColumnInfo(name = "red") val red: Float,      // Red component of the color
    @ColumnInfo(name = "green") val green: Float,  // Green component of the color
    @ColumnInfo(name = "blue") val blue: Float     // Blue component of the color
)
