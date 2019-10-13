package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.entity.FarmProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 农产品列表显示【让用户查看】
 */
@Mapper
public interface FarmProductListMapper {

    /**
     * 根据农产品id查询农产品列表数据
     * @param page 起始条数
     * @param limit 当前页面中的总数
     * @return 匹配的农产品信息列表，如果没有则返回null
     */
    List<FarmProduct> findListOfPage(@Param("page") Integer page,@Param("limit") Integer limit);

    /**
     * 根据农产品id查询农产品详细信息
     * @param id 商家id
     * @return 匹配的农产品的数据，如果没有则返回null
     */
    FarmProduct findById(Integer id);

    /**
     * 查询农产品列表总数
     * @return 农产品列表总数
     */
    Integer findListCount();
}
