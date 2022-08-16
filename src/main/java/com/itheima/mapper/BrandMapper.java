package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
     List<Brand> selectAll();

     /**
      * 查看详情根据id
      * @return
      */
     Brand selectById(int id);


    /**
     * 条件查询
     *  参数接收
     *      1.散装参数：如果方法中有多个参数需要@Param("SQL参数占位符名称")
     *      2.对象参数：对象的属性名要和SQL参数占位符名称一致
     *      3.Map集合参数
     *
     */
//     List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

//     List<Brand> selectByCondition(Brand brand);
     List<Brand> selectByCondition(Map map);

    /**
     * 单条件动态查询
     * @param brand
     * @return
     */
     List<Brand> selectByConditionSingle(Brand brand);

    /**
     * 添加方法
     */
    void add(Brand brand);

    /**
     * 修改全部字段的方法
     */

    int update(Brand brand);

    /**
     * 根据id删除字段的方法
     */
    void deleteById(int id);

    /**
     * 根据id批量删除字段的方法
     */
    void deleteByIds(@Param("ids") int[] ids);
//    void deleteByIds( int[] ids);
}
