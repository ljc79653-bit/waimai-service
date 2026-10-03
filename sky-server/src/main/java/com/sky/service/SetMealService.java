package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetMealService {

    /**
     * 新增套餐
     */
    void save(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 删除套餐
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和关联的菜品数据
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    void startOrStop(Integer status, Long id);
}
