package com.itheima.test;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    @Test
    public void testSelectAll() throws IOException {

        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        //5.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectById() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        Integer id = 1;
        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);
        //5.关闭资源
        sqlSession.close();
    }
    @Test
    /**
     * selectByCondition() 多条件动态查询测试
     */
    public void selectByCondition() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        //4.1 接收用户输入的数据
        int status = 1;
        String companyName = "华为";
//        String brandName  ="华为";

        //4.2 数据处理
        companyName = "%" + companyName + "%";//处理成配套模糊查询
//        brandName = "%" + brandName + "%"; //处理成配套模糊查询

        //三种传递参数的方式分别对应Mapper接口中重载的方法
        //执行方式一：@Param映射散装参数
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);

        //执行方式二：对象brand封装参数
//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        //执行方式三：Map集合封装参数
        Map map = new HashMap();
        map.put("status",status);
        map.put("companyName",companyName);
//        map.put("brandName",brandName);
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);
        //5.关闭资源
        sqlSession.close();
    }


    @Test
    /**
     * selectByConditionSingle() 单条件动态查询测试
     * 若未输入查询条件，默认执行恒等式
     */
    public void selectByConditionSingle() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        //4.1 接收用户输入的数据
//        int status = 1;
//        String companyName = "华为";
        String brandName  ="华为";

        //4.2 数据处理
//        companyName = "%" + companyName + "%";//处理成配套模糊查询
//        brandName = "%" + brandName + "%"; //处理成配套模糊查询

        //三种传递参数的方式分别对应Mapper接口中重载的方法
        //执行方式一：@Param映射散装参数
//        List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);

//        执行方式二：对象brand封装参数
        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);

//        //执行方式三：Map集合封装参数
//        Map map = new HashMap();
//        map.put("status",status);
//        map.put("companyName",companyName);
//        map.put("brandName",brandName);
//        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);
        //5.关闭资源
        sqlSession.close();
    }
    @Test
    /**
     * add() 增加方法
     */
    public void add() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置自动提交

        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        //4.1 接收用户输入的数据
        String brandName  = "百度搜索";
        String companyName = "百度5";
        int  ordered = 10;
        String description = "百度一下，你就知道";
        int status  = 1;
//        //4.2 数据处理
//        companyName = "%" + companyName + "%";//处理成配套模糊查询
//        brandName = "%" + brandName + "%"; //处理成配套模糊查询

//        执行方式二：对象brand封装参数
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setOrdered(ordered);
        brand.setDescription(description);

        brandMapper.add(brand);
        List<Brand> brands = brandMapper.selectAll();

        System.out.println(brands);

        // 提交事物
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }
    @Test
    /**
     * update() 修改全部数据的方法
     */
    public void update() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置自动提交

        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        //4.1 接收用户输入的数据
        String brandName  = "波导手机";
        String companyName = "波导手机";
        int  ordered = 200;
        String description = "波导手机给我改回来啊！";
        int status  = 0;
        int id = 5;
//        //4.2 数据处理
//        companyName = "%" + companyName + "%";//处理成配套模糊查询
//        brandName = "%" + brandName + "%"; //处理成配套模糊查询

//        执行方式二：对象brand封装参数
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setId(id);

        int count = brandMapper.update(brand);
        System.out.println(count);

        // 提交事物
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }

    @Test
    /**
     * deleteById() 根据指定的id删除
     */
    public void deleteById() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置自动提交

        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        //4.1 接收用户输入的数据
//        String brandName  = "波导手机";
//        String companyName = "波导手机";
//        int  ordered = 200;
//        String description = "波导手机给我改回来啊！";
//        int status  = 0;
        int id = 3;
//        //4.2 数据处理
//        companyName = "%" + companyName + "%";//处理成配套模糊查询
//        brandName = "%" + brandName + "%"; //处理成配套模糊查询

//        执行方式二：对象brand封装参数
//        Brand brand = new Brand();
//        brand.setStatus(status);
////        brand.setCompanyName(companyName);
////        brand.setBrandName(brandName);
//        brand.setOrdered(ordered);
//        brand.setDescription(description);
//        brand.setId(id);
        brandMapper.deleteById(id);

        // 提交事物
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }

    @Test
    /**
     * deleteByIds() 根据给定的的ids[]批量删除
     */
    public void deleteByIds() throws IOException {
        //1.加载mybatis核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取SqlSession对象，用它执行Sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置自动提交

        //3.1获取BrandMapper的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //4.执行方法
        //4.1 接收用户输入的数据
//        String brandName  = "波导手机";
//        String companyName = "波导手机";
//        int  ordered = 200;
//        String description = "波导手机给我改回来啊！";
//        int status  = 0;
        int[] ids = {6,7,8,9,10};
//        //4.2 数据处理
//        companyName = "%" + companyName + "%";//处理成配套模糊查询
//        brandName = "%" + brandName + "%"; //处理成配套模糊查询

//        执行方式二：对象brand封装参数
//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setOrdered(ordered);
//        brand.setDescription(description);
//        brand.setId(id);
        brandMapper.deleteByIds(ids);

        // 提交事物
        sqlSession.commit();

        //5.关闭资源
        sqlSession.close();
    }
}
