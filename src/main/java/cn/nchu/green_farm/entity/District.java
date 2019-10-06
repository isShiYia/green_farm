package cn.nchu.green_farm.entity;

import java.io.Serializable;

/**
 * 省市区数据实体类 -- 是一种字典性质的实体类
 *  * （唯一的作用就是用来被查询，没有增删改操作）
 *  * 主要就是用来查询某个父级的所有子级列表（全国所有省、某省的所有市、某市的所有区），
 *  * 根据代号查询名称
 * @author Choococo
 */
public class District implements Serializable {

    private static final long serialVersionUID = 6964398643711168301L;

    private Integer id;
    private String parent;
    private String code;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", parent='" + parent + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
