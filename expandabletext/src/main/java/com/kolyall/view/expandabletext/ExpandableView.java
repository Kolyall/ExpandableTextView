package com.kolyall.view.expandabletext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Timofey Kovalenko on 22.01.2018.
 */
public class ExpandableView extends RelativeLayout {

    ExpandableTextView mExpandableTextView;
    ImageView mArrowView;

    Drawable mCollapseIcon;
    Drawable mExpandIcon;

    private int mTextColor;

    public ExpandableView(Context context) {
        super(context);
        setContentView(R.layout.view_expandable_view);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableView);
        mTextColor = typedArray.getColor(R.styleable.ExpandableView_android_textColor, ContextCompat.getColor(getContext(), R.color.colorAccent));
        mCollapseIcon = typedArray.getDrawable(R.styleable.ExpandableView_ev_collapseIcon);
        mExpandIcon = typedArray.getDrawable(R.styleable.ExpandableView_ev_expandIcon);
        typedArray.recycle();
        setContentView(R.layout.view_expandable_view);
    }

    protected void setContentView(int viewLayout) {
        inflate(getContext(), viewLayout, this);
        onViewCreated();
    }

    protected void onViewCreated() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mExpandableTextView = findViewById(R.id.expandableTextView);
        mArrowView = findViewById(R.id.arrowView);
        mExpandableTextView.setTextColor(mTextColor);
        mArrowView.setImageDrawable(mExpandableTextView.isTrim() ? mExpandIcon : mCollapseIcon);
        mExpandableTextView.setOnExpandableTextViewListener(expanded -> mArrowView.setImageDrawable(expanded ? mCollapseIcon : mExpandIcon));
    }

    public final void setText(CharSequence text) {
        mExpandableTextView.setText(text);
    }
}
