package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.BusinessAddress;
import cn.nchu.green_farm.entity.District;
import cn.nchu.green_farm.mapper.BusinessAddressMapper;
import cn.nchu.green_farm.service.IBusinessAddressService;
import cn.nchu.green_farm.service.IDistrictService;
import cn.nchu.green_farm.service.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商家地址业务层实现类
 * @author Jianhzhong
 */
@Service
public class BusinessAddressServiceImpl implements IBusinessAddressService {

    @Resource
    private BusinessAddressMapper businessAddressMapper;
    @Resource // 在业务层实现类中调用其他业务层接口
    private IDistrictService districtService;

    @Override // 新增收货地址数据（第一次创建的收货地址设为默认）
    public BusinessAddress create(BusinessAddress businessAddress, String username) throws InsertException {
        // 根据bid查询用户收货地址的个数
        Integer count = getCountByBid(businessAddress.getBid());
        // 如果为0表示用户第一次创建数据，将本次创建的地址设为默认
        // 如果为1，将该地址设为非默认
        businessAddress.setIsDefault(count == 0 ? 1 : 0);
        // TO DO 处理数据库中district字段,提交省市区代号获取到值-单独写成一个方法
        businessAddress.setDistrict(getDistrict(businessAddress.getProvince(), businessAddress.getCity(),businessAddress.getArea()));
        // 对日志进行封装
        businessAddress.setCreatedUser(username);
        businessAddress.setCreatedTime(new Date());
        businessAddress.setModifiedUser(username);
        businessAddress.setModifiedTime(new Date());
        // 执行新增
        addnew(businessAddress);
        // 返回收货地址数据
        return businessAddress;
    }

    @Override // 显示列表
    public List<BusinessAddress> getListByBid(Integer bid) {
        // 这里是查询，显示收货地址列表，没有什么业务
        return findByBid(bid);
    }

    @Override
    @Transactional
    public void setDefault(Integer uid, Integer id) throws UpdateException, AddressNotFoundException, AccessDefinedException {
        /*
            在设为默认的操作中可能的问题，就是当前的收货地址的id可能不是对应的 uid的当前用户的收货地址
            这种情况是很有可能发生的，因为用户提交的数据都可以认为是不可靠的，所以为了避免这种问题的 出现
            我们还需要在当前的业务中增加根据收货地址id查询收货地址数据的一个方法，通过拿到这个方法中的 uid与
            从session中的uid进行比较，如果相同，那么就可以进行后面的操作
         */
        // 根据收货地址id查询收货地址数据
        BusinessAddress data = findById(id);
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出异常
            throw new AddressNotFoundException("设置默认收货地址失败!尝试访问的收获地址数据不存在!");
        }
        // 判断数据是否与用户数据匹配
        if (!data.getBid().equals(uid)) {
            // 不匹配：抛出异常
            throw new AccessDefinedException("设置收获地址失败!访问数据权限验证不通过!");
        }
        // 执行两次修改
        updateNonDefault(uid);
        updateDefault(id, data.getName(), new Date());

    }

    @Override
    @Transactional
    public void delete(Integer uid, Integer id) throws DeleteException, AddressNotFoundException, AccessDefinedException {
        // 根据收货地址数据id查询数据
        BusinessAddress data = findById(id); // 这里面的查询的只有id值，name,是否默认
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出异常
            throw new AddressNotFoundException("删除收货地址失败!尝试删除的收货地址数据不存在!");
        }
        // 判断收货地址数据归属是否有误
        if (!data.getBid().equals(uid)) {
            // 有误：抛出异常
            throw new AccessDefinedException("删除收货地址失败!您尝试删除的收获地址不存在!");
        }
        // 执行删除
        deleteById(id);
        //【注意】在删除之后还要重新设为默认
        // 检查是否还有收货地址数据
        if (getCountByBid(uid) > 0) {
            // 有：获取最后修改的收货地址的数据的id
            Integer lastModified = findLastModified(uid).getId();
            // 重新执行修改，将最新修改的时间设为默认收货地址
            setDefault(uid, lastModified);
        }
    }

    @Override
    public BusinessAddress getById(Integer id, Integer uid) throws AddressNotFoundException, AccessDefinedException {
        // 根据收货地址id获取收获地址数据
        BusinessAddress data = findById(id);
        // 判断数据是否为null
        if (data == null) {
            throw new AddressNotFoundException("收获地址显示出错!不存在当前收获地址!");
        }
        // 判断收货地址归属是否有误
        if (!data.getBid().equals(uid)) {
            throw new AccessDefinedException("收货地址显示出错!您尝试访问的收货地址归属有误!");
        }
        // 将不需要显示到界面上的值设为null
        data.setBid(null);
        data.setIsDefault(null);
        // 返回处理的收货地址数据
        return data;
    }

    @Override // 根据收货地址id修改收货地址
    public void changeInfo(BusinessAddress businessAddress, String username) throws AddressNotFoundException, UpdateException,AccessDefinedException {
        // 根据收货地址id查询收获地址数据
        BusinessAddress data = findById(businessAddress.getId());
        // 判断数据是否为null
        if (data == null) {
            throw new AddressNotFoundException("修改收货地址失败!您修改的收获地址不存在!");
        }
        // 判断用户归属是否正确
        if (data.getBid().equals(businessAddress.getBid())) {
            throw new AccessDefinedException("修改收货地址失败!您修改的收获地址用户归属有误!");
        }
        // 对district进行一个封装
        businessAddress.setDistrict(getDistrict(businessAddress.getProvince(), businessAddress.getCity(),businessAddress.getArea()));
        // 封装日志
        businessAddress.setModifiedUser(username);
        businessAddress.setModifiedTime(new Date());
        // 执行修改收货地址
        updateInfo(businessAddress);
    }


    /**
     * 新增收货地址数据
     * @param businessAddress 收货地址数据
     */
    private void addnew(BusinessAddress businessAddress) {
        Integer rows = businessAddressMapper.addnew(businessAddress);
        if (rows != 1) {
            throw new InsertException("新增收货地址数据失败!新增收货地址数据时发生未知错误!");
        }
    }

    /**
     * 根据商家id查询收货地址数据
     * @param bid 商家id
     * @return 收货地址数据【注意】这里是查询数据，所以是不需要抛出异常的，需要返回查询的结果
     */
    private Integer getCountByBid(Integer bid) {
        return businessAddressMapper.getCountByBid(bid);
    }

    /**
     * 根据省、市、区代号获取名称
     * @param province 省代号
     * @param city 市代号
     * @param area 区代号
     * @return 省市区的名称，例如：浙江省杭州市上城区
     */
    private String getDistrict(String province, String city, String area) {
       District p = districtService.getByCode(province);
       District c = districtService.getByCode(city);
       District a = districtService.getByCode(area);

       String provinceName = null;
       String cityName = null;
       String areaName = null;

       if (p != null) {
           provinceName = p.getName();
       }
       if (c != null) {
           cityName = c.getName();
       }
       if (a != null) {
           areaName = a.getName();
       }
       return provinceName + "-" + cityName + "-" + areaName;
    }

    /**
     * 根据用户id获取收获地址列表数据
     * @param bid 用户id
     * @return 匹配的收货地址列表数据，如果没有则返回null
     */
    private List<BusinessAddress> findByBid(Integer bid) {
        return businessAddressMapper.findByBid(bid);
    }

    /**
     * 根据用户id将用户的所有的收货地址设置为非默认 0
     * @param uid 用户id
     */
    private void updateNonDefault(Integer uid) {
        Integer rows = businessAddressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("修改默认地址时出现未知错误!");
        }
    }

    /**
     * 将指定的收货地址设置为默认 1
     * @param id 收货地址id
     */
    private void updateDefault(Integer id, String modifiedUser,Date modifiedTime) {
        Integer rows = businessAddressMapper.updateDefault(id, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("修改默认地址时发生未知错误!");
        }
    }

    /**
     * 根据收货地址id查询匹配的收货地址数据
     * @param id 收货地址id
     * @return 匹配的收货地址的数据，如果没有则返回null
     */
    private BusinessAddress findById(Integer id) {
        return businessAddressMapper.findById(id);
    }

    /**
     * 根据收货地址id删除收货地址
     * @param id 收货地址id
     * @return 受影响的行数
     */
    private void deleteById(Integer id) {
        Integer rows = businessAddressMapper.deleteById(id);
        if (rows != 1) {
            throw new DeleteException("删除收货地址数据时发生未知错误!");
        }
    }

    /**
     * 根据用户id查询用户最后修改的收货地址的数据
     * @param uid 用户id
     * @return 匹配的收货地址数，如果没有则返回null
     */
    private Address findLastModified(Integer uid) {
        return businessAddressMapper.findLastModified(uid);
    }

    /**
     * 修改收货地址信息
     * @param businessAddress 收货地址信息
     * @return 受影响的行数
     */
    private void updateInfo(BusinessAddress businessAddress) {
        Integer rows = businessAddressMapper.updateInfo(businessAddress);
        if (rows != 1) {
            throw new UpdateException("修改个人信息时发生未知错误!");
        }
    }

}
