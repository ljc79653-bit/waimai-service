package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 增加菜品口味
     */
    void saveWithFlavor(List<DishFlavor> dishFlavorList);

    /**
     * 根据菜品id删除口味
     */
    void deleteByDishId(List<Long> ids);

    /**
     * 根据菜品id查询口味
     */
    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
