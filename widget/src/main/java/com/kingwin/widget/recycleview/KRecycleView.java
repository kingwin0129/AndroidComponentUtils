package com.kingwin.widget.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kingwin.widget.R;

/**
 * @author KingWin
 * @since 2021/10/21 2:15 下午
 */

public class KRecycleView extends RecyclerView {

    int mOrientation = LinearLayout.VERTICAL;

    int mSpanCount = 1;

    float mSpanSize = 1;

    int mSpanColor = R.color.black;

    int mDrawableId = -1;



    public KRecycleView(@NonNull Context context) {
        super(context);
    }

    public KRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrsView(attrs);
    }

    public KRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrsView(attrs);

    }

    public void initAttrsView(@Nullable AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.KRecycleView);
        mOrientation = ta.getInt(R.styleable.KRecycleView_orientation,LinearLayout.VERTICAL);
        mSpanCount = ta.getInt(R.styleable.KRecycleView_spanCount, 1);
        mSpanSize = ta.getDimension(R.styleable.KRecycleView_spanSize, -1);
        mSpanColor = ta.getInt(R.styleable.KRecycleView_spanColor, R.color.black);
        //mDrawableId = ta.getResourceId(R.styleable.KRecycleView_divider, -1);
        mSpanCount = mSpanCount < 1 ? 1 : mSpanCount;
        addItemDecoration(new GridDivider(getContext(),mSpanCount,mSpanSize,mSpanColor));
        setLayoutManager(new GridLayoutManager(getContext(),mSpanCount));

//        if(mSpanCount > 1){
//            addItemDecoration(new GridDivider(getContext(),mSpanCount,1,R.color.black));
//            setLayoutManager(new GridLayoutManager(getContext(),mSpanCount));
//        }else{
//            addItemDecoration(new GridDivider(getContext(),mSpanCount,1,R.color.black));
//            setLayoutManager(new GridLayoutManager(getContext(),mSpanCount));
//        }

    }

    public void setLineLayout(){
        setLineLayout(true);
    }

    public void setLineLayout(Boolean isShowDivider){
        setLineLayout(GridLayoutManager.VERTICAL,isShowDivider);
    }

    public void setLineLayout(int orientation , Boolean isShowDivider){
//        if(isShowDivider){
//            addItemDecoration(new LinearLayoutDivider(getContext(), mOrientation, mDrawableId));
//        }else{
//            addItemDecoration(new LinearLayoutDivider(getContext(), orientation));
//        }
        addItemDecoration(new LinearLayoutDivider(getContext(), mOrientation, mDrawableId));
        setLayoutManager(new GridLayoutManager(getContext(), mSpanCount));
    }

}
