package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 用户持久层接口
 * @author Choococo
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer addnew(User user);

    /**
     * 通过用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);

    /**
     * 修改用户密码
     * @param uid 用户id
     * @param password 用户密码
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updatePassword(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id查询修改密码需要的用户数据
     * @param uid 用户id
     * @return 匹配的用户数据，如果不匹配返回null
     */
    User findById(Integer uid);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 受影响的行数
     */
    Integer updateInfo(User user);

    /**
     * 上传头像
     * @param uid 用户id
     * @param avatar 用户头像
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改执行时间
     * @return 受影响的行数
     */
    Integer updateAvatar(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);



}
