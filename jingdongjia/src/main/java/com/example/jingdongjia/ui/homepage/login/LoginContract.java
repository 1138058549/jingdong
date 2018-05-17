package com.example.jingdongjia.ui.homepage.login;

import com.example.jingdongjia.bean.UserBean;
import com.example.jingdongjia.ui.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void loginSuccess(UserBean userBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void login(String mobile, String password);
    }
}
