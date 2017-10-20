package com.yuandong.savedirectory.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.yuandong.savedirectory.MyApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件工具类
 * Created by yuandong on 2017/10/19.
 */

public class FileUtils {

    private static String TAG = FileUtils.class.getSimpleName();
    public static String APP_LOG = "log";//日志文件夹

    /**
     * 检查是否存SD卡
     *
     * @return
     */
    public static boolean isHasSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得文件储存根路径
     *
     * @return
     */
    public static String getRootFilePath() {
        String path = "";
        if (isHasSdcard()) {
            //外部存储手机SD卡公有目录
            //path =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;// /storage/emulated/0
            //手机SD卡app私有目录
            path = MyApplication.mContext.getExternalFilesDir("").getPath() + File.separator;// /storage/emulated/0/Android/data/包命/files
        } else {
            //注意：内部存储没有root的手机不能打开该文件夹的
            //内部存储共有目录
            //path=Environment.getDataDirectory().getAbsolutePath()+File.separator;// /data
            //内部存储app私有目录
            path = MyApplication.mContext.getFilesDir().getPath() + File.separator;//  /data/user/0/包命/files
        }
        return path;
    }


    /**
     * 创建文件
     *
     * @param filePath 文件路径
     * @param fileName 文件命
     * @return
     */
    public static File crateFile(String filePath, String fileName) {
        File file = null;
        try {
            file = new File(getRootFilePath() + filePath, fileName);
            if (!file.exists()) {
                //如果文件不存在，先创建文件夹，再创建文件
                file.getParentFile().mkdirs();
                file.createNewFile();
                Log.e(TAG, " file create success !  filePath : " + file.getAbsolutePath());
            }else{
                Log.e(TAG, " file has existed " );
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, " file create failed !");
        }
        return file;
    }

    /**
     *
     * @param filePath 文件相对路径
     * @param fileName 文件名
     */
    public  static void deleteFile(String filePath, String fileName){
        try{
            File file=new File(getRootFilePath() + filePath, fileName);
            if(file.exists()&&file.isFile()){
                file.delete();
                Log.e(TAG, " file  deleted !");
            }else{
                Log.e(TAG, " file  not exists or is not a file !");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param filePath 文件相对路径
     * @param fileName 文件名
     * @param content  写入字符串
     * @return
     */
    public static boolean writeFileFromString(String filePath, String fileName, String content) {
        File file = crateFile(filePath, fileName);
        if (TextUtils.isEmpty(content) || file == null) return false;
        BufferedWriter bw = null;
        FileWriter fw=null;
        try {
            fw=new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.newLine();//换行
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
