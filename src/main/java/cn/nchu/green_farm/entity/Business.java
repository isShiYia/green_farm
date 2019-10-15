package cn.nchu.green_farm.entity;

import java.util.Date;

public class Business extends BaseEntity {
    private static final long serialVersionUID = -6237191313025311552L;
    //
    /**商家id*/
    private Integer id;
    /**商家名*/
    private String businessName;
    /**商家密码*/
    private String password;
    /**盐值36位*/
    private String salt;
    /**性别*/
    private Integer gender;
    /**电话*/
    private String phone;
    /**邮箱*/
    private String email;
    /**头像*/
    private String avatar;
    /*营业执照*/
    private String license;
    /** '是否删除，0-未删除，1-已删除',*/
    private Integer isDelete;
    /** 0:审核中，1：审核通过：2：未通过*/
    private Integer theStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getTheStatus() {
        return theStatus;
    }

    public void setTheStatus(Integer theStatus) {
        this.theStatus = theStatus;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", license='" + license + '\'' +
                ", isDelete=" + isDelete +
                ", theStatus=" + theStatus +
                '}';
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
