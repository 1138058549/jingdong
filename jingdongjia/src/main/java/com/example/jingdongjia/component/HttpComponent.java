package com.example.jingdongjia.component;

import com.example.jingdongjia.froment.HomePageFragment;
import com.example.jingdongjia.module.HttpModule;
import com.example.jingdongjia.ui.LoginActivity;
import com.example.jingdongjia.ui.classfig.ClassifyFragment;
import com.example.jingdongjia.ui.classfig.ListActivity;
import com.example.jingdongjia.ui.classfig.ListDetailsActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(LoginActivity loginActivity);

    void inject(HomePageFragment homePageFragment);

    void inject(ClassifyFragment classifyFragment);

    void inject(ListActivity listActivity);

    void inject(ListDetailsActivity listDetailsActivity);
}
