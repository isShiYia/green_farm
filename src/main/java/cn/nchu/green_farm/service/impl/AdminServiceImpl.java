package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.mapper.AdminMapper;
import cn.nchu.green_farm.service.IAdminService;
import cn.nchu.green_farm.service.exception.AdminNotFoundException;
import cn.nchu.green_farm.service.exception.PasswordNotMatchException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * 管理员业务层实现类
 * @author Jianhzhong
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin login(String adminName, String adminPassword) throws AdminNotFoundException, PasswordNotMatchException {
        // 根据用户名查询用户数据
        Admin data = findByUsername(adminName);
        // 判断用户是否为null
        if (data == null) {
            // 是: 抛出异常用户名不存在
            throw new AdminNotFoundException("登录失败!您尝试登录的用户名" + adminName + "不存在!");
        }
        // 否：
        // 判断用户数据是否被删除
        if (data.getIsDelete() == 1) {
            // 是：抛出异常，用户名不存在
            throw new AdminNotFoundException("登录失败!您尝试登录的用户名"+adminName+"已经被注销!");
        }
        // 否
        // 取出数据库中的密码和盐值
        // 对输入的密码进行MD5加密，与数据库中的密码进行比较
        String md5Password;
        md5Password = getMd5Password(adminPassword, data.getSalt());
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
     * 通过管理员账号查询管理员数据
     * @param adminName 管理员账号
     * @return
     */
    private Admin findByUsername(String adminName) {
        return adminMapper.findByAdminName(adminName);
    }
}
