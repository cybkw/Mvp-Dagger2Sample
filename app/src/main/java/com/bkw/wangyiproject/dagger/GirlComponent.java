package com.bkw.wangyiproject.dagger;

import com.bkw.wangyiproject.MainActivity;

import dagger.Component;

/**
 * 注入对象--》MainActivity
 *
 * @author bkw
 */
@Component(modules = GirlPresenterModule.class)
public interface GirlComponent {

    /**
     * 注入
     *
     * @param mainActivity 目标V层
     */
    void inject(MainActivity mainActivity);
}
