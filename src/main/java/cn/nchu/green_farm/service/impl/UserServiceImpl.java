package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.User;
import cn.nchu.green_farm.mapper.UserMapper;
import cn.nchu.green_farm.service.IUserService;
import cn.nchu.green_farm.service.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * 用户持久层实现类
 * @author Choococo
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User reg(User user) throws InsertException, DuplicateKeyException {
        // 根据用户名查询用户数据
        User data = findByUsername(user.getUsername());
        // 判断数据是否为null
        if (data == null) {
            // 是：允许注册
            // 补充需要添加的字段
            // 设置是否删除字段为否
            user.setIsDelete(0);
            Date now = new Date();
            user.setCreatedTime(now);
            user.setCreatedUser(user.getUsername());
            user.setModifiedTime(now);
            user.setModifiedUser(user.getUsername());
            // 【处理密码加密】
            // 1. 根据UUID生成盐值，这里的盐值统一大写
            String salt = UUID.randomUUID().toString().toUpperCase();
            // 2. 获取前端界面输入的密码作为原始密码
            String srcPassword = user.getPassword();
            // 3. 用MD5对密码进行加密,获得新密码
            String md5Password = getMd5Password(srcPassword, salt);
            // 4. 将加密后的密码存放到数据库中
            user.setPassword(md5Password);
            // 5. 将盐值存放到数据库中
            user.setSalt(salt);
            // 执行注册
            addnew(user);
            return user;
        } else {
            throw new DuplicateKeyException("注册用户失败!您注册的用户名"+user.getUsername()+"已存在!");
        }
    }

    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        // 根据用户名查询用户数据
        User data = findByUsername(username);
        // 判断用户是否为null
        if (data == null) {
            // 是: 抛出异常用户名不存在
            throw new UserNotFoundException("登录失败!您尝试登录的用户名" + username + "不存在!");
        }
        // 否：
        // 判断用户数据是否被删除
        if (data.getIsDelete() == 1) {
            // 是：抛出异常，用户名不存在
            throw new UserNotFoundException("登录失败!您尝试登录的用户名"+username+"已经被注销!");
        }
        // 否
        // 取出数据库中的密码和盐值
        // 对输入的密码进行MD5加密，与数据库中的密码进行比较
        String md5Password;
        md5Password = getMd5Password(password, data.getSalt());
        // 比对成功，将查询到的用户数据中的密码和盐值设置为null，不返回给用户
        if (md5Password.equals(data.getPassword())) {
            // 匹配成功，将密码和盐值设为null
            data.setPassword(null);
            data.setSalt(null);
            // 返回用户数据
            return data;
        } else {
            // 比对失败，抛出异常，密码不匹配
            throw new PasswordNotMatchException("登录失败!您尝试登录的用户密码错误!");
        }
    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException {
        // 根据用户id查询用户数据
        User data = findById(uid);
        // 判断用户数据是否为null
        if (data == null) {
            // 是：抛出异常，UserNotFoundException
            throw new UserNotFoundException("修改密码失败!您尝试修改的用户数据不存在!");
        }
        // 判断用户数据是否被删除
        if (data.getIsDelete() == 1) {
            // 是：抛出异常，UserNotFoundException
            throw new UserNotFoundException("修改密码失败!您尝试修改的用户数据已被注销!");
        }
        // 取出盐值，用于后面的判断
        String salt = data.getSalt();
        // 对输入的原始密码进行加密，再与数据库中的进行匹配
        String md5Password = getMd5Password(oldPassword, salt);
        // 判断密码是否与数据库中匹配
        if(md5Password.equals(data.getPassword())) {
            // 对新的密码进行加密
            String newMd5Password = getMd5Password(newPassword, salt);
            // 是：执行修改操作
            updatePassword(uid, newMd5Password, data.getUsername(), new Date());
        } else {
            // 否：抛出异常，PasswordNotMatchException
            throw new PasswordNotMatchException("修改密码失败！您输入的密码与原始密码不匹配！");
        }
    }

    /*
       修改信息，首先需要将用户的信息查询出来，显示到页面上
       然后再进行修改
     */

    @Override // 显示信息
    public User getById(Integer uid) {
        User data = findById(uid);
        data.setPassword(null);
        data.setSalt(null);
        data.setIsDelete(null);
        return data;
    }

    @Override // 修改信息
    public void changeInfo(User user) throws UserNotFoundException, UpdateException {
        // 根据id查询用户数据
        User data = findById(user.getId());
        // 判断数据是否为null
        if (data == null) {
            throw new UserNotFoundException("修改个人资料失败！您尝试访问的用户数据不存在!");
        }
        // 判断数据是否删除
        if (data.getIsDelete() == 1) {
            throw new UserNotFoundException("修改个人资料失败！您尝试访问的用户数据已注销!");
        }
        // 添加两项日志
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        // 执行更新
        updateInfo(user);
    }

    @Override
    public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
        // 根据用户id查询用户数据
        User data = findById(uid);
        // 判断用户数据是否为null
        if (data == null) {
            throw new UserNotFoundException("上传头像失败！您尝试访问的用户数据不存在!");
        }
        if (data.getIsDelete() == 1) {
            throw new UserNotFoundException("上传头像失败！您尝试访问的用户数据已被注销!");
        }
        // 执行修改
        updateAvatar(uid, avatar, data.getUsername(), new Date());
    }


    /**
     * 对原始密码和盐值执行MD5加密
     * @param srcPassword 原始密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMd5Password(String srcPassword,String salt) {
        String src = salt + srcPassword + salt;
        for (int i = 0; i < 10 ; i++) {
            src = DigestUtils.md5DigestAsHex(src.getBytes()).toUpperCase();
        }
        return src;
    }


    /**
     * 插入用户数据
     * @param user 用户数据
     */
    private void addnew(User user) {
        Integer rows = userMapper.addnew(user);
        if (rows != 1) {
            throw new InsertException("新增用户数据时出现未知错误!");
        }
    }

    /**
     * 通过用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    private User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 修改用户密码
     * @param uid 用户id
     * @param password 用户密码
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     */
    private void updatePassword(Integer uid,String password, String modifiedUser,Date modifiedTime) {
        Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("修改密码时发生未知错误!修改密码失败!");
        }
    }

    /**
     * 根据用户id查询修改密码需要的用户数据
     * @param uid 用户id
     * @return 匹配的用户数据，如果不匹配返回null
     */
    private User findById(Integer uid) {
        return userMapper.findById(uid);
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 受影响的行数
     */
    private void updateInfo(User user) {
        Integer rows = userMapper.updateInfo(user);
        if (rows != 1) {
            throw new UpdateException("修改用户信息时发生未知错误!");
        }
    }

    /**
     * 上传头像
     * @param uid 用户id
     * @param avatar 用户头像
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改执行时间
     * @return 受影响的行数
     */
    private void updateAvatar(Integer uid,String avatar, String modifiedUser,Date modifiedTime) {
        Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("上传头像失败!上传头像时出现未知错误!");
        }
    }
}
