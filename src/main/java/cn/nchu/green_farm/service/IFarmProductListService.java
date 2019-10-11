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
     * @return 匹配的商品列表数据，如果没有则返回null
     */
    List<FarmProduct> getList();


}
