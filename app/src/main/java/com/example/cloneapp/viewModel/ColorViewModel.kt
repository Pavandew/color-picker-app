package com.example.cloneapp.viewModel

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cloneapp.ColorDatabase
import com.example.cloneapp.Dao.ColorDao
import com.example.cloneapp.Model.ColorModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ColorViewModel(application: Application) : AndroidViewModel(application) {

    private val colorDao: ColorDao = ColorDatabase.getDatabase(application).colorDao()

    private val _color = MutableStateFlow(Color.Black)
    val color: StateFlow<Color> get() = _color


    // LiveData to observe the color list
    val colors: LiveData<List<ColorModel>> = colorDao.getAllColors()

    init {
        // Load the last saved color from the database
        viewModelScope.launch {
            colorDao.getLastColor().collectLatest { colorEntity ->
                _color.value = Color(
                    red = colorEntity.red / 255f,
                    green = colorEntity.green / 255f,
                    blue = colorEntity.blue / 255f
                )
            }
        }
    }

    fun updateRed(red: Float) {
        _color.update { it.copy(red = red) }
    }

    fun updateGreen(green: Float) {
        _color.update { it.copy(green = green) }
    }

    fun updateBlue(blue: Float) {
        _color.update { it.copy(blue = blue) }
    }

    fun saveColor() {
        viewModelScope.launch {
            val colorEntity = ColorModel(
                red = (_color.value.red * 255).toInt().toFloat(),
                green = (_color.value.green * 255).toInt().toFloat(),
                blue = (_color.value.blue * 255).toInt().toFloat()
            )
            colorDao.insertColor(colorEntity)
        }
    }
}
