package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.BusinessAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 商家地址持久层接口
 * @author Jianhzhong
 */
@Mapper
public interface BusinessAddressMapper {

    /*
        这里我们设计的是:在收货地址
     */
    /**
     * 新增收货地址数据
     * @param businessAddress 收货地址数据
     * @return 受影响的行数
     */
    Integer addnew(BusinessAddress businessAddress);

    /**
     * 根据用户id查询收货地址数据
     * @param bid 商家id
     * @return 收货地址数据
     */
    Integer getCountByBid(Integer bid);

    /**
     * 根据用户id获取收获地址列表数据
     * @param bid 商家id
     * @return 匹配的收货地址列表数据，如果没有则返回null
     */
    List<BusinessAddress> findByBid(Integer bid);

    /*
        下面两个方法是用来处理设为默认的操作的：
        设为默认首先是将一个用户的所有地址都设为非默认，
        然后再将其中一个设为默认；
        第一个设为默认：是根据uid将所有的数据取出，设为非默认
        第二个设为默认：是根据收货地址id将数据设为默认
     */

    /**
     * 根据用户id将用户的所有的收货地址设置为非默认 0
     * @param bid 商家id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer bid);

    /**
     * 将指定的收货地址设置为默认 1
     * @param id 收货地址id
     * @return 受影响的行数
     */
    Integer updateDefault(@Param("id") Integer id,
                          @Param("modifiedUser") String modifiedUser,
                          @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址id查询匹配的收货地址数据【同样第一次设计也是用于设为默认】
     * @param id 收货地址id
     * @return 匹配的收货地址的数据，如果没有则返回null
     */
    BusinessAddress findById(Integer id);

    /**
     * 根据收货地址id删除收货地址
     * @param id 收货地址id
     * @return 受影响的行数
     */
    Integer deleteById(Integer id);

    /**
     * 根据用户id查询用户最后修改的收货地址的数据
     * @param bid 商家id
     * @return 匹配的收货地址数，如果没有则返回null
     */
    Address findLastModified(Integer bid);

    /*
        修改收货地址信息：
        （1）显示 查询所有的信息，已有findById（2）修改
     */

    /**
     * 根据收货地址id修改收货地址信息
     * @param businessAddress 收货地址信息
     * @return 受影响的行数
     */
    Integer updateInfo(BusinessAddress businessAddress);

}
