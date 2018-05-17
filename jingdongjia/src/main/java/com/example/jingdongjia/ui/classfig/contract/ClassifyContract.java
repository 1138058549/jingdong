package com.example.jingdongjia.ui.classfig.contract;

import com.example.jingdongjia.bean.CatagoryBean;
import com.example.jingdongjia.bean.ProductCatagoryBean;
import com.example.jingdongjia.ui.base.BaseContract;

public interface ClassifyContract {
    interface View extends BaseContract.BaseView {
        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProductCatagory(String cid);

        void getCatagory();
    }
}
