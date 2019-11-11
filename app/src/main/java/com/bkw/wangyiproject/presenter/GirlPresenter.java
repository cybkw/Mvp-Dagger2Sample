package com.bkw.wangyiproject.presenter;

import android.util.Log;

import com.bkw.core.network.rx.databus.RegisterRxBus;
import com.bkw.wangyiproject.entity.GirlBean;
import com.bkw.wangyiproject.iviews.IGirlView;
import com.bkw.wangyiproject.task.GirlTaskImpl;
import com.bkw.wangyiproject.task.IGirlTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GirlPresenter<T extends IGirlView> {
    /**
     * View层的定义
     */
    private WeakReference<T> view;

    /**
     * Task层的定义
     */
    private IGirlTask iGirlTask;

    public GirlPresenter(T view) {
        this.view = new WeakReference<>(view);

        iGirlTask = new GirlTaskImpl();
        //加载数据
        iGirlTask.loadGirlData();

        iGirlTask.request();
    }

    @RegisterRxBus
    public void showGirlData(ArrayList<GirlBean> arrayList) {
        //其他的事件逻辑等等。。。
        Log.e("TAG", "showGirlData" + arrayList.size());
        //把结果给View层
        view.get().showGirlData(arrayList);
    }

    @RegisterRxBus
    public void showUserInfo() {
        view.get().showUserInfo();
    }
}
