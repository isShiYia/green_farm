package cn.nchu.green_farm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类
 * @author Choococo
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 4229314630996139628L;

    private String createUser;
    private Date createTime;
    private String modifiedUser;
    private Date modifiedTime;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
