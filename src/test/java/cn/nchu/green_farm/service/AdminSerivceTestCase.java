package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.Business;
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
public class AdminSerivceTestCase {

    @Resource
    private IAdminBusService adminBusService;

    @Test // 待审核商家列表
    public void getListOfBusiness() {
        try {
            List<Business> list = adminBusService.getListOfBusiness(1,5);
            System.err.println("BEGIN.");
            for (Business business : list) {
                System.err.println("business=" + business);
            }
            System.err.println("END.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test // 审核通过
    public void changeStatusPass() {
        try {
            Integer id =1;
            String modifiedUser = "admin03";
            adminBusService.changeStatusPass(id, modifiedUser);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test // 审核未通过
    public void changeStatusNoPass() {
        try {
            Integer id =1;
            String modifiedUser = "admin04";
            adminBusService.changeStatusNoPass(id, modifiedUser);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

}
