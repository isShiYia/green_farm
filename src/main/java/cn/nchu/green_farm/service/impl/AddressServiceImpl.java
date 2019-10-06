package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.District;
import cn.nchu.green_farm.mapper.AddressMapper;
import cn.nchu.green_farm.service.IAddressService;
import cn.nchu.green_farm.service.IDistrictService;
import cn.nchu.green_farm.service.exception.AccessDefinedException;
import cn.nchu.green_farm.service.exception.AddressNotFoundException;
import cn.nchu.green_farm.service.exception.InsertException;
import cn.nchu.green_farm.service.exception.UpdateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户收货地址业务层实现类
 * @author Choococo
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Resource
    private AddressMapper addressMapper;
    @Resource // 在业务层实现类中调用其他业务层接口
    private IDistrictService districtService;

    @Override // 新增收货地址数据（第一次创建的收货地址设为默认）
    public Address create(Address address, String username) throws InsertException {
        // 根据uid查询用户收货地址的个数
        Integer count = getCountByUid(address.getUid());
        // 如果为0表示用户第一次创建数据，将本次创建的地址设为默认
        // 如果为1，将该地址设为非默认
        address.setIsDefault(count == 0 ? 1 : 0);
        // TO DO 处理数据库中district字段,提交省市区代号获取到值-单独写成一个方法
        address.setDistrict(getDistrict(address.getProvince(), address.getCity(),address.getArea()));
        // 对日志进行封装
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        // 执行新增
        addnew(address);
        // 返回收货地址数据
        return address;
    }

    @Override // 显示列表
    public List<Address> getListByUid(Integer uid) {
        // 这里是查询，显示收货地址列表，没有什么业务
        return findByUid(uid);
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
        Address data = findById(id);
        // 判断数据是否为null
        if (data == null) {
            // 是：抛出异常
            throw new AddressNotFoundException("设置默认收货地址失败!尝试访问的收获地址数据不存在!");
        }
        // 判断数据是否与用户数据匹配
        if (!data.getUid().equals(uid)) {
            // 不匹配：抛出异常
            throw new AccessDefinedException("设置收获地址失败!访问数据权限验证不通过!");
        }
        // 执行两次修改
        updateNonDefault(uid, data.getName(), new Date());
        updateDefault(id, data.getName(), new Date());

    }


    /**
     * 新增收货地址数据
     * @param address 收货地址数据
     */
    private void addnew(Address address) {
        Integer rows = addressMapper.addnew(address);
        if (rows != 1) {
            throw new InsertException("新增收货地址数据失败!新增收货地址数据时发生未知错误!");
        }
    }

    /**
     * 根据用户id查询收货地址数据
     * @param uid 用户id
     * @return 收货地址数据【注意】这里是查询数据，所以是不需要抛出异常的，需要返回查询的结果
     */
    private Integer getCountByUid(Integer uid) {
        return addressMapper.getCountByUid(uid);
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
     * @param uid 用户id
     * @return 匹配的收货地址列表数据，如果没有则返回null
     */
    private List<Address> findByUid(Integer uid) {
        return addressMapper.findByUid(uid);
    }

    /**
     * 根据用户id将用户的所有的收货地址设置为非默认 0
     * @param uid 用户id
     */
    private void updateNonDefault(Integer uid, String modifiedUser,Date modifiedTime) {
        Integer rows = addressMapper.updateNonDefault(uid, modifiedUser, modifiedTime);
        if (rows < 1) {
            throw new UpdateException("修改默认地址时出现未知错误!");
        }
    }

    /**
     * 将指定的收货地址设置为默认 1
     * @param id 收货地址id
     */
    private void updateDefault(Integer id, String modifiedUser,Date modifiedTime) {
        Integer rows = addressMapper.updateDefault(id, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("修改默认地址时发生未知错误!");
        }
    }

    /**
     * 根据收货地址id查询匹配的收货地址数据
     * @param id 收货地址id
     * @return 匹配的收货地址的数据，如果没有则返回null
     */
    private Address findById(Integer id) {
        return addressMapper.findById(id);
    }

}
