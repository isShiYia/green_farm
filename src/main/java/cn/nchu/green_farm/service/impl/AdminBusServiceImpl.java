package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.mapper.AdminBusMapper;
import cn.nchu.green_farm.service.IAdminBusService;
import cn.nchu.green_farm.service.exception.BusinessNotFoundException;
import cn.nchu.green_farm.service.exception.UpdateException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 管理员处理商家业务层实现类
 * @author Choococo
 */
@Service
public class AdminBusServiceImpl implements IAdminBusService {

    @Resource
    private AdminBusMapper adminBusMapper;

    @Override
    public List<Business> getListOfBusiness(Integer page,Integer limit) {
        return findBusinessData(page,limit);
    }

    @Override
    public void changeStatusPass(Integer id, String modifiedUser) throws UpdateException, BusinessNotFoundException {
        // 根据商家id查询商家数据
        Business data = findBusById(id);
        // 判断数据是否为null
        if (data == null) {
            throw new BusinessNotFoundException("修改商家数据失败!您尝试修改的商家数据不存在!");
        }
        // 是:抛出异常BusinessNotFoundException
        // 执行审核通过操作
        updateStatusPass(id, modifiedUser, new Date());
    }

    @Override
    public void changeStatusNoPass(Integer id, String modifiedUser)  throws UpdateException, BusinessNotFoundException {
        // 根据商家id查询商家数据
        Business data = findBusById(id);
        // 判断数据是否为null
        if (data == null) {
            throw new BusinessNotFoundException("修改商家数据失败!您尝试修改的商家数据不存在!");
        }
        // 执行审核不通过操作
        updateStatusNoPass(id, modifiedUser, new Date());
    }

    @Override
    public Integer getListOfBusinessCount() {
        return adminBusMapper.getListOfBusinessCount();
    }

    /**
     * 查询需要审核的商家的数据
     * @return 匹配的需要审核的商家数据，如果没有则返回 null
     */
    private List<Business> findBusinessData(Integer page,Integer limit) {
        return adminBusMapper.findBusinessData((page - 1)*limit,limit);
    }

    /**
     * 根据商家id对数据进行审核通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @param modifiedTime 修改执行时间
     */
    private void updateStatusPass(Integer id,String modifiedUser, Date modifiedTime) {
        Integer rows = adminBusMapper.updateStatusPass(id, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新数据时出现未知错误!");
        }
    }

    /**
     * 根据商家id对数据进行审核为通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @param modifiedTime 修改执行时间
     */
    private void updateStatusNoPass(Integer id,String modifiedUser,Date modifiedTime) {
        Integer rows = adminBusMapper.updateStatusNoPass(id, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("更新数据时出现未知错误!");
        }
    }

    /**
     * 根据商家id查询商家数据
     * @param id 商家id
     * @return 匹配的商家数据，如果没有，则返回null
     */
    private Business findBusById(Integer id) {
        return adminBusMapper.findBusById(id);
    }
}
