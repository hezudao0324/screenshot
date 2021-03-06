package com.example.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:根据网页地址转换成图片
 * @Author: admin
 * @CreateDate: 2018年6月22日
 */
public class PhantomTools {
    private static String tempPath = "E:/temp/img";// 图片保存目录
    private static String BLANK = " ";
    // 下面内容可以在配置文件中配置
    private static String binPath = "D:\\java\\demo\\src\\main\\resources\\files\\sysplugins\\phantomjs.exe";// 插件引入地址
    private static String jsPath = "D:\\java\\demo\\src\\main\\resources\\files\\sysplugins\\rasterize.js";// js引入地址

    // 执行cmd命令
    public static String cmd(String imgagePath, String url) {
        return binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
    }
    //关闭命令
    public static void close(Process process, BufferedReader bufferedReader) throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (process != null) {
            process.destroy();
            process = null;
        }
    }
    /**
     * @param url
     * @throws IOException 
     */
    public static void printUrlScreen2jpg(String url) throws IOException{
        String imgagePath = tempPath+"/"+System.currentTimeMillis()+".png";//图片路径
        //Java中使用Runtime和Process类运行外部程序
        Process process = Runtime.getRuntime().exec(cmd(imgagePath,url));
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
        while ((tmp = reader.readLine()) != null) {
            System.out.println(tmp);
        }
        close(process,reader);
        System.out.println("success");
    }
    
    public static void main(String[] args) throws IOException {
        String url = "https://www.baidu.com/";//以百度网站首页为例
        PhantomTools.printUrlScreen2jpg(url);
    }
}