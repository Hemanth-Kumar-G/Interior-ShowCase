package com.hemanth.interior.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import com.facebook.drawee.view.SimpleDraweeView

class ZoomableDraweeView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : SimpleDraweeView(context, attrs, defStyle) {
    private var mScaleDetector: ScaleGestureDetector? = null
    private var mGestureDetector: GestureDetector? = null
    private var mCurrentScale = 1.0f
    private var mCurrentMatrix: Matrix? = null
    private var mMidX = 0f
    private var mMidY = 0f
    private var mClickListener: OnClickListener? = null
    private var mLongPressListener: OnLongPressListener? = null
    private fun init() {
        val mScaleListener: OnScaleGestureListener = object : SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                var scaleFactor = detector.scaleFactor
                val newScale = mCurrentScale * scaleFactor
                // Prevent from zooming out more than original
                if (newScale > 1.0f) {
                    // We initialize this lazily so that we don't have to register (and force the user
                    // to unregister) a global layout listener on the view.
                    if (mMidX == 0.0f) {
                        mMidX = width / 2.0f
                    }
                    if (mMidY == 0.0f) {
                        mMidY = height / 2.0f
                    }
                    mCurrentScale = newScale
                    // support pinch zoom
                    mCurrentMatrix!!.postScale(scaleFactor, scaleFactor, mMidX, mMidY)
                    invalidate()
                } else {
                    scaleFactor = 1.0f / mCurrentScale
                    reset()
                }
                return true
            }
        }
        mScaleDetector = ScaleGestureDetector(context, mScaleListener)
        mCurrentMatrix = Matrix()
        val mGestureListener: SimpleOnGestureListener = object : SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                // support long press listener
                if (mLongPressListener != null) {
                    mLongPressListener!!.onLongPress()
                }
            }

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                // support single tap listener
                if (mClickListener != null) {
                    mClickListener!!.onClick()
                }
                return true
            }

            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                // support drag
                // disable drag when normal scale
                if (mCurrentScale > 1.0f) {
                    mCurrentMatrix!!.postTranslate(-distanceX, -distanceY)
                    invalidate()
                }
                return true
            }
        }
        mGestureDetector = GestureDetector(context, mGestureListener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onDraw(canvas: Canvas) {
        val saveCount = canvas.save()
        canvas.concat(mCurrentMatrix)
        super.onDraw(canvas)
        canvas.restoreToCount(saveCount)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mScaleDetector!!.onTouchEvent(event)
        if (!mScaleDetector!!.isInProgress) {
            mGestureDetector!!.onTouchEvent(event)
        }
        return true
    }

    /**
     * Resets the zoom of the attached image.
     * This has no effect if the image has been destroyed
     */
    fun reset() {
        mCurrentMatrix!!.reset()
        mCurrentScale = 1.0f
        invalidate()
    }

    fun setOnLongPressListener(listener: OnLongPressListener?) {
        mLongPressListener = listener
    }

    interface OnLongPressListener {
        fun onLongPress()
    }

    fun setOnClickListener(listener: OnClickListener?) {
        mClickListener = listener
    }

    interface OnClickListener {
        fun onClick()
    }

    init {
        init()
    }
}
