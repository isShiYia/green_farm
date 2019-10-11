package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.FarmProduct;
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
public class FarmProductListMapperTestCase {

    @Resource
    private FarmProductListMapper farmProductListMapper;


    @Test //
    public void findListById() {
        List<FarmProduct> list  = farmProductListMapper.findListById();
        System.err.println("BEGIN.");
        for (FarmProduct farmProduct : list) {
            System.err.println("farmProduct=" + farmProduct);
        }
        System.err.println("END.");

    }


}
