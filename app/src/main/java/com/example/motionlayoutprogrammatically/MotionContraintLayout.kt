package com.example.motionlayoutprogrammatically

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MotionContraintLayout(context: Context) : ConstraintLayout(context) {


    init {
        id = View.generateViewId()

        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)

        setLayoutParams(layoutParams)


        //*************************FilenameTextView***********************************
        val filenameLabel_ConstraintSet = ConstraintSet()

        //Initializes a new ContstraintSet for this constraint layout by copying any constraints that
        //are definied in this layout
        filenameLabel_ConstraintSet.clone(this)
        //Sets the constraint set to this contraint layout
        filenameLabel_ConstraintSet.applyTo(this)

        //*****************************************************************************

    }

    fun addFileName_TextView(context: Context, constrainSet:ConstraintSet) {

        val filenameLabelTextView = TextView(context)
        filenameLabelTextView.id = View.generateViewId()
        filenameLabelTextView.setText(R.string.filename_label)
        addView(filenameLabelTextView)


    }

    
}
