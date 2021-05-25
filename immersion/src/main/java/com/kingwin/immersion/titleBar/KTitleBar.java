package com.kingwin.immersion.titleBar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.kingwin.immersion.KImmersionConfig;
import com.kingwin.immersion.KImmersionMode;
import com.kingwin.immersion.R;
import com.kingwin.immersion.statusBar.KStatusBarUtils;
import com.kingwin.utils.KConvertUtils;
import com.kingwin.utils.KStringUtils;

/**
 * @author KingWin
 * @since 2021/5/10 5:06 PM
 */

public class KTitleBar extends RelativeLayout {


    /**
     * 沉浸式配置文件
     */
    private KImmersionConfig _config;

    /**
     * 所属界面
     */
    private Activity activity;

    RelativeLayout _statusView;
    TextView _backView;
    LinearLayout _menuView;
    TextView _titleView;
    RelativeLayout _mainView;

    public KTitleBar(Activity context, KImmersionConfig config) {
        super(context);
        activity = context;
        _config = config;
        initView();
    }


    /**
     * 初始化视图
     */
    public void initView(){
        LayoutInflater.from(this.getContext()).inflate(R.layout.view_titlebar, this);
        _statusView = findViewById(R.id.status_view);
        _backView = findViewById(R.id.back_view);
        _menuView = findViewById(R.id.menu_view);
        _titleView = findViewById(R.id.title_view);
        _mainView = findViewById(R.id.main_view);

        initStatusView();
        initTitleView();

    }


    /**
     * 初始化状态栏
     */
    private void initStatusView(){
        int height = _config.getStatusHeight() == -1 ?KStatusBarUtils.getStatusBarHeight(this.getContext()) :KConvertUtils.dp2px(getContext(),_config.getStatusHeight());
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        _statusView.setLayoutParams(params);
        if(_config.isOpenImmersion()){
            KStatusBarUtils.immersionBar(activity.getWindow());
            if(_config.getMainShape() != 0){
                _statusView.setBackgroundResource(_config.getMainShape());
            }else{
                _statusView.setBackgroundColor(_config.getMainColor());
            }
        }else{
            KStatusBarUtils.immersionBar(activity.getWindow(),_config.getStatusColor());
        }
    }


    /**
     * 初始化标题栏
     */
    private void initTitleView(){

        initMainView();
        initTitleTextView();
        initLeftView();
        initMenuView();
    }

    /**
     * 初始化标题栏主视图
     */
    private void initMainView(){
        int height = _config.getMainHeight() == -1 ?KConvertUtils.dp2px(getContext(),56) :KConvertUtils.dp2px(getContext(),_config.getMainHeight());
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
        params.addRule(RelativeLayout.BELOW,R.id.status_view);
        _mainView.setLayoutParams(params);

        if(_config.getMainShape() != 0){
            _mainView.setBackgroundResource(_config.getMainShape());
        }else{
            _mainView.setBackgroundColor(_config.getMainColor());
        }
    }

    /**
     * 初始化标题文本视图
     */
    private void initTitleTextView(){
        _titleView.setText(KStringUtils.ellipsize(_config.getTitleName(), _config.getTitleMaxLength()));
        _titleView.setTextColor(_config.getTitleColor());
        _titleView.setTextSize(_config.getTitleSize());
    }

    /**
     * 初始化左图标视图
     */
    private void initLeftView(){

        if(!_config.isShowLeftView()){
            _backView.setVisibility(View.GONE);
            return;
        }




        _backView.setCompoundDrawablesRelativeWithIntrinsicBounds(_config.getLeftIRes(),0,0,0);
//        int width = _config.getLeftImgWidth() == 0 ? KConvertUtils.dp2px(getContext(),22) :_config.getLeftImgWidth();
//        int height = _config.getLeftImgHeight() == 0 ? KConvertUtils.dp2px(getContext(),22) :_config.getLeftImgHeight();


        _backView.setText(KStringUtils.ellipsize(_config.getSubhead(), 5));
        _backView.setTextColor(_config.getSubheadColor());
        _backView.setTextSize(_config.getSubheadSize());

        if(null != _config.getLeftClickListener()){
            _backView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    _config.getLeftClickListener().onClick();
                }
            });


        }
    }


    private void initMenuView(){
        if(_config.getMenuList().size() > 0){
            for (View view : _config.getMenuList()){
                _menuView.addView(view);
            }
        }
    }



}
