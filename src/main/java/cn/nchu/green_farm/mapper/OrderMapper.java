package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Order;
import cn.nchu.green_farm.entity.OrderItem;
import cn.nchu.green_farm.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

/**
 *  订单与订单商品的持久层接口
 */
@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 根据id查询订单详情
     * @param id 订单id
     * @return 匹配的订单详情，如果没有匹配的数据，则返回null
     */
    OrderVO findById(Integer id);

}
