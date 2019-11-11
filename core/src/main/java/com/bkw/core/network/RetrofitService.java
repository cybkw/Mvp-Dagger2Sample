package com.bkw.core.network;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Retrofit 接口
 *
 * @author bkw
 */
public interface RetrofitService {

    /**
     * Get请求
     * 单个参数使用Query,多个参数使用QueryMap
     *
     * @param url
     * @return
     */
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * Post请求-表单
     */
    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * Put请求-表单
     */
    @FormUrlEncoded
    @POST
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 删除
     */
    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 下载-直接下载到内存中
     *
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<String> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * 上传
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Body MultipartBody body);

    /**
     * 上传文件
     *
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


    /**
     * Post请求- 原始数据
     */
    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    /**
     * Put请求- 原始数据
     */
    @POST
    Call<String> putRaw(@Url String url, @Body RequestBody body);

}
