package com.fancenxing.fanchen.mvppractice.network;

import com.fancenxing.fanchen.mvppractice.utilities.ContextHolder;
import com.fancenxing.fanchen.mvppractice.utilities.LogUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 类功能描述：请求的拦截器，添加一些统一的处理
 * Created by 孙中宛 on 2017/8/14.
 */

public class AppInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        if (!NetUtils.isNetWorkReachable(ContextHolder.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .url(url)
                    .build();
            LogUtils.v(AppInterceptor.class, url.toString() + "暂无网络");
        }
        RequestBody requestBody = request.body();
        String body = null;

        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = Charset.defaultCharset();
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Charset.defaultCharset());
            }
            body = buffer.readString(charset);
        }

        LogUtils.i("request", "request--method--" + request.method()
                + "\n--url-->" + request.url() + "\n--headers-->" + request.headers()
                + "\n---body-->" + body);
        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        if (NetUtils.isNetWorkReachable(ContextHolder.getContext())) {
            int maxAge = 60;
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 10 * 60;
            response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .build();
        }
        ResponseBody responseBody = response.body();
        String rBody = null;

        if (requestBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.defaultCharset();
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(Charset.defaultCharset());
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
        }
        LogUtils.i("response", "response--code-->" + response.code() + "\n--message-->" + response.message()
                + "\n--请求url-->" + response.request().url() + "\n--请求body-->" + body
                + "\n--响应时间-->" + tookMs + "\n--响应body-->" + rBody);
        return response;
    }
}
