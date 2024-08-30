package com.example.cloneapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloneapp.Model.ColorModel
import com.example.cloneapp.databinding.ItemColorsBinding

class ColorAdapter(private var colors: List<ColorModel>) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]
        holder.bind(color)
    }

    override fun getItemCount(): Int = colors.size

    fun updateColors(newColors: List<ColorModel>) {
        colors = newColors
        notifyDataSetChanged()
    }

    inner class ColorViewHolder(private val binding: ItemColorsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(color: ColorModel) {
            binding.colorPreview.setBackgroundColor(android.graphics.Color.rgb(color.red.toInt(), color.green.toInt(), color.blue.toInt()))
            binding.colorCode.text = String.format("#%02x%02x%02x", color.red.toInt(), color.green.toInt(), color.blue.toInt())
        }
    }
}
