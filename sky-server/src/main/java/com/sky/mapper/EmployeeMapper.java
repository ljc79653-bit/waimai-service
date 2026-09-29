package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 员工分页查询
     *
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 新增员工
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into employee (id, username, name, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values (#{id}, #{username}, #{name}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void save(Employee employee);

    /**
     * 更新员工信息
     */
    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);

}
