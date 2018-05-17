package com.example.jingdongjia.ui.classfig.contract;

import com.example.jingdongjia.ui.base.BaseContract;

public interface AddCartContract {
    interface View extends BaseContract.BaseView{
        void onSuccess(String str);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void  addCart(String uid, String pid, String token);
    }
}
