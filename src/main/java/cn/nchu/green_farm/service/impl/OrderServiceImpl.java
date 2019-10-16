package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.Order;
import cn.nchu.green_farm.entity.OrderItem;
import cn.nchu.green_farm.mapper.OrderMapper;
import cn.nchu.green_farm.service.IAddressService;
import cn.nchu.green_farm.service.ICartService;
import cn.nchu.green_farm.service.IOrderService;
import cn.nchu.green_farm.service.exception.AddressNotFoundException;
import cn.nchu.green_farm.service.exception.InsertException;
import cn.nchu.green_farm.vo.CartVO;
import cn.nchu.green_farm.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Choococo
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Resource
    private OrderMapper orderMapper;// 使用自己的mapper
    @Autowired
    private IAddressService addressService;// 使用其他类的service
    @Autowired
    private ICartService cartService;

    @Override
    @Transactional
    public Order createOrder(Integer uid, String username,Integer addressId, Integer[] cartIds)  throws InsertException {

        // 创建Date对象
        Date now = new Date();
        // 声明pay变量
        Double pay = 0.0;
        List<CartVO> carts = cartService.getByIds(cartIds);
        // 创建List<OrderItem> orderItems
        List<OrderItem> orderItems = new ArrayList<>();
        // 遍历集合
        for (CartVO cartVO : carts) {
            // 计算总价pay
            pay += cartVO.getNewPrice() * cartVO.getCount();
            // -- 创建OrderItem
            OrderItem item = new OrderItem();
            // item属性:goods 5 ,OK
            item.setGoodsId(cartVO.getPid());
            item.setGoodsTitle(cartVO.getTitle());
            item.setGoodsImage(cartVO.getImage());
            item.setGoodsPrice(cartVO.getNewPrice());
            item.setGoodsCount(cartVO.getCount());
            // 创建OrderItem:4个日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 将item添加到集合中
            orderItems.add(item);
        }

        // 创建Order对象
        Order order = new Order();
        // order属性:uid
        order.setUid(uid);
        // order属性:pay
        order.setPay(pay);
        // 通过addressService.getById()得到收货地址的数据
        Address address = addressService.getById(addressId);
        // --补充：判断是否查询到address数据
        if (address == null) {
            throw new AddressNotFoundException("创建订单失败!收获地址数据有误,请稍后刷新重试!");
        }
        // order属性:recv_4
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvDistrict(address.getDistrict());
        order.setRecvAddress(address.getAddress());
        // order属性:order_time
        order.setOrderTime(now);
        // order属性:status 值为0 ，没有支付
        order.setStatus(0);
        // order属性:4个日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);

        // 插入订单数据并获取oid:insertOrder(order)
        insertOrder(order);

        // 遍历orderItems
        for (OrderItem orderItem : orderItems) {
            // item属性:oid
            orderItem.setOid(order.getId());
            // 插入订单数据
            insertOrderItem(orderItem);
        }

        // 删除购物车中的数据
        // cartService.deleteByIds(cartIds);
        // 消除库存

        // 返回
        return order;
    }

    @Override
    public OrderVO getById(Integer id) {
        return findById(id);
    }

    /**
     * 插入订单数据
     * @param order 订单数据
     */
    private void insertOrder(Order order) {
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入订单数据时发生未知错误!");
        }
    }

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     */
    private void insertOrderItem(OrderItem orderItem) {
        Integer rows = orderMapper.insertOrderItem(orderItem);
        if (rows != 1) {
            throw new InsertException("插入订单商品数据时发生未知错误!");
        }

    }

    /**
     * 根据id查询订单详情
     * @param id 订单id
     * @return 匹配的订单详情，如果没有匹配的数据，则返回null
     */
    private OrderVO findById(Integer id) {
        return orderMapper.findById(id);
    }

}
