package cn.nchu.green_farm.vo;

import java.io.Serializable;

/**
 * 购物车VO类，这里是购物车中要显示的字段
 * @author Choococo
 */
public class CartVO implements Serializable {

    private static final long serialVersionUID = -716013301762778822L;

    /**VO类id*/
    private Integer id;
    /**用户id*/
    private Integer uid;
    /**农产品id*/
    private Integer pid;
    /**农茶品标题*/
    private String title;
    /**农产品图片*/
    private String image;
    /**添加到购物车中的数量*/
    private Integer count;
    /**购物车中的价格:因为可能用户会长时间加农产品放到购物车中，
     * 但是这个商品价格可能会发生变动*/
    private Double oldPrice;
    /**农场品详情的价格*/
    private Double newPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                '}';
    }
}
