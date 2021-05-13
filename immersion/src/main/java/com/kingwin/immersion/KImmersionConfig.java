package com.kingwin.immersion;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.kingwin.utils.KConvertUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 沉浸式配置
 * @author KingWin
 * @since 2021/5/12 2:48 PM
 */

public class KImmersionConfig {

    /**
     * 状态栏颜色
     */
    @ColorInt private final int statusColor;

    /**
     * 状态栏Shape
     */
    @DrawableRes private final int statusShape;

    /**
     * 主题色
     */
    @ColorInt private final int mainColor;

    /**
     * 主题Shape
     */
    @DrawableRes private final int mainShape;

    /**
     * 标题栏高度
     */
    private final int mainHeight;

    /**
     * 是否显示左视图
     */
    private final boolean isShowLeftView;

    /**
     * 左视图点击事件
     */
    private final LeftViewClick leftClickListener;

    /**
     * 左图片资源
     */
    @DrawableRes private final int leftImg;

    /**
     * 左图片高度
     */
    private final int leftImgHeight;

    /**
     * 左图片宽度
     */
    private final int leftImgWidth;

    /**
     * 标题名称
     */
    private final String titleName;

    /**
     * 标题颜色
     */
    @ColorInt private final int titleColor;

    /**
     * 标题尺寸
     */
    private final int titleSize;

    /**
     * 标题最大字数限制
     */
    private final int titleMaxLength;

    /**
     * 右侧菜单视图列表
     */
    private final List<View> menuList;

    /**
     * 模式
     */
    private final int mode;

    /**
     * 是否开启沉浸式
     */
    private final boolean isOpenImmersion;

    KImmersionConfig(KImmersionConfigBuild build){
        statusColor = build.statusColor;
        statusShape = build.statusShape;
        mainColor = build.mainColor;
        mainShape = build.mainShape;
        mainHeight = build.mainHeight;
        leftImg = build.leftImg;
        leftImgHeight = build.leftImgHeight;
        leftImgWidth = build.leftImgWidth;
        isShowLeftView = build.isShowLeftView;
        leftClickListener = build.leftClickListener;
        titleName = build.titleName;
        titleColor = build.titleColor;
        titleSize = build.titleSize;
        titleMaxLength = build.titleMaxLength;
        menuList = build.menuList;
        mode = build.mode;
        isOpenImmersion = build.isOpenImmersion;
    }

    /**
     * 状态栏颜色
     */
    public int getStatusColor() {
        return statusColor;
    }

    /**
     * 状态栏Shape
     */
    public int getStatusShape() {
        return statusShape;
    }


    /**
     * 主题色
     */
    public int getMainColor() {
        return mainColor;
    }

    /**
     * 主题Shape
     */
    public int getMainShape() {
        return mainShape;
    }

    /**
     * 标题栏高度
     */
    public int getMainHeight() {
        return mainHeight;
    }

    /**
     * 左图片资源
     */
    public int getLeftImg() {
        return leftImg;
    }

    /**
     * 左图片高度
     */
    public int getLeftImgHeight() {
        return leftImgHeight;
    }

    /**
     * 左图片宽度
     */
    public int getLeftImgWidth() {
        return leftImgWidth;
    }

    /**
     * 标题名称
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * 标题颜色
     */
    public int getTitleColor() {
        return titleColor;
    }

    /**
     * 标题尺寸
     */
    public float getTitleSize() {
        return titleSize;
    }

    /**
     * 标题最大字数限制
     */
    public int getTitleMaxLength() {
        return titleMaxLength;
    }

    /**
     * 右侧菜单视图列表
     */
    public List<View> getMenuList() {
        return menuList;
    }

    /**
     * 模式
     */
    public int getMode() {
        return mode;
    }

    /**
     * 是否开启沉浸式
     */
    public boolean isOpenImmersion() {
        return isOpenImmersion;
    }

    /**
     * 获取是否显示左视图
     */
    public boolean isShowLeftView() {
        return isShowLeftView;
    }

    /**
     * 获取左视图点击事件
     */
    public LeftViewClick getLeftClickListener() {
        return leftClickListener;
    }


    public static class KImmersionConfigBuild{

        /**
         * 状态栏颜色
         */
        @ColorInt private int statusColor;

        /**
         * 状态栏Shape
         */
        @DrawableRes private int statusShape;

        /**
         * 主题色
         */
        @ColorInt private int mainColor;

        /**
         * 主题Shape
         */
        @DrawableRes private int mainShape;

        /**
         * 标题栏高度
         */
        private int mainHeight;

        /**
         * 是否显示左视图
         */
        private boolean isShowLeftView;

        /**
         * 左视图点击事件
         */
        private LeftViewClick leftClickListener;



        /**
         * 左图片资源
         */
        @DrawableRes private int leftImg;

        /**
         * 左图片高度
         */
        private int leftImgHeight;

        /**
         * 左图片宽度
         */
        private int leftImgWidth;

        /**
         * 标题名称
         */
        private String titleName;

        /**
         * 标题颜色
         */
        @ColorInt private int titleColor;

        /**
         * 标题尺寸
         */
        private int titleSize;

        /**
         * 标题最大字数限制
         */
        private int titleMaxLength;

        /**
         * 右侧菜单视图列表
         */
        private List<View> menuList;

        /**
         * 模式 默认ImmersionMode.CENTER
         */
        private int mode;

        /**
         * 是否开启沉浸式
         */
        private boolean isOpenImmersion;



        public KImmersionConfigBuild(){
            titleName = "";
            statusColor = Color.BLUE;
            mainColor = Color.BLUE;
            titleColor = Color.WHITE;
            titleSize = 20;
            titleMaxLength = 8;
            menuList = new ArrayList<>();
            isOpenImmersion = true;
            mode = KImmersionMode.CENTER;
            isShowLeftView = true;
            leftClickListener = null;
            leftImg = 0;
            leftImgWidth = 0;
            leftImgHeight = 0;
        }

        /**
         * 状态栏颜色
         */
        public KImmersionConfigBuild setStatusColor(int statusColor) {
            this.statusColor = statusColor;
            return this;
        }

        /**
         * 状态栏Shape
         */
        public KImmersionConfigBuild setStatusShape(int statusShape) {
            this.statusShape = statusShape;
            return this;
        }

        /**
         * 主题色
         */
        public KImmersionConfigBuild setMainColor(int mainColor) {
            this.mainColor = mainColor;
            return this;
        }

        /**
         * 主题Shape
         */
        public KImmersionConfigBuild setMainShape(int mainShape) {
            this.mainShape = mainShape;
            return this;
        }

        /**
         * 标题栏高度
         */
        public KImmersionConfigBuild setMainHeight(int mainHeight) {
            this.mainHeight = mainHeight;
            return this;
        }

        /**
         * 左图片资源
         */
        public KImmersionConfigBuild setLeftImg(int leftImg) {
            this.leftImg = leftImg;
            return this;
        }

        /**
         * 左图片高度
         */
        public KImmersionConfigBuild setLeftImgHeight(int leftImgHeight) {
            this.leftImgHeight = leftImgHeight;
            return this;
        }

        /**
         * 左图片宽度
         */
        public KImmersionConfigBuild setLeftImgWidth(int leftImgWidth) {
            this.leftImgWidth = leftImgWidth;
            return this;
        }

        /**
         * 标题名称
         */
        public KImmersionConfigBuild setTitleName(String titleName) {
            this.titleName = titleName;
            return this;
        }

        /**
         * 标题颜色
         */
        public KImmersionConfigBuild setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        /**
         * 标题文字尺寸
         */
        public KImmersionConfigBuild setTitleSize(int titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        /**
         * 标题最大字数限制
         */
        public KImmersionConfigBuild setTitleMaxLength(int titleMaxLength) {
            this.titleMaxLength = titleMaxLength;
            return this;
        }

        /**
         * 右侧菜单视图列表
         */
        public KImmersionConfigBuild setMenuList(@NonNull List<View> menuList) {
            this.menuList = menuList;
            return this;
        }

        /**
         * 模式
         */
        public KImmersionConfigBuild setMode(int mode) {
            this.mode = mode;
            return this;
        }

        /**
         * 设置是否开启沉浸式
         */
        public KImmersionConfigBuild setIsOpenImmersion(boolean isOpenImmersion) {
            this.isOpenImmersion = isOpenImmersion;
            return this;
        }

        /**
         * 设置是否显示左视图
         */
        public KImmersionConfigBuild setShowLeftView(boolean showLeftView) {
            isShowLeftView = showLeftView;
            return this;
        }


        /**
         * 设置左视图点击事件
         */
        public KImmersionConfigBuild setLeftClickListener(LeftViewClick leftClickListener) {
            this.leftClickListener = leftClickListener;
            return this;
        }



        public KImmersionConfig build(){
            return new KImmersionConfig(this);
        }



    }


    /**
     * 左边视图点击事件
     */
    public interface LeftViewClick{
        void onClick();
    }
}
