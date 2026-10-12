package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    void add(ShoppingCartDTO shoppingCartDTO);

    // 查看购物车列表
    List<ShoppingCart> list();

    // 清空购物车
    void clean();

    // 删除购物车的一件商品
    void sub(ShoppingCartDTO shoppingCartDTO);
}
