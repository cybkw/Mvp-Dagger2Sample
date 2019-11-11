package com.bkw.core;

import com.bkw.core.network.RequestClient;

public class Test {

    public static void main(String[] args) {

    }

    public void testRequest() {
        RequestClient.create()
                .url("")
                .build();
    }
}
