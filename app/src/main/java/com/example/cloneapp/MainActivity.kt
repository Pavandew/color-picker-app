package com.example.cloneapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import com.example.cloneapp.viewModel.ColorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var colorViewModel: ColorViewModel
    private lateinit var colorRecyclerView: RecyclerView
    private lateinit var colorAdapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        colorViewModel = ViewModelProvider(this).get(ColorViewModel::class.java)

        // Initialize RecyclerView
        colorRecyclerView = findViewById(R.id.recycler_view_colors)
        colorRecyclerView.layoutManager = LinearLayoutManager(this)
        colorAdapter = ColorAdapter(emptyList())
        colorRecyclerView.adapter = colorAdapter

        // Observe changes in the color list
        colorViewModel.colors.observe(this) { colors ->
            colorAdapter.updateColors(colors)
        }

        // Set up color picker UI
        val colorPreview: View = findViewById(R.id.A_value)
        val sbRed: SeekBar = findViewById(R.id.sb_red)
        val sbGreen: SeekBar = findViewById(R.id.sb_green)
        val sbBlue: SeekBar = findViewById(R.id.sb_blue)
        val btnSave: Button = findViewById(R.id.btn_show_color_page)

        sbRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                colorViewModel.updateRed(progress.toFloat() / 255)
                updateColorPreview(colorPreview)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                colorViewModel.updateGreen(progress.toFloat() / 255)
                updateColorPreview(colorPreview)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        sbBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                colorViewModel.updateBlue(progress.toFloat() / 255)
                updateColorPreview(colorPreview)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnSave.setOnClickListener {
            colorViewModel.saveColor()
        }
    }

    private fun updateColorPreview(view: View) {
        val color = colorViewModel.color.value
        view.setBackgroundColor(Color.rgb(
            (color.red * 255).toInt(),
            (color.green * 255).toInt(),
            (color.blue * 255).toInt()
        ))
    }
}
