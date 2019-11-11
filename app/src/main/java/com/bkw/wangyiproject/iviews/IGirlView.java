package com.bkw.wangyiproject.iviews;

import com.bkw.wangyiproject.entity.GirlBean;

import java.util.List;

public interface IGirlView {
    void showGirlData(List<GirlBean> girlBeans);

    void showUserInfo();
}
