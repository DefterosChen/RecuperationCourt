package com.kyy.recuperationcourt.common.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kyy.recuperationcourt.common.util.SystemUtils;

import java.io.File;

import static com.xuexiang.xutil.file.FileUtils.deleteFile;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = MyBroadcastReceiver .class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("收到广播了");


        /**
         * 获取（安装/替换/卸载）应用的 信息
         */
        String packages = intent.getDataString();
        packages = packages.split(":")[1];

        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Log.d(TAG, packages + "应用程序安装了，需要进行该应用安全扫描吗");
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Log.d(TAG, packages + "应用程序卸载了，需要清理垃圾有缓存吗");

            if (deleteDirectory(SystemUtils.getRootPath())) {
                System.out.println("卸载app后 文件夹删除成功");
            }
            System.out.println("卸载app后 文件夹删除失败");

        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {

            Log.d(TAG, packages + "应用程序覆盖了");
        }

    }


    /**
     * 删除文件夹以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */

    public boolean deleteDirectory(String filePath) {

        boolean flag = false;

        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }

        File dirFile = new File(filePath);

        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }

        flag = true;
        File[] files = dirFile.listFiles();

        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }


    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param filePath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */

    public boolean DeleteFolder(String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {

                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {

                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

}
