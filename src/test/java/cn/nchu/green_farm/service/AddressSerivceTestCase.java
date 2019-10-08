package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.User;
import cn.nchu.green_farm.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Choococo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressSerivceTestCase {

    @Resource
    private IAddressService addressService;

    @Test
    public void create() {
        try {
            String username = "Admin";
            Address address = new Address();
            // 这里用户的id好只能从登陆后的session中获取到
            // 所以我们只能在控制器层得到真实的uid数据
            // 这里面的数据都是我们认为设定的，为测试数据
            address.setUid(3);
            address.setName("小马同学");
            address.setProvince("440000");
            address.setCity("440300");
            address.setArea("440305");
            Address result = addressService.create(address,username);
            System.err.println("result=" + result );
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test // 收货地址列表
    public void getListByUid() {
        try {
            Integer uid = 2;
            List<Address> list =  addressService.getListByUid(uid);
            System.err.println("BEGIN.");
            for (Address address : list) {
                System.err.println("address=" + address);
            }
            System.err.println("END.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

}
