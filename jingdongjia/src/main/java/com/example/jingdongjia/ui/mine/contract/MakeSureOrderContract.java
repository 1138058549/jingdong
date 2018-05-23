package com.example.jingdongjia.ui.mine.contract;

import com.example.jingdongjia.bean.AddrsBean;
import com.example.jingdongjia.ui.base.BaseActivity;
import com.example.jingdongjia.ui.base.BaseContract;
import com.example.jingdongjia.ui.mine.presenter.MakeSureOrderPresenter;

import java.util.List;

public interface MakeSureOrderContract {
    interface View extends BaseContract.BaseView {
        void addrsSuccess(List<AddrsBean.DataBean> list);

        void createOrderSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddrs(String uid, String token);

        void createOrder(String uid, String price, String token);
    }
}
