package cn.nchu.green_farm.entity;

/**
 * 购物车实体类:
 *      这里在将商品的信息提交到购物车中只需要商品id，价格和数量
 *      至于图片是在农产品表中存在的，可以不用
 * @author Choococo
 */
public class Cart extends BaseEntity {

    private static final long serialVersionUID = -2663797817839925123L;

    /**购物车id*/
    Integer id;
    /**用户id*/
    Integer uid;
    /**农产品商品id product*/
    Integer pid;
    /**商品价格*/
    Double price;
    /**'商品数量'*/
    Integer count;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
