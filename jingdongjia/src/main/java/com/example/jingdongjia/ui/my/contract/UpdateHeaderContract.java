package com.example.jingdongjia.ui.my.contract;

import com.example.jingdongjia.ui.base.BaseContract;

public interface UpdateHeaderContract {
    interface View extends BaseContract.BaseView{
        void updateSuccess(String code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void updateHeader(String uid, String filePath);
    }

}
