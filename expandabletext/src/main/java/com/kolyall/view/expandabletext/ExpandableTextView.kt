package com.kolyall.view.expandabletext

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs), View.OnClickListener {
    private var originalText: CharSequence? = null
    private var trimmedText: CharSequence? = null
    private var bufferType: BufferType? = null
    var isTrim = true
    private var trimLength: Int

    private fun setText() {
        super.setText(displayableText, bufferType)
    }

    private val displayableText: CharSequence?
        get() = if (isTrim) trimmedText else originalText

    override fun setText(text: CharSequence, type: BufferType) {
        originalText = text
        trimmedText = getTrimmedText(text)
        bufferType = type
        setText()
    }

    private fun getTrimmedText(text: CharSequence?): CharSequence? {
        return if (isModified) {
            SpannableStringBuilder(
                originalText,
                0,
                trimLength + 1
            ).append(ELLIPSIS)
        } else {
            originalText
        }
    }

    val isModified: Boolean
        get() = originalText != null && originalText!!.length > trimLength

    fun setTrimLength(trimLength: Int) {
        this.trimLength = trimLength
        trimmedText = getTrimmedText(originalText)
        setText()
    }

    fun getTrimLength(): Int {
        return trimLength
    }

    override fun onClick(v: View) {
        isTrim = !isTrim
        setText()
        onExpandableTextViewListener?.onExpandChanged(!isTrim)
    }

    var onExpandableTextViewListener: OnExpandableTextViewListener? = null

    interface OnExpandableTextViewListener {
        fun onExpandChanged(expanded: Boolean)
    }

    companion object {
        private const val DEFAULT_TRIM_LENGTH = 160
        private const val ELLIPSIS = "..."
    }

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)
        trimLength = typedArray.getInt(
            R.styleable.ExpandableTextView_trimLength,
            DEFAULT_TRIM_LENGTH
        )
        typedArray.recycle()
        setOnClickListener(this)
    }
}