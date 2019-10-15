package cn.nchu.green_farm.entity;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * @author Jianhzhong
 */
public class Admin {
    /**管理员账号*/
    private String name;

    /**管理员id*/
    private Integer id;

    /**管理员密码*/
    private String password;

    /**盐值36位*/
    private String salt;

    /** '是否删除，0-未删除，1-已删除',*/
    private Integer isDelete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
