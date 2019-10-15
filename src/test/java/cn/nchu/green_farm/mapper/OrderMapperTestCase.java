package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.entity.Order;
import cn.nchu.green_farm.entity.OrderItem;
import cn.nchu.green_farm.vo.CartVO;
import cn.nchu.green_farm.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderMapperTestCase {

    @Resource
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(9);
        order.setRecvName("小刘老师");
        order.setRecvPhone("13899990000");
        order.setRecvDistrict("内蒙古兴安盟乌兰浩特市");
        order.setRecvAddress("幸福小区3单元304");
        Integer rows = orderMapper.insertOrder(order);
        System.err.println("rows=" +rows);
    }

    @Test
    public void insertOrderItem () {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setGoodsTitle("HUAWEI手机");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findById() {
        Integer id = 5;
        OrderVO orderVO = orderMapper.findById(id);
        System.err.println("orderVO" + orderVO);
    }

}
