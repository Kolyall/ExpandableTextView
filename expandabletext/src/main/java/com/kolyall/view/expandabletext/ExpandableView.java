package com.kolyall.view.expandabletext;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

    Drawable mArrowUp;
    Drawable mArrowDown;

    public ExpandableView(Context context) {
        super(context);
        setContentView(R.layout.view_expandable_view);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setContentView(R.layout.view_expandable_view);
    }

    public ExpandableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentView(R.layout.view_expandable_view);
    }

    protected void setContentView(int viewLayout) {
        inflate(getContext(),viewLayout,this);
        mExpandableTextView = findViewById(R.id.expandableTextView);
        mArrowView = findViewById(R.id.arrowView);
        onViewCreated();
    }

    protected void onViewCreated() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mExpandableTextView.setOnExpandableTextViewListener(expanded -> mArrowView.setImageDrawable(expanded ? mArrowUp : mArrowDown));
    }

    public final void setText(CharSequence text) {
        mExpandableTextView.setText(text);
    }
}
