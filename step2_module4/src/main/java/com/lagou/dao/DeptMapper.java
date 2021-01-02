package com.lagou.dao;

import com.lagou.domain.Dept;
import org.apache.ibatis.annotations.Select;

public interface DeptMapper {

    @Select("select * from dept where dept_id = #{dept_id}")
    public Dept findById(Integer dept_id);
}
