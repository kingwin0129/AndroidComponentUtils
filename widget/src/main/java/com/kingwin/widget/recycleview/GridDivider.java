package com.kingwin.widget.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author KingWin
 * @since 2021/10/22 10:40 上午
 */


public class GridDivider extends RecyclerView.ItemDecoration {

    private Drawable mDividerDarwable;
    private int mDividerHight = 1;
    private Paint mColorPaint;
    private int mSpaceCount = 1;


    public final int[] ATRRS = new int[]{android.R.attr.listDivider};

    public GridDivider(Context context) {
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDividerDarwable = ta.getDrawable(0);
        ta.recycle();
    }

    /*
     int dividerHight 分割线的线宽
     int dividerColor 分割线的颜色
     */
    public GridDivider(Context context,int spaceCount, float dividerHight, int dividerColor) {
        this(context);
        mSpaceCount = spaceCount;
        mDividerHight = (int) Math.floor(dividerHight);
        mColorPaint = new Paint();
        mColorPaint.setColor(dividerColor);
    }

    /*
     int dividerHight 分割线的线宽
     Drawable dividerDrawable 图片分割线
     */
    public GridDivider(Context context,int spaceCount, int dividerHight, Drawable dividerDrawable) {
        this(context);
        mSpaceCount = spaceCount;
        mDividerHight = dividerHight;
        mDividerDarwable = dividerDrawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if(mDividerHight > 0){
            //画水平和垂直分割线
            drawHorizontalDivider(c, parent);
            drawVerticalDivider(c, parent);
        }

    }

    public void drawVerticalDivider(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getTop();
            final int bottom = child.getBottom();

            int left = 0;
            int right = 0;


            if(mSpaceCount > 1){
                //左边第一列
                if ((i % mSpaceCount) == 0) {
                    //item左边分割线
                    left = child.getLeft() - mDividerHight;
                    right = child.getLeft();
                    mDividerDarwable.setBounds(left, top, right, bottom);
                    mDividerDarwable.draw(c);
                    if (mColorPaint != null) {
                        c.drawRect(left, top, right, bottom, mColorPaint);
                    }
                    params.leftMargin = mDividerHight;
                    //item右边分割线
                    if(i == childCount -1){
                        //item右边分割线
                        right = child.getRight() + mDividerHight;
                        left = child.getRight();
                        params.rightMargin = mDividerHight/2;
                    }else{
                        left = child.getRight();
                        right = left + (mDividerHight/2);

                        params.rightMargin = mDividerHight/2;
                    }

                }else if((i % mSpaceCount) == (mSpaceCount -1) ){
                    //item右边分割线
                    right = child.getRight() + mDividerHight;
                    left = child.getRight();
                    params.rightMargin = mDividerHight;
                    mDividerDarwable.setBounds(left, top, right, bottom);
                    mDividerDarwable.draw(c);
                    if (mColorPaint != null) {
                        c.drawRect(left, top, right, bottom, mColorPaint);
                    }
                    //item右边左分割线
                    left = child.getLeft();
                    right = child.getLeft() - (mDividerHight/2);
                    params.leftMargin = mDividerHight/2;
                } else {
                    params.leftMargin = (mDividerHight/2);
                    params.rightMargin = (mDividerHight/2);
                    //中间
                    left = child.getLeft()-  (mDividerHight/2);
                    right = child.getLeft();


                    mDividerDarwable.setBounds(left, top, right, bottom);
                    mDividerDarwable.draw(c);
                    if (mColorPaint != null) {
                        c.drawRect(left, top, right, bottom, mColorPaint);
                    }

                    //item右边分割线
                    if(i == childCount -1){
                        //item右边分割线
                        right = child.getRight() + mDividerHight;
                        left = child.getRight();
                        params.rightMargin = mDividerHight/2;
                    }else{
                        //item右边左分割线
                        left = child.getRight();
                        right = child.getRight() + (mDividerHight/2);
                    }

                }
            }



            //画分割线
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }

        }
    }

    public void drawHorizontalDivider(Canvas c, RecyclerView parent) {

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin - mDividerHight;
            final int right = child.getRight() + params.rightMargin;
            int top = 0;
            int bottom = 0;

            // 最上面一行
//            if ((i / mSpaceCount) == 0) {
//                //当前item最上面的分割线
////                top = child.getTop();
////                //当前item下面的分割线
////                bottom = top + mDividerHight;
////                mDividerDarwable.setBounds(left, top, right, bottom);
////                mDividerDarwable.draw(c);
////                if (mColorPaint != null) {
////                    c.drawRect(left, top, right, bottom, mColorPaint);
////                }
//                top = child.getBottom() - params.bottomMargin;
//                bottom = child.getBottom();
//            } else {
//                top = child.getBottom() - params.bottomMargin;
//                bottom = child.getBottom();
//            }
            if(Math.ceil((float)(i+1)/(float)mSpaceCount) < (Math.ceil((float)childCount/(float)mSpaceCount))){
                top = child.getBottom() ;
                bottom = child.getBottom() + mDividerHight;
                params.bottomMargin = mDividerHight;
            }else{
                params.bottomMargin = 0;
            }

            child.setLayoutParams(params);

            //画分割线
            mDividerDarwable.setBounds(left, top, right, bottom);
            mDividerDarwable.draw(c);
            if (mColorPaint != null) {
                c.drawRect(left, top, right, bottom, mColorPaint);
            }
        }
    }
}
