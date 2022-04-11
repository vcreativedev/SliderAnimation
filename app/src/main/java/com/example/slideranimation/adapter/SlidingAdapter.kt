package com.example.slideranimation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slideranimation.activity.DetailActivity
import com.example.slideranimation.databinding.ItemSliderBinding
import com.example.slideranimation.model.SliderModel
import com.squareup.picasso.Picasso

class SlidingAdapter(private val context : Context, private val sliderList: ArrayList<SliderModel>) : RecyclerView.Adapter<SlidingAdapter.SliderViewHolder>() {

    /**
     * On view created on the adapter class from recyclerview
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    /**
     * Update view in the adapter view based on the position
     */
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val element = sliderList[position]
        Picasso.get().load(element.image).fit().centerCrop().into(holder.binding.sliderImage)
        holder.binding.title.text = element.title
        holder.binding.caption.text = element.caption
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * Returns the number of items in the list
     */
    override fun getItemCount(): Int {
        return sliderList.size
    }

    class SliderViewHolder(val binding : ItemSliderBinding) : RecyclerView.ViewHolder(binding.root)
}