package com.mayikt.demo.test.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderMapper {
    @Insert("insert into order_number values(null,#{number});")
    int inserOrder(@Param("number") String number);

    List<Map<String,Object>> findUser();
}
