package com.datn.shoppingapp.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager

object ViewEtx {
    val ANIMATION_DURATION: Long = 500
    val ANIMATION_SCALE: Float = 0.9f
    val ANIMATION_OPACITY: Float = 0.8f
}

fun View.showKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toGone() {
    this.visibility = View.GONE
}


fun View.toVisible(animationDuration: Long = ViewEtx.ANIMATION_DURATION) {
    if (this.visibility != View.VISIBLE) {
        this.alpha = 0f
        this.visibility = View.VISIBLE
        this.animate()
            .alpha(1f)
            .setDuration(animationDuration)
            .setListener(null)
    }
}

fun View.toInvisible(animationDuration: Long = ViewEtx.ANIMATION_DURATION) {
    if (this.visibility != View.INVISIBLE) {
        this.animate()
            .alpha(0f)
            .setDuration(animationDuration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    this@toInvisible.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
    }
}


fun View.onClickScale(
    animationDuration: Long = ViewEtx.ANIMATION_DURATION,
    scale: Float = ViewEtx.ANIMATION_SCALE,
    delay: Long = -1,
    action: () -> Unit
) {
    this.setOnClickListener {
        this.scale(
            animationDuration,
            scale,
            delay
        ) {
            action()
        }
    }
}

fun View.scale(
    animationDuration: Long = ViewEtx.ANIMATION_DURATION,
    scale: Float = ViewEtx.ANIMATION_SCALE,
    delay: Long = -1,
    callback: (() -> Unit?)? = null
) {
    var tDelay = delay
    val animator = ObjectAnimator.ofFloat(this, "scaleX", 1f, scale, 1f)
    val animator2 = ObjectAnimator.ofFloat(this, "scaleY", 1f, scale, 1f)
    animator.duration = animationDuration
    animator.start()
    animator2.duration = animationDuration
    animator2.start()
    if (tDelay < 0L) tDelay = animationDuration * 9 / 10
    this.postDelayed({
        if (callback != null) {
            callback()
        }
    }, tDelay)
}

fun View.scaleX(
    animationDuration: Long = ViewEtx.ANIMATION_DURATION,
    vararg values: Float = floatArrayOf(1f, ViewEtx.ANIMATION_SCALE, 1f)
) {
    val animator = ObjectAnimator.ofFloat(this, "scaleX", *values)
    animator.duration = animationDuration
    animator.start()
}
fun View.scaleY(
    animationDuration: Long = ViewEtx.ANIMATION_DURATION,
    vararg values: Float = floatArrayOf(1f, ViewEtx.ANIMATION_SCALE, 1f)
) {
    val animator = ObjectAnimator.ofFloat(this, "scaleY", *values)
    animator.duration = animationDuration
    animator.start()
}

fun View.onClickOpacity(
    animationDuration: Long = ViewEtx.ANIMATION_DURATION,
    opacity: Float = ViewEtx.ANIMATION_OPACITY,
    delay: Long = -1,
    action: () -> Unit
) {
    this.setOnClickListener {
        var tDelay = delay
        val animator = ObjectAnimator.ofFloat(this, "alpha", 1f, opacity, 1f)
        animator.duration = animationDuration
        animator.start()
        if (tDelay == -1L) tDelay = animationDuration * 9 / 10
        this.postDelayed({
            action()
        }, tDelay)
    }
}
