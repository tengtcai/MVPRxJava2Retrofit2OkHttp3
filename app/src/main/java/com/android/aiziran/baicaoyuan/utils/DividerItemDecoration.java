package com.android.aiziran.baicaoyuan.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration{
    /**
     * GrideView有多少列
     */
    private int mSpanCount;
    private int mSpace;
    private Paint mPaint;
    private int mMaxSpanGroupIndex;
    private boolean mCanvasLast;

    /**
     * @param canvasLast 是否在最后一行添加一个分割线
     * @param space 分割线的高度
     * @param spaceColor 分割线颜色
     */
    public DividerItemDecoration(int space, int spaceColor, boolean canvasLast) {
        this.mSpace = space;
        this.mCanvasLast = canvasLast;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(spaceColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    /**
     * 获取Item的偏移量
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 获取位置
        int position = parent.getChildAdapterPosition(view);
        view.setTag(position);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            mSpanCount =  gridLayoutManager.getSpanCount();//列数
            /**
             * 整行被充满的行数
             * 列：这里有10个item，每行的spanCount是，那么mMaxSpanGroupIndex的返回值为2，
             *      如果有12个item，那么返回值为3
             *  这个方法获取到的最大行数不准确
             */
//            mMaxSpanGroupIndex = spanSizeLookup.getSpanGroupIndex(parent.getAdapter().getItemCount(), mSpanCount);
            int spanSize = spanSizeLookup.getSpanSize(position);//当前item占多少列
            int spanIndex = spanSizeLookup.getSpanIndex(position, mSpanCount);//item在当前行中所占列的下标（这里设置了四行那么从左到有依次是0-3）
            int spanGroupIndex = spanSizeLookup.getSpanGroupIndex(position, mSpanCount);//当前item在第几行（下标从0开始）
            //由于程序先执行getItemOffsets(),所以可以通过这个spanGroupIndex来获取最大行数。spanGroupIndex的最大值就是最大行数
            mMaxSpanGroupIndex = spanGroupIndex;
            /**
             * 判断当前Item不是位于该行的第一个（spanIndex != 0）并且没有占据最后一行（mSpanCount-spanSize）
             * 的时候就需要给Item设置righ偏移量为我们的Space
             */
            if (spanSize <mSpanCount && spanIndex<mSpanCount-spanSize) {
                // 右边需要偏移
                outRect.right = mSpace;
            }
            /**
             * 当Item所在的行不是第一行（spanGroupIndex != 0）的时候需要给Item设置top偏移
             */
            if(spanGroupIndex != 0) {
                outRect.bottom = mSpace;
            }
        }else if(layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                outRect.right = mSpace;
            } else {
                outRect.bottom = mSpace;
            }
        }
    }

    /**
     * 绘制分割线
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager) {
            drawSpace(c, parent);
        }else if(layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if(linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL){
                // 画竖直分割线
                drawVertical(c,parent);
            }else{
                // 画横向分割线
                drawHorizontal(c,parent);
            }
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 画横向分割线
        int top,bottom,left,right;
        for(int i=0;i<parent.getChildCount();i++){
            View child = parent.getChildAt(i);
            int position = (int) child.getTag();
            // 判断是否位于边缘
            if(position == parent.getChildCount()-1 && !mCanvasLast){
                continue;
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            /**
             * layoutParams.bottomMargin item下边的外边距
             * child.getBottom item底边距离屏幕顶部的距离
             */
            top = child.getBottom()+layoutParams.bottomMargin;//分割线距离屏幕顶部的距离（绘制矩形上边的起点）
            //绘制时向下偏移一个分割线高度的位置，显示出分割线
            bottom = top + mSpace;//分割线距离屏幕顶部的距离（绘制矩形下边的终点）
            left = child.getLeft() - layoutParams.leftMargin;//分割线距离屏幕左边的距离（绘制矩形左边的起点）
            right = child.getRight() + layoutParams.rightMargin;//分割线距离屏幕左边的距离（绘制矩形右边的终点）
            c.drawRect(left,top,right,bottom,mPaint);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        // 画竖直分割线
        int top,bottom,left,right;
        for(int i=0;i<parent.getChildCount();i++){
            View child = parent.getChildAt(i);
            int position = (int) child.getTag();
            // 判断是否位于边缘
            if(position == parent.getChildCount()-1  && !mCanvasLast) {
                continue;
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            top = child.getTop()-layoutParams.topMargin;
            bottom = child.getBottom()+layoutParams.bottomMargin;
            left = child.getRight() + layoutParams.rightMargin;
            right = left + mSpace;
            c.drawRect(left,top,right,bottom,mPaint);
        }
    }

    /**
     * 绘制分割线
     * @param canvas
     * @param parent
     */
    private void drawSpace(Canvas canvas, RecyclerView parent) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = gridLayoutManager.getSpanCount();//列数
        GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        int childCount = parent.getChildCount();//item个数
        int top,bottom,left,right;
        for(int i=0;i<childCount;i++){
            // 绘制思路，以绘制bottom和left为主，top和right不绘制，需要判断出当前的item是否位于边缘，位于边缘的item不绘制bottom和left，你懂得
            View child = parent.getChildAt(i);
            int position = (int) child.getTag();//值和i对应
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            int spanGroupIndex = spanSizeLookup.getSpanGroupIndex(position, spanCount);//当前item在的行数下标
            int spanSize = spanSizeLookup.getSpanSize(position);//当前item占多少列
            int spanIndex = spanSizeLookup.getSpanIndex(position, spanCount);//item在当前行中所占列的下标（这里设置了四行那么从左到有依次是0-3）

            /** 画right分割线
             * 当前Item不是位于该行的第一个（spanIndex != 0）并且没有占据最后行（mSpanCount-spanSize）
             */
            if (spanSize != mSpanCount && spanIndex<mSpanCount-spanSize) {
                // 左边需要分割线，开始绘制
                top = child.getTop() + layoutParams.topMargin;
                bottom = child.getBottom() + layoutParams.bottomMargin+mSpace;
                left = child.getRight() +layoutParams.rightMargin;
                right = left+mSpace;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }

            // 画bottom分割线,如果没到达底部，绘制bottom
            if(spanGroupIndex >= mMaxSpanGroupIndex && !mCanvasLast ) {
               continue;
            }
            top = child.getBottom() + layoutParams.bottomMargin;
            bottom = top + mSpace;
            left = child.getLeft() - layoutParams.leftMargin; // 不需要外边缘
            right = child.getRight() + layoutParams.rightMargin + mSpace;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }
}