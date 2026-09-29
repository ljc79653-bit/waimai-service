package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.dto.PasswordEditDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult> pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询员工，第{}页，每页{}条，姓名：{}", employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize(), employeePageQueryDTO.getName());
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 新增员工
     */
    @PostMapping
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工：{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }
    /**
     * 启用禁用员工
     */
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, @RequestParam Long id){
        log.info("启用禁用员工：{}，状态：{}", id, status);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 编辑员工
     */
    @PutMapping
    public Result<String> update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑员工：{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

    /**
     * 根据id查询员工
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工：{}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 修改密码
     */
    @PutMapping("/editPassword")
    public Result<String> editPassword(@RequestBody PasswordEditDTO passwordEditDTO){
        log.info("修改密码：{}", passwordEditDTO);
        employeeService.editPassword(passwordEditDTO);
        return Result.success();
    }


}
