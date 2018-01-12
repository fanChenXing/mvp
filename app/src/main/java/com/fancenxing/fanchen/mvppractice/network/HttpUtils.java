package com.fancenxing.fanchen.mvppractice.network;

import com.fancenxing.fanchen.mvppractice.utilities.Constant;
import com.fancenxing.fanchen.mvppractice.utilities.FileUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * 类功能描述：网络请求的工具类
 * Created by 孙中宛 on 2017/8/14.
 */

public class HttpUtils {
    private static final String TAG = "http";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private IApiService service;

    private HttpUtils() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new AppInterceptor());
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存路径
        File cacheFile = new File(FileUtils.getAvailableCacheDir(), "appCache");
        //设置缓存大小
        Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);
        clientBuilder.cache(cache);

        OkHttpClient client = clientBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(IApiService.class);
    }

    //在访问HttpUtils
    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public IApiService getService() {
        return service;
    }

}
