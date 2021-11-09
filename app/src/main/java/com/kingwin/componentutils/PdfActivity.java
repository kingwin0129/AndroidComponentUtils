package com.kingwin.componentutils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.joanzapata.pdfview.PDFView;
import com.kingwin.logger.KLogger;
import com.kingwin.net.KNetWork;
import com.kingwin.utils.KFileUtils;
import com.kingwin.utils.KPathUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author KingWin
 * @since 2021/11/8 5:48 下午
 */

public class PdfActivity extends AppCompatActivity {

    PDFView viewPdf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        viewPdf = findViewById(R.id.view_pdf);
        KNetWork.downloadFile("http://221.181.173.253:8018/Affairs/ShowFile?filePath=/YmsFileUpload/AffairGuidFiles/RSZ047.pdf", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                KLogger.d("下载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                KLogger.d("下载成功");
                InputStream inputStream = response.body().byteStream();
                if(null != inputStream){
                    String savePath = KPathUtils.getExternalAppPicturesPath() + File.separator + "curAffair.pdf";
                    //KFileUtils.createFile(savePath);
                    Boolean isDownloadFile = KFileUtils.writeFileFromIS(savePath,inputStream,false);
                    if(isDownloadFile){
                        KLogger.d("保存成功");
                        displayFromFile(new File(savePath));
                    }else{
                        KLogger.d("保存失败");
                    }

                }
            }
        });
    }

    private void displayFromFile( File file ) {
        viewPdf.fromFile(file)   //设置pdf文件地址
                .defaultPage(1)         //设置默认显示第1页
                //.onPageChange(this)     //设置翻页监听
                //.onLoad(this)           //设置加载监听
                //.onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical( false )  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load();
    }

}
