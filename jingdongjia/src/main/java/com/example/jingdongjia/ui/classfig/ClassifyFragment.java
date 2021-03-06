package com.example.jingdongjia.ui.classfig;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.jingdongjia.R;
import com.example.jingdongjia.bean.CatagoryBean;
import com.example.jingdongjia.bean.ProductCatagoryBean;
import com.example.jingdongjia.component.DaggerHttpComponent;
import com.example.jingdongjia.inter.OnItemClickListener;
import com.example.jingdongjia.module.HttpModule;
import com.example.jingdongjia.ui.base.BaseFragment;
import com.example.jingdongjia.ui.classfig.adapter.ElvAdapter;
import com.example.jingdongjia.ui.classfig.adapter.RvLeftAdapter;
import com.example.jingdongjia.ui.classfig.contract.ClassifyContract;
import com.example.jingdongjia.ui.classfig.presenter.ClassifyPresenter;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View{
    private ImageView mIvZxing;
    private RecyclerView mRvLeft;
    private ImageView mIv;
    private ExpandableListView mElv;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_class;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        mIvZxing = (ImageView) view.findViewById(R.id.ivZxing);
        mRvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        mIv = (ImageView) view.findViewById(R.id.iv);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mIv.setBackgroundResource(R.drawable.timg);

        mPresenter.getCatagory();

    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {
        //定义一个集合用于存放title
        List<String> groupList = new ArrayList<>();
        //定义一个集合用于存放title对应的内容
        List<List<ProductCatagoryBean.DataBean.ListBean>> childList = new ArrayList<>();
        List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
        for (int i = 0; i < data.size(); i++) {
            groupList.add(data.get(i).getName());
            List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
            childList.add(list);
        }
        ElvAdapter elvAdapter = new ElvAdapter(getContext(), groupList, childList);
        mElv.setAdapter(elvAdapter);
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
       // productCatagoryBean.getMsg();


    }

    @Override
    public void getCatagorySuccess(final CatagoryBean catagoryBean) {
        List<CatagoryBean.DataBean> data = catagoryBean.getData();
        mRvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeft.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        final RvLeftAdapter adapter = new RvLeftAdapter(getContext(), data);
        mRvLeft.setAdapter(adapter);

        int cid = data.get(0).getCid();
        mPresenter.getProductCatagory(cid + "");

        //设置第一个为默认选中
        adapter.changeCheck(0, true);

        //设置点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //改变DataBean里面check的属性值
                adapter.changeCheck(position, true);
                //请求右侧对应的数据
                mPresenter.getProductCatagory(catagoryBean.getData().get(position).getCid() + "");
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

    }
}
