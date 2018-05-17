package com.example.jingdongjia.ui.homepage;

import com.example.jingdongjia.bean.AdBean;
import com.example.jingdongjia.bean.CatagoryBean;
import com.example.jingdongjia.ui.base.BaseContract;

public interface HomPageContract {
    interface View extends BaseContract.BaseView {
        void getAdSuccess(AdBean adBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAd();

        void getCatagory();
    }
}
