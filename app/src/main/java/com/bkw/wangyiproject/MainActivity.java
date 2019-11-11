package com.bkw.wangyiproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.bkw.core.network.rx.databus.RxBus;
import com.bkw.wangyiproject.adapter.GirlAdapter;
import com.bkw.wangyiproject.dagger.DaggerGirlComponent;
import com.bkw.wangyiproject.dagger.GirlPresenterModule;
import com.bkw.wangyiproject.entity.GirlBean;
import com.bkw.wangyiproject.iviews.IGirlView;
import com.bkw.wangyiproject.presenter.GirlPresenter;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements IGirlView {

    private ListView listView;

    private GirlAdapter mAdapter;

    /**
     * P层交给dagger2
     */
    @Inject
    GirlPresenter girlPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_item);

        //-给P层绑定对应View层且注入目标
        //girlPresenter=new GirlPresenter(this);
        DaggerGirlComponent
                .builder()
                .girlPresenterModule(new GirlPresenterModule(this))
                .build()
                .inject(this);

        //注册总线 ,将P层注册
        RxBus.getInstance().register(girlPresenter);
    }

    @Override
    public void showGirlData(List<GirlBean> girlBeans) {
        mAdapter = new GirlAdapter(this, girlBeans);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void showUserInfo() {
        Toast.makeText(this, "showUserInfo", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除总线
        RxBus.getInstance().unRegister(this);
    }
}
