package com.example.slideranimation.bottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slideranimation.R
import com.example.slideranimation.databinding.ModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class ModalBottomSheet : BottomSheetDialogFragment() {
    private lateinit var modalBottomSheetBinding : ModalBottomSheetBinding

    /**
     * On Create view functionality for the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        modalBottomSheetBinding = ModalBottomSheetBinding.inflate(inflater, container, false)
        return modalBottomSheetBinding.root
    }

    /**
     * Lifecycle event from the OS
     *
     * @param savedInstanceState last saved instance from application
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    /**
     * On create dialog functionality for the fragment
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        (bottomSheetDialog as BottomSheetDialog).behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        bottomSheetDialog.behavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        return bottomSheetDialog
    }

    /**
     * After view created then handled the needed item for view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get()
            .load("https://cdn.dribbble.com/users/329207/screenshots/6128300/bemocs_fisherspeak_dribbble.jpg")
            .fit()
            .centerCrop()
            .into(modalBottomSheetBinding.bottomSheetImage)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}