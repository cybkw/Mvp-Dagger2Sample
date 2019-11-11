package com.bkw.wangyiproject.task;

import com.bkw.core.network.rx.databus.RxBus;
import com.bkw.wangyiproject.R;
import com.bkw.wangyiproject.entity.GirlBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Function;

public class GirlTaskImpl implements IGirlTask {
    @Override
    public void loadGirlData() {
        //调用核心层的网络请求

        //模拟数据
        RxBus.getInstance()
                .doProcessInvoke(new Function() {
                    @Override
                    public Object apply(Object o) throws Exception {

                        List<GirlBean> girlBeans = new ArrayList<>();
                        girlBeans.add(new GirlBean("uu", "三颗星", R.mipmap.ic_launcher_round));
                        girlBeans.add(new GirlBean("uu", "三颗星", R.mipmap.ic_launcher_round));
                        girlBeans.add(new GirlBean("uu", "三颗星", R.mipmap.ic_launcher_round));
                        girlBeans.add(new GirlBean("uu", "三颗星", R.mipmap.ic_launcher_round));
                        girlBeans.add(new GirlBean("uu", "三颗星", R.mipmap.ic_launcher_round));

                        //返回数据
                        return girlBeans;
                    }
                });
    }

    /**
     * 请求网络数据
     */
    @Override
    public void request() {

        final HashMap<String, Object> param = new HashMap<>();
        param.put("name", "bkw");
        param.put("age", 12);

//        RxBus.getInstance().doProcessInvoke(
//                RxRequestClient.create()
//                        .url("user/info")
//                        .param(param)
//                        .build()
//                        .post()
//        );

    }
}
