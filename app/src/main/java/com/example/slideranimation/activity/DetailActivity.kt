package com.example.slideranimation.activity

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.slideranimation.bottomSheet.ModalBottomSheet
import com.example.slideranimation.databinding.ActivityDetailBinding


@SuppressLint("ClickableViewAccessibility")
class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private var modalBottomSheetDialog : ModalBottomSheet? = null
    private val BUTTON_CLICK_DURATION: Long = 100
    private var buttonAnimation : ViewPropertyAnimator?= null
    private var buttonShadowAnimation : ViewPropertyAnimator?= null

    /**
     * Lifecycle event from the OS
     *
     * @param savedInstanceState last saved instance from application
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)
        resetView()
        startButtonAnimation()

        /**
         * add back arrow to toolbar
          */
        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        activityDetailBinding.button.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    viewReleasedAnimation(view, activityDetailBinding.shadowView, true, BUTTON_CLICK_DURATION)
                    return@OnTouchListener false
                }
                MotionEvent.ACTION_DOWN -> {
                    clearAnimation()
                    viewPressedAnimation(view, activityDetailBinding.shadowView, true, BUTTON_CLICK_DURATION)
                    return@OnTouchListener false
                }
                else -> return@OnTouchListener false
            }
        })

        activityDetailBinding.button.setOnClickListener {
            it.postDelayed({
                showBottomSheetDialog()
            }, 500)
        }


    }



    /**
     * start the translation animation in button
     */
    private fun startButtonAnimation() {
        viewPressedAnimation(activityDetailBinding.button, activityDetailBinding.shadowView, false)
    }

    /**
     * Reset the animated view to the default position when initialized
     */
    private fun resetView() {
        activityDetailBinding.button.animate().translationY(0f).start()
        activityDetailBinding.shadowView.animate().translationY(0f).start()
    }

    /**
     * Handle the animation with functionality for view clicked state
     *
     * @param view           the button view
     * @param shadowView     the shadow view below to button
     * @param isUserTouched  is user touched on the button or not boolean value
     * @param duration       animation duration
     */
    private fun viewPressedAnimation(view : View, shadowView : View, isUserTouched : Boolean, duration: Long = 1500) {
        val animationEndRunnable = Runnable {
            if (!isUserTouched) {
                viewReleasedAnimation(view, shadowView, false, duration)
            }
        }

        buttonAnimation = view.animate().translationY(if(isUserTouched) 24f else 14f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(duration)
            .withEndAction(animationEndRunnable)
        buttonAnimation?.start()

        buttonShadowAnimation = shadowView.animate().translationY(if(isUserTouched) -12f else -5f).setDuration(duration)
        buttonShadowAnimation?.start()
    }

    /**
     * Handle the animation with functionality for view click release state
     *
     * @param view           the button view
     * @param shadowView     the shadow view below to button
     * @param isUserTouched  is user touched on the button or not boolean value
     * @param duration       animation duration
     */
    private fun viewReleasedAnimation(view : View, shadowView : View, isUserTouched : Boolean, duration: Long = 2000) {
        val animationEndRunnable = Runnable {
            if (!isUserTouched) {
                viewPressedAnimation(view, shadowView, false, duration)
            } else {
                startButtonAnimation()
            }
        }
        buttonAnimation = view.animate().translationY(0f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(duration)
            .withEndAction(animationEndRunnable)
            buttonAnimation?.start()
        buttonShadowAnimation = shadowView.animate().translationY(0f).setDuration(duration)
        buttonShadowAnimation?.start()
    }

    /**
     * Cancel the property view animation when user touches the button
     */
    private fun clearAnimation() {
        buttonAnimation?.cancel()
        buttonShadowAnimation?.cancel()
    }

    /**
     * * BottomSheet Layout implementation
     */

    private fun showBottomSheetDialog() {
        if(modalBottomSheetDialog == null) {
            modalBottomSheetDialog = ModalBottomSheet()
        } else if (modalBottomSheetDialog?.dialog?.isShowing == true) {
            modalBottomSheetDialog?.dismiss()
        }
        modalBottomSheetDialog?.show(supportFragmentManager, ModalBottomSheet.TAG)
    }

    /**
     * Handle Arrow and Close this Activity and return to preview activity
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}