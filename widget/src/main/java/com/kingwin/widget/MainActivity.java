package com.kingwin.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.kingwin.utils.KConvertUtils;
import com.kingwin.utils.KScreenUtils;
import com.kingwin.widget.dialog.SingleSelectDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        List<String> sexArr = new ArrayList<>();
        sexArr.add("男1");
        sexArr.add("男2");
        sexArr.add("男3");
        sexArr.add("男4");
        sexArr.add("男5");
        sexArr.add("男6");
        sexArr.add("男7");
        sexArr.add("男8");
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleSelectDialog dialog = new SingleSelectDialog(MainActivity.this)
                        .setTitle("选择性别")
                        .setGravity(Gravity.CENTER)
                        .setWidth(KScreenUtils.getScreenWidth() *0.88F)
                        .setData(sexArr)
                        .selectListener(new SingleSelectDialog.onSelectListener() {
                            @Override
                            public void select(int index) {
                                Toast.makeText(MainActivity.this, "选择->"+sexArr.get(index), Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.show();
            }
        });


        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexArr.add("男6");
                SingleSelectDialog dialog = new SingleSelectDialog(MainActivity.this)
                        .setGravity(Gravity.CENTER)
                        .setWidth(KScreenUtils.getScreenWidth()*0.88F)
                        .setMaxHeight(200)
                        .setData(sexArr)
                        .selectListener(new SingleSelectDialog.onSelectListener() {
                            @Override
                            public void select(int index) {
                                Toast.makeText(MainActivity.this, "选择->"+sexArr.get(index), Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.show();
            }
        });


        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexArr.add("男7");
                SingleSelectDialog dialog = new SingleSelectDialog(MainActivity.this)
                        .setGravity(Gravity.BOTTOM)
                        .setWidth(KScreenUtils.getScreenWidth()*0.88F)
                        .setMaxHeight(300)
                        .setData(sexArr)
                        .selectListener(new SingleSelectDialog.onSelectListener() {
                            @Override
                            public void select(int index) {
                                Toast.makeText(MainActivity.this, "选择->"+sexArr.get(index), Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.show();
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexArr.add("男7");
                sexArr.add("男7");


                SingleSelectDialog dialog = new SingleSelectDialog(MainActivity.this)
                        .setTitle("标题名")
                        .setGravity(Gravity.CENTER)
                        .setMaxHeight(400)
                        .setData(sexArr)
                        .setTitleBgColor(getResources().getColor(R.color.purple_200))
                        .setTitleColor(getResources().getColor(R.color.teal_200))
                        .setTitleSize(16)
                        .setTitleHeight(KConvertUtils.dp2px(MainActivity.this,50))
                        .selectListener(new SingleSelectDialog.onSelectListener() {
                            @Override
                            public void select(int index) {
                                Toast.makeText(MainActivity.this, "选择->"+sexArr.get(index), Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.show();
            }
        });
    }

}