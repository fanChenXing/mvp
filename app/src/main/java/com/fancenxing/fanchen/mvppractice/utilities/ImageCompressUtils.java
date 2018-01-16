package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fancenxing.fanchen.mvppractice.network.CommonErrorHandle;
import com.fancenxing.fanchen.mvppractice.network.CommonResponseListener;
import com.fancenxing.fanchen.mvppractice.network.ErrorConsumer;
import com.fancenxing.fanchen.mvppractice.network.NormalConsumer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能描述：图片压缩的工具类
 * Created by 孙中宛 on 2017/9/18.
 */

public class ImageCompressUtils {

    /**
     * 压缩图片
     *
     * @param context
     * @param path     图片地址
     * @param dpSize   图片大小（正方形）
     * @param listener
     */
    public static void compressBitmap(final Context context, String path, int dpSize, CommonResponseListener<File> listener) {
        compressBitmap(context, path, dpSize, dpSize, listener);
    }

    public static void compressBitmap(final Context context, String path, final int dpWidth, final int dpHeight, CommonResponseListener<File> listener) {
        Observable.just(path)
                .subscribeOn(Schedulers.io())
                .map(new Function<String, File>() {
                    @Override
                    public File apply(@NonNull String s) throws Exception {
                        return getSmallBitmapPath(context, s, dpWidth, dpHeight);
                    }
                })
                .onErrorResumeNext(new CommonErrorHandle<File>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NormalConsumer<>(listener), new ErrorConsumer(listener));

    }

    /**
     * 压缩图片到50k以下
     *
     * @param context
     * @param path     图片地址
     * @param dpWidth  图片宽度dp
     * @param dpHeight 图片高度dp
     * @return
     */
    private static File getSmallBitmapPath(Context context, String path, int dpWidth, int dpHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(path, options);
        LogUtils.e("sun", options.outWidth + "---old--" + options.outHeight);
        int width = options.outWidth;
        int height = options.outHeight;
        int wRate = width / MeasureUtils.dp2px(context, dpWidth);
        int hRate = height / MeasureUtils.dp2px(context, dpHeight);
        int rate;
        if (wRate >= hRate) {
            rate = hRate;
        } else {
            rate = wRate;
        }
        if (rate <= 0) {
            rate = 1;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = rate;
        String suffix = path.substring(path.lastIndexOf(".") + 1);
        LogUtils.e("sun", "---suffix--" + suffix);
        Bitmap.CompressFormat format;
        if ("png".equals(suffix)) {
            format = Bitmap.CompressFormat.PNG;
        } else {
            format = Bitmap.CompressFormat.JPEG;
        }
        Bitmap newBm = BitmapFactory.decodeFile(path, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        newBm.compress(format, 100, baos);
        if (newBm.getByteCount() / 1024 > 50) {
            int quality = 100;
            while (baos.toByteArray().length / 1024 > 50) {
                quality -= 10;
                if (quality <= 0) {
                    break;
                }
                baos.reset();
                newBm.compress(format, quality, baos);
            }
        }
        LogUtils.e("Sun", "-newBm----" + newBm.getWidth() + "---" + newBm.getHeight());
        FileOutputStream fos = null;
        try {
            File outFile = new File(context.getCacheDir(), System.currentTimeMillis() + "." + suffix);
            fos = new FileOutputStream(outFile);
            baos.writeTo(fos);
            LogUtils.e("Sun", path + "---size--" + outFile.length() + "---" + outFile.getAbsolutePath());
            return outFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
