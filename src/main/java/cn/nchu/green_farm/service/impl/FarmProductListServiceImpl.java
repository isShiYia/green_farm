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
public class FarmProductListServiceImpl implements IFarmProductListService {

    @Resource
    private FarmProductListMapper farmProductListMapper;

    @Override
    public List<FarmProduct> getListOfPage(Integer page,Integer limit) {
        return findListOfPage(page,limit);
    }

    @Override
    public Integer getListOfCount() {
        return findListOfCunt();
    }

    @Override
    public FarmProduct getById(Integer id) {
        return findById(id);
    }

    /**
     * 根据农产品id查询农产品列表数据
     * @return 匹配的农产品信息列表，如果没有则返回null
     */
    private List<FarmProduct> findListOfPage(Integer page, Integer limit) {
        return farmProductListMapper.findListOfPage(page,limit);
    }

    /**
     * 查询农产品总数
     * @return 农产品总数
     */
    private Integer findListOfCunt() {
        return farmProductListMapper.findListCount();
    }

    /**
     * 根据农产品id查询农产品数据
     * @param id 农产品id
     * @return
     */
    private FarmProduct findById(Integer id) {
        return farmProductListMapper.findById(id);
    }


}
