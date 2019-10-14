package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.service.exception.ServiceException;
import cn.nchu.green_farm.vo.CartVO;
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
public class CartSerivceTestCase {

    @Resource
    private ICartService cartService;

    @Test
    public void addToCart() {
        try {
            String username = "user04";
            Cart cart = new Cart();
            cart.setUid(1);
            cart.setPid(2);
            cart.setPrice(900.0);
            cart.setCount(7);
            cartService.addToCart(username, cart);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test
    public void getByUid() {
        try {
            Integer uid =1;
            List<CartVO> list = cartService.getByUid(uid);
            System.err.println("BEGIN.");
            for (CartVO cartVO : list) {
                System.err.println("cartVO=" +cartVO);
            }
            System.err.println("END.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass());
            System.err.println("错误描述:" + e.getMessage());
        }
    }
}
