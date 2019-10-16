package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.entity.FarmProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 管理员持久层接口
 * @author Jianhzhong
 */
@Mapper
public interface AdminMapper {
    /**
     * 通过用户名查询用户数据
     * @param adminName 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    Admin findByAdminName(String adminName);

    /**
     * 查询需要审核的商家的数据
     * @param page 页码
     * @param limit 单页条数
     * @return 匹配的需要审核的商家数据，如果没有则返回 null
     */
    List<FarmProduct> findFarmProductData(@Param("page") Integer page, @Param("limit") Integer limit);

    Integer getListOfFarmProductCount();

    FarmProduct findProById(Integer id);

    Integer updateStatusPass(Integer id, String modifiedUser, Date modifiedTime);

    Integer updateStatusNoPass(Integer id, String modifiedUser, Date modifiedTime);
}
