package com.demo.mytest.Util;

import android.content.Context;
import android.util.Log;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 引包RXJava1.1.3 RXAndroid1.1.0 OKHttp3.2.0 OKio1.7.0
 * 接口请求工具 现已添加get,post请求
 */
public class HttpApi<T>{
    public static final String ISEMPTY = "NULL";
    public static final String SUCCESS = "success";
    public static final String NETWORKLOST = "网络连接失败或未链接网络";
    private String url;
    private OkHttpClient client;
    private Observable obs;
    private Context context;

    public HttpApi(){
        if (client==null) {
            client = new OkHttpClient();
        }
    }
    public HttpApi(Context context) {
        if (client==null) {
            client = new OkHttpClient();
        }
        this.context=context;
    }

    public Observable httpGet(String string) {
        url=string;
        return allHttp(new Request.Builder().url(string).build());
    }
    /**
     * post Body封装 new FormBody.Builder().add("info_id","2").add("state","2").add("info","android文本添加").build();
     *
     * */
    public Observable httpPost(String string, FormBody body) {
        url=string;
        return allHttp(new Request.Builder().post(body).url(string).build());
    }
    public Observable allHttp(final Request request) {
        return Observable.just(url).observeOn(Schedulers.newThread()).map(new Func1<String, String>() {
            @Override
            public String call(String string) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("whx", "1");
                        return response.body().string();
                    } else {
                        Log.i("whx", "@");
                        return NETWORKLOST;
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        });
    }


    /**
     * 解析为Gosn 后返回泛型
     * */
    public Observable gosnPost(String string, FormBody body, T t) {
        url=string;
        return allGson(new Request.Builder().post(body).url(string).build(),t);
    }
    public Observable gsonGet(String string,T t) {
        url=string;
        return allGson(new Request.Builder().url(string).build(),t);
    }
    public Observable allGson(final Request request,final T t){
        return Observable.just(url).observeOn(Schedulers.newThread()).map(new Func1<String, Object>() {
            @Override
            public Object call(String string) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return new Gson().fromJson(response.body().string(),t.getClass());
                    }else {
                        return null;
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        });
    }
}
