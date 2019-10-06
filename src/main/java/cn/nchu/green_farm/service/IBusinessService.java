package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.service.exception.*;
import org.springframework.web.bind.annotation.RequestParam;

public interface IBusinessService {
    /**
     *
     * @param business:商家数据
     * @return：商家数据
     * @throws DuplicateKeyException：违反unique约束异常
     * @throws InsertException：插入数据异常
     */
    Business reg(Business business)throws DuplicateKeyException, InsertException;

    /**
     *
     * @param businessName
     * @param password
     * @return
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     * @throws DuplicateKeyException
     */
    Business login(String businessName,
                   String password)
            throws UserNotFoundException, PasswordNotMatchException,DuplicateKeyException;

    /**
     *
     * @param businessId:商家id
     * @param newPassword：新密码
     * @param oldPassword：原密码
     * @throws UserNotFoundException：用户未找到异常
     * @throws PasswordNotMatchException：原密码错误异常
     * @throws UpdateException：更新数据异常
     */
    void updataPassword(Integer businessId,String newPassword,String oldPassword)
            throws UserNotFoundException,PasswordNotMatchException, UpdateException;

    /**
     * 上传头像
     * @param businessId 商家id
     * @param avatar 商家头像
     * @throws UserNotFoundException 商家名不存在异常
     * @throws UpdateException 更新数据异常
     */
    void changeAvatar(Integer businessId, String avatar) throws UserNotFoundException, UpdateException;

}
