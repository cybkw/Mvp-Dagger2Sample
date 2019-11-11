package com.bkw.wangyiproject.dagger;

import com.bkw.wangyiproject.MainActivity;
import com.bkw.wangyiproject.presenter.GirlPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * 对P层进行包裹。
 *
 * @author bkw
 */
@Module
public class GirlPresenterModule {

    /**
     * 对应的V层，接口IGirlView的实现类
     */
    private MainActivity mView;

    public GirlPresenterModule(MainActivity mView) {
        this.mView = mView;
    }

    @Provides
    public GirlPresenter provider() {
        return new GirlPresenter(mView);
    }
}
