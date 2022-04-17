package com.example.tensorhw7

import android.animation.ArgbEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tensorhw7.databinding.FragmentAnimationsBinding

private const val ANIMATION_DURATION = 400L
private const val TAG = "AnimationsFragment"

class AnimationsFragment : Fragment() {
    private lateinit var binding: FragmentAnimationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.animationsButton.setOnClickListener {
            animateViews()
        }
    }

    private fun animateViews() {
        val squareToScale = getScale(R.dimen.square_after, R.dimen.square_before)
        val squareFromColor = (binding.square.background as ColorDrawable).color
        val squareToColor = ContextCompat.getColor(requireContext(), R.color.square_after)

        val rectToScale = getScale(R.dimen.rectangle_after, R.dimen.rectangle_before)
        val rectTranslation =
            resources.getDimension(R.dimen.square_after) - resources.getDimension(R.dimen.square_before)

        val squareScaleHolder =
            PropertyValuesHolder.ofFloat("squareScale", binding.square.scaleX, squareToScale)
        val colorHolder = PropertyValuesHolder.ofObject(
            "squareColor", ArgbEvaluator(),
            squareFromColor, squareToColor
        )

        val rectScaleHolder =
            PropertyValuesHolder.ofFloat("rectScale", binding.rectangle.scaleY, rectToScale)
        val rectTranslationHolder = PropertyValuesHolder.ofFloat(
            "rectTranslation",
            binding.rectangle.translationX, rectTranslation
        )

        ValueAnimator.ofPropertyValuesHolder(
            squareScaleHolder,
            colorHolder,
            rectScaleHolder,
            rectTranslationHolder
        ).apply {
            duration = ANIMATION_DURATION
            addUpdateListener {
                val squareScale = it.getAnimatedValue("squareScale") as Float
                binding.square.pivotX = 0f
                binding.square.scaleX = squareScale
                binding.square.scaleY = squareScale

                val color = it.getAnimatedValue("squareColor") as Int
                binding.square.setBackgroundColor(color)

                val rectScale = it.getAnimatedValue("rectScale") as Float
                binding.rectangle.translationX = it.getAnimatedValue("rectTranslation") as Float
                binding.rectangle.pivotY = rectScale
                binding.rectangle.scaleY = rectScale
            }
            start()
        }
    }

    private fun getScale(@DimenRes idAfter: Int, @DimenRes idBefore: Int) =
        resources.getDimension(idAfter) / resources.getDimension(idBefore)
}