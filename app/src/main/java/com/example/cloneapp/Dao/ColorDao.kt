package com.example.cloneapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cloneapp.Model.ColorModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Insert
    suspend fun insertColor(color: ColorModel)

    @Query("SELECT * FROM colors ORDER BY id DESC LIMIT 1")
    fun getLastColor(): Flow<ColorModel>

    @Query("SELECT * FROM colors")
    fun getAllColors(): LiveData<List<ColorModel>>
}
