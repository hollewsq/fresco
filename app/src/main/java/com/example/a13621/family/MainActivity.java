package com.example.a13621.family;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button hhh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();

        //设置js交互的权限
        settings.setJavaScriptEnabled(true);
        //设置允许js弹窗
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        //先加载js代码
        //格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file://arest.html");

        hhh = findViewById(R.id.but_hhh);
        hhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {

                        //注意调用的js方法名要对应是
                        //调用javascript的calljs方法
                        webView.loadUrl("javascript:callJS()");
                    }
                });
            }
        });

        //由于设置了弹窗检验调用结果，所以需要支持js对话框
        //webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        //通过设置webChromeClient对象处理javascript的对话框
        //设置响应js的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });

    }
}
