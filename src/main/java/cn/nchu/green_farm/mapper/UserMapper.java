package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Choococo
 */
@Mapper
public interface UserMapper {

    Integer addnew(User user);
}
