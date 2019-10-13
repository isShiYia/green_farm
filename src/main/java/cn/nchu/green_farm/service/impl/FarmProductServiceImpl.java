package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.mapper.FarmProductListMapper;
import cn.nchu.green_farm.mapper.FarmProductMapper;
import cn.nchu.green_farm.service.IFarmProductListService;
import cn.nchu.green_farm.service.IFarmProductService;
import cn.nchu.green_farm.service.exception.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Choococo
 */
@Service
public class FarmProductServiceImpl implements IFarmProductService {

    @Autowired
    FarmProductMapper farmProductMapper;
    @Autowired
    BusinessServiceImpl businessService;
    @Override
    public FarmProduct create(FarmProduct farmProduct) throws InsertException {
        //给商品随机一个优先级
        Random random = new Random();
        int i = random.nextInt(100);
        random.nextInt(100);
        farmProduct.setPriority(i);
        //产品状态初始化为0：审核中
        farmProduct.setStatus(0);
        Business business = businessService.findById(farmProduct.getBid());
        // 对日志进行 封装
        farmProduct.setCreatedUser(business.getBusinessName());
        farmProduct.setCreatedTime(new Date());
        farmProduct.setModifiedUser(business.getBusinessName());
        farmProduct.setModifiedTime(new Date());
        addnew(farmProduct);
        return farmProduct;
    }
    private void addnew(FarmProduct farmProduct){
        farmProductMapper.addnew(farmProduct);
    }


}
