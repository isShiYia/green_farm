package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Choococo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FarmProductListSerivceTestCase {

    @Resource
    private IFarmProductListService farmProductListService;


    @Test // 收货地址列表
    public void getListOfPage() {
        try {
            Integer page = 2;
            Integer limit = 6;
            List<FarmProduct> list =  farmProductListService.getListOfPage(page, limit);
            System.err.println("BEGIN.");
            for (FarmProduct farmProduct : list) {
                System.err.println("farmProduct=" +farmProduct);
            }
            System.err.println("END.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test //
    public void getListOfCount() {
        try {
            Integer count =  farmProductListService.getListOfCount();
            System.err.println("count=" +count);

        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test //
    public void getById() {
        try {
            Integer id = 10000001;
            FarmProduct farmProduct =  farmProductListService.getById(id);
            System.err.println("farmProduct=" +farmProduct);

        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

}
