package com.example.fresco2;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MainActivity extends AppCompatActivity {

    private Button round, roundx, proportion, progressivetype, cache, gif, monitor, http1;
    private SimpleDraweeView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        round = findViewById(R.id.round);
        roundx = findViewById(R.id.roundx);
        proportion = findViewById(R.id.proportion);
        progressivetype = findViewById(R.id.progressivetype);
        cache = findViewById(R.id.cache);
        gif = findViewById(R.id.gif);
        monitor = findViewById(R.id.monitor);
        http1 = findViewById(R.id.http);

        image = findViewById(R.id.my_imager);
        /**
         * 设置默认比例
         */
        image.setAspectRatio(1);
        /**
         * 加载图片
         */
        Uri uri = Uri.parse("http://image.hnol.net/c/2019-02/10/12/201902101251055161-239867.jpg");
        image.setImageURI(uri);

        final GenericDraweeHierarchy hierarchy = image.getHierarchy();

        /**
         *圆角
         */
        round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
                hierarchy.setRoundingParams(roundingParams);
            }
        });

        /**
         * 圆形
         */
        roundx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setRoundAsCircle(true);
                hierarchy.setRoundingParams(roundingParams);
            }
        });

        /**
         * 比例
         */
        proportion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setAspectRatio(2);
            }
        });

        /**
         * 渐进式
         */


        /**
         * 磁盘缓存
         */
        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置磁盘缓存
                DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(MainActivity.this)
                        .setBaseDirectoryName("images_zjj")
                        .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                        .build();
                //设置磁盘缓存的配置,生成配置文件
                ImagePipelineConfig config = ImagePipelineConfig.newBuilder(MainActivity.this)
                        .setMainDiskCacheConfig(diskCacheConfig)
                        .build();
                Fresco.initialize(MainActivity.this, config);
            }
        });

        /**
         * GIF动图
         */
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549982574317&di=f44f23b11f36ad90f1cd8761bc34f9fa&imgtype=0&src=http%3A%2F%2Fs6.sinaimg.cn%2Fmw690%2F0062ywFUgy6Y2pBG8Vn65%26690&qq-pf-to=pcqq.c2c");
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)
                        .build();
                image.setController(draweeController);
            }
        });

        /**
         * 加载监听
         */
        monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
