package com.example.jingdongjia.ui.classfig;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.example.jingdongjia.R;
import com.example.jingdongjia.bean.ProductsBean;
import com.example.jingdongjia.component.DaggerHttpComponent;
import com.example.jingdongjia.module.HttpModule;
import com.example.jingdongjia.ui.base.BaseActivity;
import com.example.jingdongjia.ui.classfig.adapter.XrvListAdapter;
import com.example.jingdongjia.ui.classfig.contract.ListContract;
import com.example.jingdongjia.ui.classfig.presenter.ListPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity<ListPresenter> implements ListContract.View {

    private ImageView mIvZxing;
    private XRecyclerView mXrv;
    private boolean isRefresh = true;
    private int pscid;
    private XrvListAdapter adapter;
    public static final int LISTACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        initView();
        mPresenter.getProducts(pscid + "");

    }

    private void initView() {
        mIvZxing = (ImageView) findViewById(R.id.ivZxing);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mXrv.setLayoutManager(linearLayoutManager);
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                mPresenter.getProducts(pscid+"");

            }

            @Override
            public void onLoadMore() {
                //加载
                isRefresh = false;
                mPresenter.getProducts(pscid+"");

            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);

    }

    @Override
    public void onSuccess(List<ProductsBean.DataBean> list) {
        final List<ProductsBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        if(isRefresh){
            adapter = new XrvListAdapter(this, list);
            mXrv.setAdapter(adapter);
            adapter.refresh(tempList);
            mXrv.refreshComplete();//刷新完成

        }else {
            if (adapter!=null){
                adapter.loadMore(tempList);
                mXrv.loadMoreComplete();
            }

        }
        if (adapter==null){
            return;
        }
        adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(ProductsBean.DataBean dataBean) {
                Intent intent = new Intent(ListActivity.this, ListDetailsActivity.class);
                intent.putExtra("bean", dataBean);
                intent.putExtra("flag", LISTACTIVITY);
                startActivity(intent);
            }
        });

    }
}
