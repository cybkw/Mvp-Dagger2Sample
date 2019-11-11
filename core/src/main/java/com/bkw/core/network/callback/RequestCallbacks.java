package com.bkw.core.network.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback {

    private IError iError;

    private IFailure iFailure;

    private ISuccess iSuccess;

    private IRequest iRequest;

    public RequestCallbacks(IError iError, IFailure iFailure, ISuccess iSuccess, IRequest iRequest) {
        this.iError = iError;
        this.iFailure = iFailure;
        this.iSuccess = iSuccess;
        this.iRequest = iRequest;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful() && call.isExecuted() && iSuccess != null) {
            iSuccess.onSuccess(response.message());
        } else if (iError != null) {
            iError.onError(response.code(), response.message());
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (iFailure != null) {
            iFailure.onFailure(t);
        }

        if (iRequest != null) {
            //请求结束
            iRequest.onRequestEnd();
        }
    }
}
