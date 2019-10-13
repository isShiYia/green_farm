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
    public void findListOfPage() {
        Integer page = 1;
        Integer limit = 6;
        List<FarmProduct> list  = farmProductListMapper.findListOfPage(page, limit);
        System.err.println("BEGIN.");
        for (FarmProduct farmProduct : list) {
            System.err.println("farmProduct=" + farmProduct);
        }
        System.err.println("END.");

    }

    @Test
    public void findListCount() {
        Integer count = farmProductListMapper.findListCount();
        System.err.println("count=" +count);
    }

    @Test
    public void findById() {
        Integer id = 10000001;
        FarmProduct farmProduct = farmProductListMapper.findById(id);
        System.err.println("farmProduct" + farmProduct);

    }
}
