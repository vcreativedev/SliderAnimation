package com.example.slideranimation.utils

import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView

/**
 * Attach scroll listener for the recyclerview to show progress indicator to the view
 *
 * @param lottieAnimationView   the progress animation view while scrolling
 */
fun RecyclerView.attachScrollListener(lottieAnimationView: LottieAnimationView) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val offset = recyclerView.computeHorizontalScrollOffset()
            val extent = recyclerView.computeHorizontalScrollExtent()
            val range = recyclerView.computeHorizontalScrollRange()

            val percentage = offset / (range - extent).toFloat()
            lottieAnimationView.progress = if(percentage < 0.07) 0.07f else percentage
        }
    })
}