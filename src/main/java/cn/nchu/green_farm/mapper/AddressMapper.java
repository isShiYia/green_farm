package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 收货地址持久层接口
 * @author Choococo
 */
@Mapper
public interface AddressMapper {

    /*
        这里我们设计的是:在收货地址
     */
    /**
     * 新增收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer addnew(Address address);

    Address findByUid(Integer uid);

}
