package com.lagou.dao;

import com.lagou.domain.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper {


//    @param emp_id
//    @return
//    @return Employee
    @Select("select * from employee where emp_id = #{emp_id}")
    public Employee findById(Integer emp_id);
    /**
     * 查询所有用户的方法
     * @return
     */
    @Select("select * from employee")
    public List<Employee> findAll();
    /**
     * 添加新的用户的方法
     * @param employee
     * @return
     */
    @Insert("insert into employee(emp_name,dept_id,job_name,join_date,telephone) values(#{emp_name},#{dept_id},#{job_name},#{join_date},#{telephone})")
    @Options(useGeneratedKeys = true, keyProperty = "emp_id")
    int addEmployee( Employee employee);

    /**
     * 添加删除用户的方法
     * @param emp_id
     * @return
     */
    @Delete("delete employee where emp_id = #{emp_id}")
    int deleteEmployee(Integer emp_id);
    /**
     * 添加删除用户的方法
     * @param employee
     * @return
     */
    @Update("update employee set role_name = emp_name=#{emp_name},dept_id=#{dept_id},job_name=#{job_name},join_date=#{join_date},telephone=#{telephone}  where emp_id = #{emp_id}")
    int updateEmployee(Employee employee);


}
