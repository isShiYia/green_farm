package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.Business;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BusinessMapper {
    /**
     *
     * @param business:商家信息
     * @return：数据库受影响行数
     */
    Integer addnew(Business business);

    /**
     *
     * @param businessName：商家名
     * @return:商家信息
     */
    Business getByBusinessName(String businessName);

    /**
     *
     * @param businessId:商家id
     * @param newPassword：新密码
     * @param modifiedUser：修改人姓名
     * @param modifiedTime：修改时间
     * @return：返回受影响行数
     */
    Integer updatePassword(@Param("businessId") Integer businessId,
                           @Param("newPassword") String newPassword,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    /**
     *
     * @param businessId:商家id
     * @return：商家信息
     */
    Business getById(Integer businessId);
    /**
     * 上传头像
     * @param businessId 商家id
     * @param avatar 商家头像
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改执行时间
     * @return 受影响的行数
     */
    Integer updateAvatar(
            @Param("businessId") Integer businessId,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
