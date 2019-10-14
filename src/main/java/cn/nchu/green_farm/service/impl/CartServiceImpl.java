package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.mapper.CartMapper;
import cn.nchu.green_farm.service.ICartService;
import cn.nchu.green_farm.service.exception.AccessDefinedException;
import cn.nchu.green_farm.service.exception.CartNotFoundException;
import cn.nchu.green_farm.service.exception.InsertException;
import cn.nchu.green_farm.service.exception.UpdateException;
import cn.nchu.green_farm.vo.CartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 购物车业务层实现类
 * @author Choococo
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public void addToCart(String username, Cart cart) throws InsertException, UpdateException {
        // 根据uid好pid查询商品数据
        Cart data = findByUidAndPid(cart.getUid(),cart.getPid());
        // 判断数据是否为null
        if (data == null) {
            // 是：该用户没有将此商品添加进购物车
            // 进行添加，并进行思想日志的修改
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            addnew(cart);
        } else {
            // 否：
            // 对已在购物车中的数量与新的数量进行一个加法运算
            // 取出uid
            Integer id = data.getId();
            // 取出旧的数量
            Integer oldCount = data.getCount();
            // 与新的数量进行一个增加
            Integer newCount = oldCount + cart.getCount();
            // 并执行更新操作
            updateCount(id, newCount, username, new Date());
        }
    }

    @Override
    public List<CartVO> getByUid(Integer uid) {
        return findByUid(uid);
    }

    @Override
    public void addCount(String username, Integer id, Integer uid) throws CartNotFoundException, AccessDefinedException, UpdateException {

    }

    /**
     * 新增购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    private void addnew(Cart cart) {
        Integer rows = cartMapper.addnew(cart);
        if (rows != 1) {
            throw new InsertException("新增购物车数据时发生未知错误!");
        }
    }

    /**
     * 根据用户id和商品id查询购物车数据
     * @param uid 用户id
     * @param pid 农产品id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    private Cart findByUidAndPid(Integer uid, Integer pid) {
        return cartMapper.findByUidAndPid(uid, pid);
    }

    /**
     * 更新购物车中商品的数量
     * @param id 购物车数据id
     * @param count 购物车中商品新的数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    private void updateCount(Integer id,Integer count,String modifiedUser,Date modifiedTime) {
        Integer rows = cartMapper.updateCount(id, count, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新购物车数据时发生未知错误!");
        }
    }

    /**
     * 根据用户id查询该用户购物车数据
     * @param uid 用户id
     * @return 该用户购物车数据
     */
    private List<CartVO> findByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }

    /**
     * 根据id获取用户购物车数据
     * @param id 购物车数据的id
     * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
     */
    private Cart findById(Integer id) {
        return cartMapper.findById(id);
    }
}
