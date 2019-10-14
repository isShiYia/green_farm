package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.vo.CartVO;
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
public class CartMapperTestCase {

    @Resource
    private CartMapper cartMapper;

    @Test // 增加收货地址数据【测试成功】
    public void addnew() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setPid(1);
        cart.setPrice(20.0);
        cart.setCount(10);
        Date now = new Date();
        cart.setCreatedUser("user01");
        cart.setModifiedUser("user01");
        cart.setCreatedTime(now);
        cart.setModifiedTime(now);
        Integer rows = cartMapper.addnew(cart);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findByUidAndPid() {
        Integer uid =1;
        Integer pid = 1;
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        System.err.println("cart=" + cart);
    }

    @Test
    public void updateCount() {
        Integer id = 1;
        Integer count = 11;
        String modifiedUser = "user02";
        Date modifiedTime = new Date();
        Integer rows = cartMapper.updateCount(id, count, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findByUid() {
        Integer uid = 1;
        List<CartVO> list = cartMapper.findByUid(uid);
        System.err.println("BEGIN.");
        for (CartVO cartVO : list) {
            System.err.println("cartVO=" + cartVO);
        }
        System.err.println("END.");
    }

}
