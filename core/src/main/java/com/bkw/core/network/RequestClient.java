package com.bkw.core.network;

import com.bkw.core.network.callback.IError;
import com.bkw.core.network.callback.IFailure;
import com.bkw.core.network.callback.IRequest;
import com.bkw.core.network.callback.ISuccess;
import com.bkw.core.network.callback.RequestCallbacks;

import java.io.File;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 请求客户端
 *
 * @author bkw
 */
public class RequestClient {
    private final HashMap<String, Object> param;

    private final String url;
    private final IError iError;
    private final IFailure iFailure;
    private final IRequest iRequest;
    private final ISuccess iSuccess;

    /**
     * 请求体
     */
    private final RequestBody requestBody;

    //上传-下载
    private final File file;
    private final String downloadDir;
    private final String extension;
    private final String fileName;

    public RequestClient(HashMap<String, Object> param,
                         String url,
                         IError iError,
                         IFailure iFailure,
                         IRequest iRequest,
                         ISuccess iSuccess,
                         RequestBody requestBody,
                         File file,
                         String downloadDir,
                         String extension,
                         String fileName) {
        this.param = param;
        this.url = url;
        this.iError = iError;
        this.iFailure = iFailure;
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
        this.requestBody = requestBody;
        this.file = file;
        this.downloadDir = downloadDir;
        this.extension = extension;
        this.fileName = fileName;
    }


    /**
     * 为了配合建造者模式
     *
     * @return
     */
    public static RequestClientBuilder create() {
        return new RequestClientBuilder();
    }

    /**
     * 内部真正完成请求操作的处理
     */
    private void requestAction(HttpMethod httpMethod) {
        RetrofitService retrofitService = RetrofitManager.getRetrofitService();

        //标记一个起点
        if (iRequest != null) {
            iRequest.onRequestStart();
        }

        //接收
        Call<String> callResult = null;

        switch (httpMethod) {
            case GET:
                callResult = retrofitService.get(url, param);
                break;
            case PUT:
                callResult = retrofitService.put(url, param);
                break;
            case POST:
                callResult = retrofitService.post(url, param);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
                final MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                retrofitService.upload(url, part);
                break;
            case DOWNLOAD:
                callResult = retrofitService.download(url, param);
                break;
            case DELETE:
                callResult = retrofitService.delete(url, param);
                break;
            case UPDATE:
                callResult = retrofitService.put(url, param);
                break;
            case POST_RAM:
                break;
            case PUT_RAM:
                break;
            default:
                break;
        }

        //异步请求
        if (callResult != null) {
            callResult.enqueue(new RequestCallbacks(iError, iFailure, iSuccess, iRequest));
        }

    }
}
