package com.mayikt.demo.test.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    @Insert("insert into users values(null,#{name},#{age});")
    int addUser(@Param("name") String name, @Param("age") Integer age);


}
