package com.fancenxing.fanchen.mvppractice.utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 类功能描述：json数据和javaBean转换的工具类
 * Created by 孙中宛 on 2017/8/14.
 */

public class JsonUtils {

    /**
     * 将Object转换为json数据
     *
     * @param obj
     * @return
     */
    public static String parse2Str(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将json数据转换为JavaBean
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse2Object(String data, Class clazz) {
        if (TextUtils.isEmpty(data)){
            return null;
        }
        return (T) JSON.parseObject(data, clazz);
    }

    /**
     * 将json数据转换为对象列表
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parse2List(String data, Class clazz) {
        if (TextUtils.isEmpty(data)){
            return null;
        }
        return JSON.parseArray(data, clazz);
    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
