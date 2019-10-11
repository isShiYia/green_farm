package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.mapper.FarmProductListMapper;
import cn.nchu.green_farm.service.IFarmProductListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Choococo
 */
@Service
public class FarmProductServiceImpl implements IFarmProductListService {

    @Resource
    private FarmProductListMapper farmProductListMapper;

    @Override
    public List<FarmProduct> getList() {
        return findListById();
    }

    /**
     * 根据农产品id查询农产品列表数据
     * @return 匹配的农产品信息列表，如果没有则返回null
     */
    private List<FarmProduct> findListById() {
        return farmProductListMapper.findListById();
    }
}
