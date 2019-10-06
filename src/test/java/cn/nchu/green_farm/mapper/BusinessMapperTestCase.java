package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Business;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessMapperTestCase {
    @Autowired
    private BusinessMapper businessMapper;
    @Test
    public void addnew(){
        Business business = new Business();
        business.setBusinessName("大巴");
        business.setPassword("111");
        business.setSalt("1234");
        business.setEmail("admin@nchu.cn");
        business.setPhone("13988889999");
        Integer addnew = businessMapper.addnew(business);
        System.out.println(addnew);
    }
    @Test
    public void getByName(){
        String business_name="大巴";
        Business byBusinessName = businessMapper.getByBusinessName(business_name);
        System.out.println(byBusinessName);
    }

}
