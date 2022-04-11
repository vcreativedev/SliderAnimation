package com.example.slideranimation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slideranimation.adapter.SlidingAdapter
import com.example.slideranimation.databinding.ActivitySliderBinding
import com.example.slideranimation.model.SliderModel
import com.example.slideranimation.utils.attachScrollListener

class SliderActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivitySliderBinding

    /**
     * Lifecycle event from the OS
     *
     * @param savedInstanceState last saved instance from application
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        activityMainBinding.recyclerView.adapter = SlidingAdapter(this, getSliderList())
        activityMainBinding.recyclerView.attachScrollListener(activityMainBinding.lottieProgressBar)
    }

    /**
     * Handle the image for sliderList which are dynamically load from url
     */
    private fun getSliderList() : ArrayList<SliderModel> {
        return arrayListOf(
            SliderModel(
                "Fishing",
                "Patryk Wojciec",
                "https://cdn.dribbble.com/users/3178178/screenshots/6287074/cormorant_fishing_1600x1200_final_04_05_2019_4x.jpg"),
            SliderModel(
                "On The Road",
                "Brian Edward",
                "https://cdn.dribbble.com/users/329207/screenshots/6522800/2026_nationwide_02_train_landscape_v01.00.jpg"),
            SliderModel(
                "journey",
                "Febin_Raj",
                "https://cdn.dribbble.com/users/1803663/screenshots/6163551/nature-4_4x.png"),
            SliderModel(
                "Explorer",
                "Uran",
                "https://cdn.dribbble.com/users/1355613/screenshots/6441984/landscape_4x.jpg"),
            SliderModel(
                "Fishers Peak",
                "Brian Miller",
                "https://cdn.dribbble.com/users/329207/screenshots/6128300/bemocs_fisherspeak_dribbble.jpg"),
            SliderModel(
                "First Man",
                "Lana Marandina",
                "https://cdn.dribbble.com/users/1461762/screenshots/6280906/first_man_lana_marandina_4x.png"),
            SliderModel(
                "Mountain House",
                "Alex Pasquarella",
                "https://cdn.dribbble.com/users/989466/screenshots/6100954/cabin-2-dribbble-alex-pasquarella_4x.png")
        )
    }
}