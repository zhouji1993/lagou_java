package com.lagou.controller;
import com.lagou.dao.DeptMapper;
import com.lagou.dao.EmployeeMapper;
import com.lagou.domain.Dept;
import com.lagou.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class MyBatisController {

    @Resource
    DeptMapper deptMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @GetMapping("/employee/queryAll")
    public List<Employee> queryEmpAll(){
        return employeeMapper.findAll();
    }

    @GetMapping("/dept/queryAll")
    public List<Dept> queryDeptAll(){
        return deptMapper.findAll();
    }

    @GetMapping("/employee/delete")
    public Boolean delete(Integer id) {
        if (id == null || id < 1) {
            return false;
        }
        return employeeMapper.deleteEmployee(id) > 0;
    }


    @GetMapping("/employee/insert")
    public Boolean insert(Employee employee) {
        if (StringUtils.isEmpty(employee.getEmp_name()) || StringUtils.isEmpty(employee.getTelephone())) {
            return false;
        }


        return EmployeeMapper.addEmployee(employee) > 0;
    }

    @GetMapping("/employee/update")
    public Boolean update(Employee employee) {
        if (StringUtils.isEmpty(employee.getEmp_name()) || StringUtils.isEmpty(employee.getTelephone())) {
            return false;
        }


        return EmployeeMapper.updateEmployee(employee) > 0;
    }


}
