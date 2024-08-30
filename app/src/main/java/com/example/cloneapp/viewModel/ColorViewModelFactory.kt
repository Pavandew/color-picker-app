package com.example.cloneapp.viewModel

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.cloneapp.Dao.ColorDao
//
//class ColorViewModelFactory(private val colorDao: ColorDao) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(ColorViewModel:: class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ColorViewModel(colorDao) as T
//        }
//        throw IllegalArgumentException("")
//    }
//}