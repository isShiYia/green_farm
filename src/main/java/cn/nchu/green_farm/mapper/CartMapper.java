package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 购物车持久层接口
 */
@Mapper
public interface CartMapper {

    /**
     * 新增购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer addnew(Cart cart);

    /**
     * 根据用户id和商品id查询购物车数据
     * @param uid 用户id
     * @param pid 农产品id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    Cart findByUidAndPid(@Param("uid") Integer uid,@Param("pid") Integer pid);

    /**
     * 更新购物车中商品的数量
     * @param id 购物车数据id
     * @param count 购物车中商品新的数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateCount(@Param("id") Integer id,@Param("count") Integer count,
                        @Param("modifiedUser") String modifiedUser,
                        @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id查询该用户购物车数据
     * @param uid 用户id
     * @return 该用户购物车数据
     */
    List<CartVO> findByUid(Integer uid);

    /**
     * 根据id获取用户购物车数据
     * @param id 购物车数据的id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    Cart findById(Integer id);

    /**
     * 根据一系列id获取购物车中的数据
     * @param ids 购物车中所勾选的id的数组
     * @return 购物车中所勾选的id好的商品数据的信息的集合
     */
    List<CartVO> findByIds(Integer[] ids);
}
