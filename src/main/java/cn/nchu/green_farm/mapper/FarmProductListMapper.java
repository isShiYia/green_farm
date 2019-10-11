package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.entity.FarmProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 农产品列表显示【让用户查看】
 */
@Mapper
public interface FarmProductListMapper {

    /**
     * 根据农产品id查询农产品列表数据
     * @return 匹配的农产品信息列表，如果没有则返回null
     */
    List<FarmProduct> findListById();

    /**
     * 根据商家id查询商家信息（这里主要是用户名）
     * @param bid 商家id
     * @return 匹配的商家的数据，如果没有则返回null
     */
    Business findByBid(Integer bid);
}
