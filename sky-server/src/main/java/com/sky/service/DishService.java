package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     */
    void save(DishDTO dishDTO);

    /**
     * 菜品分页查询
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询菜品
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 启用，禁用菜品
     */
    void startOrstop(Integer status, Long id);

    /**
     * 修改菜品
     */
    void update(DishDTO dishDTO);
}
