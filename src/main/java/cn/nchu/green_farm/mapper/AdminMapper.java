package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

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
}
