package com.example.jingdongjia.ui.classfig.contract;

import com.example.jingdongjia.bean.ProductsBean;
import com.example.jingdongjia.ui.base.BaseContract;

import java.util.List;

public interface ListContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(List<ProductsBean.DataBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProducts(String pscid);
    }
}
