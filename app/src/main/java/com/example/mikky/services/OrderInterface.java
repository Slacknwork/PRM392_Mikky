package com.example.mikky.services;

import com.example.mikky.models.Order;
import com.example.mikky.models.OrderDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderInterface {
    @GET("Order")
    Call<List<Order>> getAllOrder();
    @GET("OrderDetail")
    Call<List<OrderDetail>> getAllOrderDetail();
    @GET("Order/Ordid={orderId}")
    Call<Order> getOrder(@Path(value = "orderId", encoded = true)int orderId);
    @GET("OrderDetail/Order/Ordetid={orderId}")
    Call<OrderDetail> getOrderDetailByOrderId(@Path(value = "orderId", encoded = true)int orderId);
    @GET("OrderDetail/Drink/Drid={drinkId}")
    Call<OrderDetail> getOrderDetailByDrinkId(@Path(value = "drinkId", encoded = true)int drinkId);
    @POST("Order/create")
    Call<Order> createOrder(@Body Order order);
    @POST("OrderDetail/create")
    Call<OrderDetail> createOrderDetail(@Body OrderDetail orderDetail);
}
