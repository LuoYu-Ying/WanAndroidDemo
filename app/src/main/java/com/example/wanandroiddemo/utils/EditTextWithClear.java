package com.example.wanandroiddemo.utils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.wanandroiddemo.R;

public class EditTextWithClear extends androidx.appcompat.widget.AppCompatEditText {
    private final Drawable iconDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_clear_24);

    public EditTextWithClear(Context context) {
        super(context);
    }

    public EditTextWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        toggleClearIcon();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP
            && event.getX() > getWidth() - iconDrawable.getIntrinsicWidth() - 20
            && event.getX() < getWidth() + 20
            && event.getY() > getHeight() / 2 - iconDrawable.getIntrinsicHeight() / 2 - 20
            && event.getY() < getHeight() / 2 + iconDrawable.getIntrinsicHeight() / 2 + 20
        ) {
            getText().clear();
        }
        performClick();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        toggleClearIcon();
    }

    private void toggleClearIcon() {
        Drawable icon;
        if (isFocused() && !"".equals(getText().toString()))
            icon = iconDrawable;
        else
            icon = null;
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, icon, null);
    }

}
