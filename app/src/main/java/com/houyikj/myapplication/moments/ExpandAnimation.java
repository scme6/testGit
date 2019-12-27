package com.houyikj.myapplication.moments;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ExpandAnimation extends Animation {


    private View view;
    private RelativeLayout.LayoutParams mViewLayoutParams;
    private int mMarginStart, mMarginEnd;
    private boolean mIsVisibleAfter = false;
    private boolean mWasEndedAlready = false;

    public ExpandAnimation(View view, long duration) {
        setDuration(duration);
        this.view = view;
        mViewLayoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        mIsVisibleAfter = (mViewLayoutParams.bottomMargin == 0);
        mMarginStart = mViewLayoutParams.bottomMargin;
        view.measure(0, 0);
        mMarginEnd = (mMarginStart == 0) ? (0 - view.getMeasuredHeight()) : 0;
        // mMarginEnd = mMarginStart - view.getMeasuredHeight();
        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1f) {
            mViewLayoutParams.bottomMargin = mMarginStart + (int) ((mMarginEnd - mMarginStart) * interpolatedTime);
            view.requestLayout();
        } else if (!mWasEndedAlready) {
            mViewLayoutParams.bottomMargin = mMarginEnd;
            view.requestLayout();
            if (mIsVisibleAfter)
                view.setVisibility(View.GONE);
            mWasEndedAlready = true;
        }
    }

}
