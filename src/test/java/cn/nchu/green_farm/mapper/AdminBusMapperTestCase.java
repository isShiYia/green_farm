package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Business;
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
public class AdminBusMapperTestCase {

    @Resource
    private AdminBusMapper adminBusMapper;

    @Test // 增加收货地址数据【测试成功】
    public void findBusinessData() {
        List<Business> list = adminBusMapper.findBusinessData(1,5);
        System.err.println("OK.");
        for (Business business : list) {
            System.err.println("business=" +business);
        }
        System.err.println("END.");
    }


    @Test
    public void updateStatusPass() {
        Integer id =1;
        String modifiedUser = "admin01";
        Date modifiedTime = new Date();
        Integer rows = adminBusMapper.updateStatusPass(id, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateStatusNoPass() {
        Integer id =1;
        String modifiedUser = "admin02";
        Date modifiedTime = new Date();
        Integer rows = adminBusMapper.updateStatusNoPass(id, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findBusById() {
        Integer id= 1;
        Business business = adminBusMapper.findBusById(id);
        System.err.println("business=" + business);
    }
}
