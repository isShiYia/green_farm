package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.District;
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
public class DistrictSerivceTestCase {

    @Resource
    private IDistrictService districtService;


    @Test
    public void getListByParent() {
        try {
            String parent = "86";
            List<District> result  = districtService.getListByParent(parent);
            System.err.println("BEGIN.");
            for (District district : result) {
                System.err.println(district);
            }
            System.err.println("END.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }

    @Test
    public void getByCode() {
        try {
            String code = "320000";
            District district  = districtService.getByCode(code);
            System.err.println("district=" + district);
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }



}
