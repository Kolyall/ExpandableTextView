package com.kolyall.view.expandabletext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by author on 13.01.2018.
 */
@Accessors(prefix = "m")
public class ExpandableTextView extends AppCompatTextView implements View.OnClickListener {
    private static final int DEFAULT_TRIM_LENGTH = 160;
    private static final String ELLIPSIS = "...";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();

        setOnClickListener(this);
    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {
            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {
            return originalText;
        }
    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }

    @Override
    public void onClick(View v) {
        trim = !trim;
        setText();
        requestFocusFromTouch();
        mOnExpandableTextViewListener.onExpandChanged(!trim);
    }

    @Setter OnExpandableTextViewListener mOnExpandableTextViewListener;

    public interface OnExpandableTextViewListener{
        void onExpandChanged(boolean expanded);
    }
}