package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    void deleteByIds(@Param("ids") int[] ids);
    /*
    * 分页查询
    * */
    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin,@Param("size")int size);
    /*
     * 分页查询的总数
     * */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();
    /*
     * 分页查询和条件查询
     * */
    List<Brand> selectByPageAndCondition(@Param("begin")int begin,@Param("size")int size,@Param("brand")Brand brand);
    /*
     * 分页查询和条件查询的总数
     * */
    int selectTotalCountByCondition(@Param("brand")Brand brand);

    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(@Param("id") int id);

    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(@Param("id") int id);

    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id = #{id}")
    void updateById(Brand brand);
}
