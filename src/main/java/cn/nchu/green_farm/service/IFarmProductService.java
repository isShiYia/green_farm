package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.exception.InsertException;

import java.util.List;

/**
 * @author Jianhzhong
 */
public interface IFarmProductService {

    FarmProduct create(FarmProduct farmProduct) throws InsertException;

    /**
     *
     * @return 新到好货数据
     */
    List<FarmProduct> getNewList();

    /**
     *
     * @return 热销排行数据
     */
    List<FarmProduct> getHotList();
}
