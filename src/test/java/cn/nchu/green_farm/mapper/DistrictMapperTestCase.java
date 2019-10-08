package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.District;
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
public class DistrictMapperTestCase {

    @Resource
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        // String parent = "86";
        String parent = "86";
        List<District> result = districtMapper.findByParent(parent);
        System.err.println("BEGIN.");
        /*
            生成foreach：iter + enter
         */
        for (District district : result) {
            System.err.println(district);
        }
        System.err.println("END.");
    }

    @Test
    public void findByCode() {
        String code = "440000";
//        String code = "371999"; // 没有的值，返回null
        District district = districtMapper.findByCode(code);
        System.err.println("district=" + district);
    }



}
