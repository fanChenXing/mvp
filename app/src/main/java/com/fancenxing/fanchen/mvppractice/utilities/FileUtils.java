package com.fancenxing.fanchen.mvppractice.utilities;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * 类功能描述：
 * Created by 孙中宛 on 2017/8/14.
 */

public class FileUtils {

    public static File getAvailableCacheDir() {
        return ContextHolder.getContext().getCacheDir();
    }

    public static File saveFile(String fileName, ResponseBody responseBody) {
        File file = new File(ContextHolder.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                fileName);
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            int len;
            byte[] buffer = new byte[1024];
            is = responseBody.byteStream();
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static void deleteApkFile() {
        File file = new File(ContextHolder.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                AppUtils.getVersionName() + ".apk");
        if (file.exists()) {
            file.delete();
        }
    }
}
