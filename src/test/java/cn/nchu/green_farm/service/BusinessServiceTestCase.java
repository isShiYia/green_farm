package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.service.exception.ServiceException;
import cn.nchu.green_farm.service.impl.BusinessServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessServiceTestCase {
    @Autowired
    private BusinessServiceImpl businessService;
@Test
    public void reg() {
        try {
            Business business = new Business();
            business.setBusinessName("辛巴");
            business.setPassword("111");
            business.setSalt("1234");
            business.setEmail("admin@nchu.cn");
            business.setPhone("13988889999");
            Business reg = businessService.reg(business);
            System.out.println(reg);
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());


        }
    }
    @Test
    public void updatePassword(){
    try {
        Integer businessId=17;
        String oldPassword="111";
        String newPassword="1111";
        businessService.updataPassword(businessId,newPassword,oldPassword);

    }catch (ServiceException e){
        System.err.println("错误类型:" + e.getClass().getName());
        System.err.println("错误描述:" + e.getMessage());
    }
    }
}
