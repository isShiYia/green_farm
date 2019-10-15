package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 管理员处理用户和商家信息持久层接口
 * @author Choococo
 */
@Mapper
public interface AdminBusMapper {

    /**
     * 查询需要审核的商家的数据
     * @param page 页码
     * @param limit 单页条数
     * @return 匹配的需要审核的商家数据，如果没有则返回 null
     */
    List<Business> findBusinessData(@Param("page") Integer page,@Param("limit") Integer limit);

    /**
     * 根据商家id对数据进行审核通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @param modifiedTime 修改执行时间
     * @return 受影响的行数
     */
    Integer updateStatusPass(@Param("id") Integer id,
                             @Param("modifiedUser") String modifiedUser,
                             @Param("modifiedTime")  Date modifiedTime);

    /**
     * 根据商家id对数据进行审核为通过操作
     * @param id 需要审核的商家的id
     * @param modifiedUser 修改执行人：管理员
     * @param modifiedTime 修改执行时间
     * @return 受影响的行数
     */
    Integer updateStatusNoPass(@Param("id") Integer id,
                               @Param("modifiedUser")  String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据商家id查询商家数据
     * @param id 商家id
     * @return 匹配的商家数据，如果没有，则返回null
     */
    Business findBusById(Integer id);

    Integer getListOfBusinessCount();
}
