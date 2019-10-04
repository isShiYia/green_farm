package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.User;
import cn.nchu.green_farm.service.exception.*;

/**
 * 用户持久层接口
 * @author Choococo
 */
public interface IUserService {

    /**
     * 用户注册
     * @param user 用户注册时，添加的信息
     * @return 注册成功后用户的数据
     * @throws InsertException 插入数据时异常
     * @throws DuplicateKeyException 违反unique约束异常
     */
    User reg(User user) throws InsertException, DuplicateKeyException;

    /**
     * 用户登录
     * @param username 登录名
     * @param password 登录密码
     * @return 成功登录后用户的数据
     * @throws UserNotFoundException 用户名不存在异常
     * @throws PasswordNotMatchException 密码不匹配异常
     */
    User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException;

    /**
     * 修改密码
      * @param uid 用户id
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @return 受影响的行数
     * @throws UserNotFoundException 用户不存在异常
     * @throws PasswordNotMatchException 用户密码不匹配异常
     * @throws UpdateException 用户更新数据异常
     */
    void changePassword(Integer uid, String oldPassword,String newPassword) throws UserNotFoundException,PasswordNotMatchException, UpdateException;

    /**
     * 根据用户id查询用户数据，用于显示到页面上
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的用户数据，则返回null
     */
    User getById(Integer uid);

    /**
     * 用户修改信息
     * @param user 用户信息
     * @throws UserNotFoundException 用户不存在异常
     * @throws UpdateException 更新数据异常
     */
    void changeInfo(User user) throws UserNotFoundException,UpdateException;

    /**
     * 上传头像
     * @param uid 用户id
     * @param avatar 用户头像
     * @throws UserNotFoundException 用户名不存在异常
     * @throws UpdateException 更新数据异常
     */
    void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException;

}
