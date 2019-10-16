package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.mapper.AdminMapper;
import cn.nchu.green_farm.service.IAdminService;
import cn.nchu.green_farm.service.exception.AdminNotFoundException;
import cn.nchu.green_farm.service.exception.BusinessNotFoundException;
import cn.nchu.green_farm.service.exception.PasswordNotMatchException;
import cn.nchu.green_farm.service.exception.UpdateException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<FarmProduct> getListOfFarmProduct(Integer page, Integer limit) {
        return adminMapper.findFarmProductData(page,limit);
    }

    @Override
    public Integer getListOfFarmProductCount() {
        return adminMapper.getListOfFarmProductCount();
    }

    @Override
    public void changeStatusPass(Integer id, String modifiedUser) throws UpdateException, BusinessNotFoundException {
        // 根据商家id查询商家数据
        FarmProduct data = findProById(id);
        // 判断数据是否为null
        if (data == null) {
            throw new BusinessNotFoundException("修改商家数据失败!您尝试修改的商家数据不存在!");
        }
        // 是:抛出异常BusinessNotFoundException
        // 执行审核通过操作
        updateStatusPass(id, modifiedUser, new Date());
    }

    @Override
    public void changeStatusNoPass(Integer id, String modifiedUser) throws UpdateException, BusinessNotFoundException {
        // 根据商家id查询商家数据
        FarmProduct data = findProById(id);
        // 判断数据是否为null
        if (data == null) {
            throw new BusinessNotFoundException("修改商家数据失败!您尝试修改的商家数据不存在!");
        }
        // 执行审核不通过操作
        updateStatusNoPass(id, modifiedUser, new Date());
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

    /**
     * 根据商家id查询商家数据
     * @param id 农产品id
     * @return 匹配的农产品数据，如果没有，则返回null
     */
    private FarmProduct findProById(Integer id) {
        return adminMapper.findProById(id);
    }
    /**
     * 根据农产品id对数据进行审核通过操作
     * @param id 需要审核的农产品的id
     * @param modifiedUser 修改执行人：管理员
     * @param modifiedTime 修改执行时间
     */
    private void updateStatusPass(Integer id,String modifiedUser, Date modifiedTime) {
        Integer rows = adminMapper.updateStatusPass(id, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新数据时出现未知错误!");
        }
    }
    /**
     * 根据农产品id对数据进行审核为通过操作
     * @param id 需要审核的农产品的id
     * @param modifiedUser 修改执行人：管理员
     * @param modifiedTime 修改执行时间
     */
    private void updateStatusNoPass(Integer id,String modifiedUser,Date modifiedTime) {
        Integer rows = adminMapper.updateStatusNoPass(id, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新数据时出现未知错误!");
        }
    }
}
