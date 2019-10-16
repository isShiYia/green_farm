package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.exception.AdminNotFoundException;
import cn.nchu.green_farm.service.exception.BusinessNotFoundException;
import cn.nchu.green_farm.service.exception.PasswordNotMatchException;
import cn.nchu.green_farm.service.exception.UpdateException;

import java.util.List;

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

    /**
     * 查询需要审核的农产品数据
     * @return 需要审核的商农产品
     */
    List<FarmProduct> getListOfFarmProduct(Integer page, Integer limit);

    Integer getListOfFarmProductCount();

    /**
     * 根据农产品id对数据进行审核通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @throws UpdateException 更新数据异常
     * @throws BusinessNotFoundException 商家数据不存在异常
     */
    void changeStatusPass(Integer id,String modifiedUser) throws UpdateException, BusinessNotFoundException;

    /**
     * 根据农产品id对数据进行审核为通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @throws UpdateException 更新数据异常
     * @throws BusinessNotFoundException 商家数据不存在异常
     */
    void changeStatusNoPass(Integer id,String modifiedUser)  throws UpdateException, BusinessNotFoundException;
}
