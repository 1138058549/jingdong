package com.example.jingdongjia.net;



import com.example.jingdongjia.bean.AdBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AdApiService {
    @GET("ad/getAd")
    Observable<AdBean> getAd();
}
