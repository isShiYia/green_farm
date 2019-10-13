package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.FarmProduct;

import java.util.List;

/**
 * 用户查看农产品列表业务层接口
 * @author Choococo
 */
public interface IFarmProductListService {

    /**
     * 用户查看商品列表
     * @param page
     * @param limit
     * @return 匹配的商品列表数据，如果没有则返回null
     */
    List<FarmProduct> getListOfPage(Integer page, Integer limit);

    /**
     * 获取农产品总数
     * @return 农产品总数
     */
    Integer getListOfCount();

    /**
     * 根据id查询商品详情
     * @param id 商品的id
     * @return 商品的详情，如果没有匹配的数据，则返回null
     */
    FarmProduct getById(Integer id);

}
