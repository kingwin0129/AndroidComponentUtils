package com.kingwin.widget.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingwin.widget.R;

/**
 * @author KingWin
 * @since 2021/10/21 10:43 上午
 */

public class KCommonViewHolder extends RecyclerView.ViewHolder {

    // 子View的集合
    private SparseArray<View> mViews;

    TextView tvDefaultText;

    KCommonViewHolder curView;

    public KCommonViewHolder(@NonNull View itemView,KCommonAdapter.onSelectListener listener) {
        super(itemView);
        mViews = new SparseArray<>();
        tvDefaultText = itemView.findViewById(R.id.tv_default_text);
        curView = this;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listener){
                    listener.select(curView.getAdapterPosition());
                }
            }
        });
    }

    public void setDefaultText(String str){
        if(null != tvDefaultText){
            tvDefaultText.setText(str);
        }
    }

    /**
     * 提供给外部访问 View 的方法
     *
     * @param viewId id
     * @param <T>    泛型
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置文本
     *
     * @param viewId id
     * @param text   文本
     * @return this
     */
    public KCommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置图片
     *
     * @param viewId id
     * @param resId  图片id
     * @return this
     */
    public KCommonViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
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
