package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.service.exception.AdminNotFoundException;
import cn.nchu.green_farm.service.exception.PasswordNotMatchException;

/**
 * 管理员持久层接口
 * @author Jianhzhong
 */
public interface IAdminService {
    /**
     *
     * @param adminId 管理员账号
     * @param adminPassword 管理员密码
     * @return 成功登陆后管理员数据
     * @throws AdminNotFoundException 管理员账号不存在异常
     * @throws PasswordNotMatchException 管理员密码不匹配异常
     */
    Admin login(String adminId,String adminPassword) throws AdminNotFoundException, PasswordNotMatchException;
}
