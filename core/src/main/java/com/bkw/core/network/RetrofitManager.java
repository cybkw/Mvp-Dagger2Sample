package com.bkw.core.network;

import com.bkw.core.app.ProjectInit;
import com.bkw.core.network.rx.RxRetrofitService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Retrofit管理者
 *
 * @author bkw
 */
public class RetrofitManager {

    /**
     * 定义一个全局的Retrofit客户端
     */
    private final static class RetrofitHolder {
        private final static String BASE_URL = ProjectInit.API_HOST;

        private final static Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .build();
    }


    /**
     * OkHttp客户端
     */
    private final static class OKHttpHolder {
        private final static int TIME_OUT = 60;
        private final static int CALL_OUT = 20;
        private final static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .callTimeout(CALL_OUT, TimeUnit.SECONDS)
                .build();
    }

    public static RetrofitService getRetrofitService() {
        return RetrofitHolder.RETROFIT_CLIENT.create(RetrofitService.class);
    }

    public static RxRetrofitService getRxRetrofitService() {
        return RetrofitHolder.RETROFIT_CLIENT.create(RxRetrofitService.class);
    }
}
