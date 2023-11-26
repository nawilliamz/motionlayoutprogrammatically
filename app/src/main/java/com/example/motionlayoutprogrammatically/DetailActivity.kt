package com.example.motionlayoutprogrammatically

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.motion.widget.TransitionBuilder
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import com.example.motionlayoutprogrammatically.databinding.ActivityDetailBinding
import com.google.android.material.color.utilities.Contrast
import kotlinx.coroutines.cancel



    class DetailActivity : AppCompatActivity() {

        private lateinit var binding: ActivityDetailBinding
        private lateinit var scene: MotionScene



        private fun ConstraintLayout.initStartSet() = ConstraintSet().apply {
            clone(this@initStartSet)
            setStartingConstraints(this)
            applyTo(this@initStartSet)
        }

//        private fun ConstraintLayout.initStartSet() = ConstraintSet().apply {
//            val constraintSetX = binding.motionLayout.getConstraintSet(id)
//
//
////            clone(this@initStartSet)
//            setStartingConstraints(constraintSetX)
//            constraintSetX.applyTo(this@initStartSet)
//        }

        private fun ConstraintLayout.endStartSet() = ConstraintSet().apply {
            clone(this@endStartSet)
            setEndingConstraints(this)
            applyTo(this@endStartSet)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityDetailBinding.inflate(layoutInflater)
            setContentView(binding.root)


            //*****************************INTENT CODE*************************
            val fileName = intent.extras?.getString("FILENAME")
            val status = intent.extras?.getString("STATUS")

            Log.i("DetailActivity", "Valaue of fileName in Detail Activity is ${fileName}")
            binding.fileNameText.setText(fileName)
            binding.statusText.setText(status)
            //*****************************************************************

            runMotionLayoutTransition()

        }

        private fun runMotionLayoutTransition() {

//        val initSet = binding.root.getConstraintSet(binding.root.endState)

            scene = MotionScene(binding.root).apply {

//                val startSet = this.getConstraintSet(this@DetailActivity, "start")

                val transition = TransitionBuilder.buildTransition(
                    this,
                    R.id.transition_id,
                    R.id.start_set_id,
                    binding.root.initStartSet(),
                    R.id.end_set_id,
                    binding.root.endStartSet()
                )

                setTransition(transition)
                binding.root.scene = this
            }

            binding.root.apply {
                setTransitionDuration(5000)
                startLayoutAnimation()
//                transitionToEnd()
            }

        }


        //pass in "this"
        private fun setStartingConstraints(constraintSet: ConstraintSet) {


            //set the attributes (textColor & textSize)
//            binding.statusText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            binding.statusText.setTextColor(Color.BLACK)

            //set the layoutParameters (viewWidth, viewHeight, marginStart, marginEnd)
            val params = (binding.statusText.layoutParams as ViewGroup.MarginLayoutParams)
            params.height = resources.getDimension(R.dimen.status_text_start_height).toInt()
//            params.height = LayoutParams.WRAP_CONTENT
            params.width = LayoutParams.MATCH_PARENT
            params.setMargins(8,0,16,0)
            binding.statusText.layoutParams = params



            //set the constraints
//            constraintSet.setGuidelineEnd(R.id.guideline7, 232)
            constraintSet.connect(binding.statusText.id, ConstraintSet.BOTTOM, binding.guideline7.id, ConstraintSet.TOP)

            constraintSet.connect(binding.statusText.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

            constraintSet.setHorizontalBias(binding.statusText.id, 1.0f)

//            constraintSet.setGuidelineEnd(binding.guideline2.id, 312)
            constraintSet.connect(binding.statusText.id, ConstraintSet.START, binding.guideline2.id, ConstraintSet.END)

//            constraintSet.setGuidelineEnd(binding.guideline6.id, 488)
            constraintSet.connect(binding.statusText.id, ConstraintSet.TOP, binding.guideline6.id, ConstraintSet.BOTTOM)

            constraintSet.setVerticalBias(binding.statusText.id, 1.0f)
        }


        private fun setEndingConstraints(constraintSet: ConstraintSet) {

            //set the attributes (textColor & textSize)
            //setTextSize() sets the texttize to the given valed (scaled pixels in this case) and size
            binding.statusText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
            binding.statusText.setTextColor(Color.RED)

            //set the layoutParameters (viewWidth, viewHeight, marginStart, marginEnd)
            val params = (binding.statusText.layoutParams as ViewGroup.MarginLayoutParams)
            params.height = resources.getDimension(R.dimen.status_text_end_height).toInt()
            params.width = LayoutParams.MATCH_PARENT
            params.setMargins(8,0,16,0)
            binding.statusText.layoutParams = params

            //set the constraints
            constraintSet.connect(binding.statusText.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.START)
            constraintSet.connect(binding.statusText.id, ConstraintSet.START, binding.guideline2.id, ConstraintSet.END)
            constraintSet.connect(binding.statusText.id, ConstraintSet.TOP, binding.guideline4.id, ConstraintSet.BOTTOM)
            constraintSet.connect(binding.statusText.id, ConstraintSet.BOTTOM, binding.guideline6.id, ConstraintSet.TOP)


        }

        override fun onStart() {
            super.onStart()




        }

        override fun onDestroy() {
            super.onDestroy()

//            motionTransitionScope.cancel()

        }

}