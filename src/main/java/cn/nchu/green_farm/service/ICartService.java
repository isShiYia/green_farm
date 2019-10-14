package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.service.exception.AccessDefinedException;
import cn.nchu.green_farm.service.exception.CartNotFoundException;
import cn.nchu.green_farm.service.exception.InsertException;
import cn.nchu.green_farm.service.exception.UpdateException;
import cn.nchu.green_farm.vo.CartVO;

import java.util.List;

/**
 * 购物车业务层接口
 * @author choococo
 */
public interface ICartService {
    /**
     * 将商品添加到购物车
     * @param username 当前操作的执行人
     * @param cart 购物车数据
     * @throws InsertException 数据插入异常
     * @throws UpdateException 更新数据异常
     */
    void addToCart(String username, Cart cart) throws InsertException, UpdateException;

    /**
     * 根据用户id查询该用户购物车列表数据
     * @param uid 用户id
     * @return 购物车列表数据
     */
    List<CartVO> getByUid(Integer uid);

    /**
     * 增加购物车中商品的数量
     * @param username 当前执行操作的执行人
     * @param id 购物车中商品的id
     * @param uid 登录的用户的id
     * @throws CartNotFoundException
     * @throws AccessDefinedException
     * @throws UpdateException
     */
    void addCount(String username,Integer id,Integer uid) throws CartNotFoundException, AccessDefinedException,UpdateException;
}
