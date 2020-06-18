package com.kolyall.view.expandabletext

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.kolyall.view.expandabletext.ExpandableTextView.OnExpandableTextViewListener

class ExpandableView : RelativeLayout {
    private lateinit var mExpandableTextView: ExpandableTextView
    private lateinit var mArrowView: ImageView
    private var mCollapseIcon: Drawable? = null
    private var mExpandIcon: Drawable? = null
    private var mTextGravity = 0
    private var mTextColor = 0

    constructor(context: Context?) : super(context) {
        setContentView(R.layout.view_expandable_view)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ExpandableView)
        mTextColor = typedArray.getColor(
            R.styleable.ExpandableView_android_textColor,
            ContextCompat.getColor(getContext(), R.color.colorAccent)
        )
        mCollapseIcon = typedArray.getDrawable(R.styleable.ExpandableView_ev_collapseIcon)
        mExpandIcon = typedArray.getDrawable(R.styleable.ExpandableView_ev_expandIcon)
        mTextGravity = typedArray.getInt(R.styleable.ExpandableView_ev_textGravity, -1)
        typedArray.recycle()
        setContentView(R.layout.view_expandable_view)
    }

    private fun setContentView(viewLayout: Int) {
        View.inflate(context, viewLayout, this)
        onViewCreated()
    }

    private fun onViewCreated() {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mExpandableTextView = findViewById(R.id.expandableTextView)
        mArrowView = findViewById(R.id.arrowView)
        mExpandableTextView.setTextColor(mTextColor)
        if (mTextGravity != -1) {
            setTextGravity(mTextGravity)
        }
        mArrowView.setImageDrawable(if (mExpandableTextView.isTrim) mExpandIcon else mCollapseIcon)
        mExpandableTextView.onExpandableTextViewListener =  object:OnExpandableTextViewListener{
            override fun onExpandChanged(expanded: Boolean) {
                mArrowView.setImageDrawable(if (expanded) mCollapseIcon else mExpandIcon)
                onExpandableTextViewListener?.onExpandChanged(expanded)
            }
        }
    }

    var onExpandableTextViewListener: OnExpandableTextViewListener? = null

    fun setText(text: CharSequence?) {
        mExpandableTextView.text = text
        mArrowView.visibility = if (mExpandableTextView.isModified) View.VISIBLE else View.GONE
    }

    fun setTextGravity(center: Int) {
        mExpandableTextView.gravity = center
    }
}