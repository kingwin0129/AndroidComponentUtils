package com.kingwin.widget.dialog;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.kingwin.widget.R;
import com.kingwin.widget.adapter.KCommonAdapter;
import com.kingwin.utils.KConvertUtils;
import com.kingwin.widget.adapter.KCommonViewHolder;
import com.kingwin.widget.recycleview.KRecycleView;
import com.kingwin.widget.recycleview.LinearLayoutDivider;

import java.util.List;

/**
 * @author KingWin
 * @since 2021/10/18 5:24 下午
 */

public class SingleSelectDialog extends Dialog {

    /**
     * 控制点击dialog外部是否dismiss
     */
    private boolean iscancelable;

    /**
     * 控制返回键是否dismiss
     */
    private boolean isBackCancelable;

    /**
     * 标题名
     */
    private String mTitle;

    /**
     * 标题名文字大小
     */
    private int mTitleSize;

    /**
     * 标题名文字颜色
     */
    private int mTitleColor;

    /**
     * 标题名背景颜色
     */
    private int mTitleBgColor;

    /**
     * 标题名高度
     */
    private int mTitleHeight;

    /**
     * 展示数据
     */
    private List<String> mData;

    /**
     * 展示方式
     */
    private int mGravity;


    /**
     * 展示宽度
     */
    private Float mWidth;

    /**
     * 展示最大高度
     */
    private int mMaxHeight;

    /**
     * 选择结果监听器
     */
    private onSelectListener mListener;

    /**
     * 展示适配器
     */
    private KCommonAdapter mAdapter;


    public Sinnew KCommonAdapter.onBindViewListener() {
        @Override
        public int getLayoutId(int viewType) {
            return 0;
        }

        @Override
        public void onBindViewHolder(@NonNull KCommonViewHolder holder, Object data, int position) {

        }

        @Override
        public int getItemViewType(int pos) {
            return 0;
        }
    }gleSelectDialog(@NonNull Context context) {
        this(context, R.style.MyDialog);
    }

    public SingleSelectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        iscancelable = true;
        isBackCancelable = true;
        mTitle = "";
        mWidth = 0F;
        mMaxHeight = 300;
        mGravity = Gravity.BOTTOM;
        mTitleSize = 16;
        mTitleColor = context.getResources().getColor(R.color.black);
        mTitleBgColor = context.getResources().getColor(R.color.white);
        mTitleHeight = KConvertUtils.dp2px(context,50);
    }

    /**
     * 设置点击dialog外部是否dismiss
     */
    public SingleSelectDialog setIscancelable(Boolean isb){
        this.iscancelable = isb;
        return this;
    }


    /**
     * 设置返回键是否dismiss
     */
    public SingleSelectDialog setIsBackCancelable(Boolean isb){
        this.isBackCancelable = isb;
        return this;
    }

    /**
     * 设置标题名
     */
    public SingleSelectDialog setTitle(String str){
        this.mTitle = str;
        return this;
    }

    /**
     * 设置标题文字大小
     */
    public SingleSelectDialog setTitleSize(int value){
        this.mTitleSize = value;
        return this;
    }

    /**
     * 设置标题文字颜色
     */
    public SingleSelectDialog setTitleColor(@ColorInt int value){
        this.mTitleColor = value;
        return this;
    }

    /**
     * 设置标题背景颜色
     */
    public SingleSelectDialog setTitleBgColor(@ColorInt int value){
        this.mTitleBgColor = value;
        return this;
    }

    /**
     * 设置标题高度
     */
    public SingleSelectDialog setTitleHeight(int value){
        this.mTitleHeight = value;
        return this;
    }



    /**
     * 设置展示数据
     */
    public SingleSelectDialog setData(List<String> data){
        this.mData = data;
        return this;
    }

    /**
     * 设置展示方式  遵循Gravity规则\
     */
    public SingleSelectDialog setGravity(int gravity){
        mGravity = gravity;
        return this;
    }

    /**
     * 设置展示最大高度
     */
    public SingleSelectDialog setMaxHeight(int height){
        mMaxHeight = height;
        return this;
    }

    /**
     * 设置展示宽度
     */
    public SingleSelectDialog setWidth(Float width){
        mWidth = width;
        return this;
    }


    /**
     * 设置选择结果监听器
     */
    public SingleSelectDialog selectListener(onSelectListener listener){
        mListener = listener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_single,null));
        setCancelable(iscancelable);
        setIsBackCancelable(isBackCancelable);
        Window window = this.getWindow();
        window.setGravity(mGravity);
        WindowManager.LayoutParams params = window.getAttributes();
        if(mWidth > 0){
            params.width = mWidth.intValue();
        }else{
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
        }
        if(mMaxHeight > 0){
            //params.height = KConvertUtils.dp2px(getContext(),mHeight);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) findViewById(R.id.view_content));
            constraintSet.constrainMaxHeight(R.id.rl_view,KConvertUtils.dp2px(getContext(),mMaxHeight));
            TransitionManager.beginDelayedTransition((ConstraintLayout) findViewById(R.id.view_content));
            constraintSet.applyTo((ConstraintLayout) findViewById(R.id.view_content));
        }else{
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }


        window.setAttributes(params);
        initTitle();
        initAdapter();
    }



    private void initTitle(){
        TextView tvTitle = findViewById(R.id.tv_title);
        RelativeLayout rlTitle = findViewById(R.id.rl_title);
        tvTitle.setTextColor(mTitleColor);
        tvTitle.setTextSize(mTitleSize);
        rlTitle.setBackgroundColor(mTitleBgColor);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,mTitleHeight);
        rlTitle.setLayoutParams(layoutParams);

        if(!mTitle.isEmpty()){
            tvTitle.setText(mTitle);
        }else{
            rlTitle.setVisibility(View.GONE);
        }
    }

    private void initAdapter(){
        KRecycleView recyclerView = findViewById(R.id.rl_view);

        mAdapter = new KCommonAdapter(this.mData, new KCommonAdapter.onBindViewListener() {
            @Override
            public int getLayoutId(int viewType) {
                return 0;
            }

            @Override
            public void onBindViewHolder(@NonNull KCommonViewHolder holder, Object data, int position) {

            }

            @Override
            public int getItemViewType(int pos) {
                return 0;
            }
        });

        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new KCommonAdapter.onSelectListener() {
            @Override
            public void select(int index) {
                if(null != mListener){
                    mListener.select(index);
                }

                dismiss();
            }
        });

    }

    /**
     * 择结果监听器
     */
    public interface onSelectListener{
        /**
         * 选择结果
         * @param index 选择数据的下标
         */
        void select(int index);
    }


}
