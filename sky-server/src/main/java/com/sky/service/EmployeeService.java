package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 员工分页查询
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 新增员工
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 启用禁用员工
     */
    void startOrStop(Integer status, Long id);

    /**
     * 编辑员工
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 根据id查询员工
     */
    Employee getById(Long id);

    /**
     * 修改密码
     */
    void editPassword(PasswordEditDTO passwordEditDTO);

}
