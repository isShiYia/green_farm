package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.mapper.BusinessMapper;
import cn.nchu.green_farm.service.IBusinessService;
import cn.nchu.green_farm.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 商家持久层实现
 * ...
 */
@Service
public class BusinessServiceImpl implements IBusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    /**
     *
     * @param business:商家数据
     * @return 商家数据
     * @throws DuplicateKeyException：违反unique约束异常
     * @throws InsertException：数据插入异常
     */
    @Override
    public Business reg(Business business) throws DuplicateKeyException, InsertException {
        Business data = getByBusinessName(business.getBusinessName());
        //判断数据是否为空
        if(data==null){
            // 是：允许注册
            // 补充需要添加的字段
            // 设置是否删除字段为否
            business.setIsDelete(0);
            //设置注册默认状态为审核中
            business.setTheStatus(0);
            Date now = new Date();
            business.setCreatedTime(now);
            business.setCreatedUser(business.getBusinessName());
            business.setModifiedTime(now);
            business.setModifiedUser(business.getBusinessName());
            // 【处理密码加密】
            // 1. 根据UUID生成盐值，这里的盐值统一大写
            String salt = UUID.randomUUID().toString().toUpperCase();
            // 2. 获取前端界面输入的密码作为原始密码
            String srcPassword = business.getPassword();
            // 3. 用MD5对密码进行加密,获得新密码
            String md5Password = getMd5Password(srcPassword, salt);
            // 4. 将加密后的密码存放到数据库中
            business.setPassword(md5Password);
            // 5. 将盐值存放到数据库中
            business.setSalt(salt);
            // 执行注册
            addnew(business);
            return business;
        }
        else{
            throw new DuplicateKeyException("注册商家失败!您注册的商家名"+business.getBusinessName()+"已存在!");
        }
    }

    /**
     *
     * @param businessName:商家名
     * @param password：密码
     * @return 商家信息
     * @throws UserNotFoundException：用户名未找到异常
     * @throws PasswordNotMatchException：密码错误异常
     * @throws DuplicateKeyException：违反unique约束异常
     */
    @Override
    public Business login(String businessName, String password) throws UserNotFoundException, PasswordNotMatchException, DuplicateKeyException {
        Business data=getByBusinessName(businessName);
        if(data==null){
            throw new UserNotFoundException("登录失败！商家名不存在或密码错误！：商家名");
        }
        else if(data.getIsDelete()==1){
            throw new UserNotFoundException("登陆失败！该商家"+businessName+"已被封号！");
        }
        else if(data.getTheStatus()==0){
            throw new UserNotFoundException("登录失败！该商家"+businessName+"注册审核中！");
        }
        else if(data.getTheStatus()==2){
            throw new UserNotFoundException("登录失败！该商家"+businessName+"注册审核未通过！");
        }
        String md5Password=getMd5Password(password,data.getSalt());
         if(md5Password.equals(data.getPassword())){
             // 匹配成功，将密码和盐值设为null
             data.setPassword(null);
             data.setSalt(null);
             // 返回用户数据
            return data;
        }
         else {
             throw new UserNotFoundException("登录失败！商家名不存在或密码错误！：密码");
         }
        }

    /**
     *
     * @param businessId:商家id
     * @param newPassword：新密码
     * @param oldPassword：原密码
     * @throws UserNotFoundException:用户未找到异常
     * @throws PasswordNotMatchException：原密码错误异常
     * @throws UpdateException：数据更新异常
     */
    @Override
    public void updataPassword(Integer businessId, String newPassword, String oldPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException {
        Business data=getById(businessId);
        if(data==null){
            throw new UserNotFoundException("修改失败！商家信息未找到，请重试！");
        }
        String md5Password=getMd5Password(oldPassword,data.getSalt());
        if (md5Password.equals(data.getPassword())) {
            Date  modifiedTime=new Date();
            String  modifiedUser=data.getBusinessName();
            String newMd5Password=getMd5Password(newPassword,data.getSalt());
            updatePassword(businessId,newMd5Password,modifiedUser,modifiedTime);
            System.out.println("成功");
        } else {
            throw new PasswordNotMatchException("修改失败！原密码错误，请重试！");
        }
    }

        //上传头像
    @Override
    public void changeAvatar(Integer businessId, String avatar) throws UserNotFoundException, UpdateException {
        // 根据用户id查询用户数据
        Business data = getById(businessId);
        // 判断用户数据是否为null
        if (data == null) {
            throw new UserNotFoundException("上传头像失败！您尝试访问的用户数据不存在!");
        }
        if (data.getIsDelete() == 1) {
            throw new UserNotFoundException("上传头像失败！您尝试访问的用户数据已被注销!");
        } else if (data.getTheStatus() == 0) {
            throw new UserNotFoundException("上传头像失败！该商家注册审核中！");

        } else if (data.getTheStatus() == 2) {
            throw new UserNotFoundException("上传头像失败！该商家注册审核未通过！");
        }
            // 执行修改
            updateAvatar(businessId, avatar, data.getBusinessName(), new Date());

    }

    private Business getById(Integer businessId){
        return businessMapper.getById(businessId);
    }

    private Business getByBusinessName(String businessName){
        return businessMapper.getByBusinessName(businessName);
    }

    /**
     * 对原始密码和盐值执行MD5加密
     * @param srcPassword 原始密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMd5Password(String srcPassword,String salt) {
        String src = salt + srcPassword + salt;
        for (int i = 0; i < 10 ; i++) {
            src = DigestUtils.md5DigestAsHex(src.getBytes()).toUpperCase();
        }
        return src;
    }

    /**
     * 插入商家数据
     * @param business：商家数据
     */
    private void addnew(Business business) {
        Integer rows = businessMapper.addnew(business);
        if (rows != 1) {
            throw new InsertException("新增商家数据时出现未知错误!");
        }
    }

    private void updatePassword(Integer businessId,String newPassword, String modifiedUser,Date modifiedTime){
        Integer rows = businessMapper.updatePassword(businessId, newPassword, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("修改密码时发生未知错误!修改密码失败!");
        }
    }

    /**
     * 上传头像
     * @param busisnessId 用户id
     * @param avatar 用户头像
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改执行时间
     */
    private void updateAvatar(Integer busisnessId,String avatar, String modifiedUser,Date modifiedTime) {
        Integer rows = businessMapper.updateAvatar(busisnessId, avatar, modifiedUser, modifiedTime);
        if (rows != 1) {
            throw new UpdateException("上传头像失败!上传头像时出现未知错误!");
        }
    }

}
