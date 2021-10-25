package com.kingwin.widget.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingwin.widget.R;

import java.util.List;

/**
 * @author KingWin
 * @since 2021/10/21 10:36 上午
 */

public class KCommonAdapter<T> extends RecyclerView.Adapter<KCommonViewHolder> {

    private List<T> mList;

    private onBindViewListener mOnBindViewListener;

    public KCommonAdapter(List<T> list){
        mList = list;
    }

    public KCommonAdapter(List<T> list,onBindViewListener listener){
        mList = list;
        mOnBindViewListener = listener;
    }


    @NonNull
    @Override
    public KCommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.itemview_default;
        if(null != mOnBindViewListener){
            layoutId = mOnBindViewListener.getLayoutId(viewType);
        }
        return new KCommonViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull KCommonViewHolder holder, int position) {
        if(null != mOnBindViewListener){
            mOnBindViewListener.onBindViewHolder(holder,mList.get(position),position);
        }else{
            if(mList.get(position) instanceof String){
                holder.setDefaultText((String) mList.get(position));
            }else{
                holder.setDefaultText("数据类型非String类型,默认展示失效");
            }
        }
    }

    @Override
    public int getItemCount() {
        if(null == mList){
            return 0;
        }
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(null == mOnBindViewListener){
            return 0;
        }
        return mOnBindViewListener.getItemViewType(position);
    }

    public interface onBindViewListener<T>{
        int getLayoutId(int viewType);
        void onBindViewHolder(@NonNull KCommonViewHolder holder, T data, int position);
        int getItemViewType(int pos);
    }
}
