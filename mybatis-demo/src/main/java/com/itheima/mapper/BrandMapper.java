package com.itheima.mapper;

import com.itheima.pojo.Brand;

import java.util.List;

public interface BrandMapper {
     List<Brand> selectAll();

     /**
      * 查看详情根据id
      * @return
      */
     Brand selectById(int id);

}
