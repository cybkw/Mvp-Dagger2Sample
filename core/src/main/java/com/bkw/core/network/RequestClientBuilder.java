package com.bkw.core.network;

import com.bkw.core.network.callback.IError;
import com.bkw.core.network.callback.IFailure;
import com.bkw.core.network.callback.IRequest;
import com.bkw.core.network.callback.ISuccess;

import java.io.File;
import java.util.HashMap;

import okhttp3.RequestBody;

/**
 * @author bkw
 */
public class RequestClientBuilder {
    private HashMap<String, Object> param;

    private String url;
    private IError iError;
    private IFailure iFailure;
    private IRequest iRequest;
    private ISuccess iSuccess;

    /**
     * 请求体
     */
    private RequestBody requestBody;

    //上传-下载
    private File file;
    private String downloadDir;
    private String extension;
    private String fileName;

    public RequestClientBuilder param(HashMap<String, Object> param) {
        this.param = param;
        return this;
    }

    public RequestClientBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RequestClientBuilder error(IError iError) {
        this.iError = iError;
        return this;
    }

    public RequestClientBuilder failure(IFailure iFailure) {
        this.iFailure = iFailure;
        return this;
    }

    public RequestClientBuilder failure(IRequest iRequest) {
        this.iRequest = iRequest;
        return this;
    }

    public RequestClientBuilder success(ISuccess iSuccess) {
        this.iSuccess = iSuccess;
        return this;
    }

    public RequestClientBuilder requestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public RequestClientBuilder file(File file) {
        this.file = file;
        return this;
    }

    public RequestClientBuilder downloadDir(String downloadDir) {
        this.downloadDir = downloadDir;
        return this;
    }

    
    /**
     * 构建
     *
     * @return
     */
    public RequestClient build() {
        return new RequestClient(param, url, iError, iFailure, iRequest, iSuccess, requestBody, file, downloadDir, extension, fileName);
    }
}
