package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.service.exception.BusinessNotFoundException;
import cn.nchu.green_farm.service.exception.UpdateException;

import java.util.List;

/**
 * 管理员处理商家数据业务层接口
 */
public interface IAdminBusService {

    /**
     * 查询需要审核的商家数据
     * @return 需要审核的商家数据
     */
    List<Business> getListOfBusiness(Integer page,Integer limit);

    /**
     * 根据商家id对数据进行审核通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @throws UpdateException 更新数据异常
     * @throws BusinessNotFoundException 商家数据不存在异常
     */
    void changeStatusPass(Integer id,String modifiedUser) throws UpdateException, BusinessNotFoundException;

    /**
     * 根据商家id对数据进行审核为通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @throws UpdateException 更新数据异常
     * @throws BusinessNotFoundException 商家数据不存在异常
     */
    void changeStatusNoPass(Integer id,String modifiedUser)  throws UpdateException, BusinessNotFoundException;

    Integer getListOfBusinessCount();
}
