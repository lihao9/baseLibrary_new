//package com.jiacaizichan.baselibrary.inteface;
//
//import com.google.gson.JsonObject;
//
//import retrofit2.Call;
//import retrofit2.http.POST;
//import retrofit2.http.Query;
//import rx.Observable;
//
//public interface RetrofitService {
//
//    //单纯使用retrofit接口定义
//    @POST("mine/getCouponList.json")
//    Call<JsonObject> getZhihuDailyRetrofitOnly(@Query("sessionId") String sessionId);
//
//    //使用retrofit+RxAndroid的接口定义
//    @POST("mine/getCouponList.json")
//    Observable<JsonObject> getZhihuDaily(@Query("sessionId") String sessionId);
//
//}
