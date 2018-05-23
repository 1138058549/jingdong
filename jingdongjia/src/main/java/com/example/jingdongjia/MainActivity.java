package com.example.jingdongjia;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jingdongjia.froment.HomePageFragment;
import com.example.jingdongjia.ui.classfig.ClassifyFragment;
import com.example.jingdongjia.ui.my.MyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton mRbHomepage;
    private RadioButton mRbClass;
    private RadioButton mRbFind;
    private RadioButton mRbShopCar;
    private RadioButton mRbMine;
    private RadioGroup mRg;
    private FrameLayout mFlContent;
    private HomePageFragment homePageFragment;
    private FragmentManager fragmentManager;
    private ClassifyFragment classifyFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        homePageFragment = new HomePageFragment();
        fragmentManager = getSupportFragmentManager();
        classifyFragment = new ClassifyFragment();
        myFragment = new MyFragment();
        fragmentManager.beginTransaction().replace(R.id.flContent,homePageFragment).commit();

        mRbHomepage.setChecked(true);

    }

    private void initView() {
        mRbHomepage = (RadioButton) findViewById(R.id.rbHomepage);
        mRbHomepage.setOnClickListener(this);
        mRbClass = (RadioButton) findViewById(R.id.rbClass);
        mRbClass.setOnClickListener(this);
        mRbFind = (RadioButton) findViewById(R.id.rbFind);
        mRbFind.setOnClickListener(this);
        mRbShopCar = (RadioButton) findViewById(R.id.rbShopCar);
        mRbShopCar.setOnClickListener(this);
        mRbMine = (RadioButton) findViewById(R.id.rbMine);
        mRbMine.setOnClickListener(this);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFlContent = (FrameLayout) findViewById(R.id.flContent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rbHomepage:
                //首页
                fragmentManager.beginTransaction().replace(R.id.flContent,homePageFragment).commit();
                break;
            case R.id.rbClass:
                fragmentManager.beginTransaction().replace(R.id.flContent, classifyFragment).commit();
                break;
            case R.id.rbFind:
                break;
            case R.id.rbShopCar:
                break;
            case R.id.rbMine:
                fragmentManager.beginTransaction().replace(R.id.flContent, myFragment).commit();
                break;
        }
    }
}
