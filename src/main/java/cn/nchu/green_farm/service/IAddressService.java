package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.service.exception.*;

import java.util.List;

/**
 * 收货地址业务层接口
 * @author Choococo
 */
public interface IAddressService {

    /**
     * 新增收货地址
     * @param address 收货地址信息
     * @param username 执行修改人
     * @return 新增收货地址的数据
     * @throws InsertException 插入数据异常
     */
    Address create(Address address, String username) throws InsertException;

    /**
     * 根据用户id获取收货地址列表
     * @param uid 用户id
     * @return 匹配的收货地址数据，如果没有则返回null
     */
    List<Address> getListByUid(Integer uid);

    /**
     * 设置默认收货地址
      * @param uid 用户id
     * @param id 收货地址id
     * @throws UpdateException 更新数据异常
     * @throws AddressNotFoundException 收货地地址不存在异常（没有发现当前id）
     * @throws AccessDefinedException 访问数据权限异常（可能不是当前用户的uid）
     */
    void setDefault(Integer uid, Integer id) throws UpdateException, AddressNotFoundException, AccessDefinedException;

    /**
     * 删除根据用户id和收货地址id删除用户数据
     * @param uid 用户id
     * @param id 收货地址id
     * @throws DeleteException 删除数据异常
     */
    void delete(Integer uid, Integer id) throws DeleteException,AddressNotFoundException,AccessDefinedException;

    /**
     * 根据收获地址id获取收获地址数据
     * @param id 收货地址id
     * @param uid 用户id
     * @return
     */
    Address getById(Integer id, Integer uid) throws AddressNotFoundException, AccessDefinedException;

    /**
     * 修改收货地址信息
     * @param address 收货地址信息
     * @throws AddressNotFoundException 收获地址异常
     * @throws UpdateException 数据更新异常
     */
    void changeInfo(Address address, String username) throws AddressNotFoundException,UpdateException,AccessDefinedException;

    Address getById(Integer addressId);
}
