package cn.nchu.green_farm.entity;

/**
 * CREATE TABLE `t_farm_product` (
 *   `id` int(200) NOT NULL AUTO_INCREMENT COMMENT '商品id',
 *   `bid` bigint(20) DEFAULT NULL COMMENT '商家id',
 *   `item_type` varchar(100) DEFAULT NULL COMMENT '商品系列',
 *   `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
 *   `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
 *   `price` decimal(20,2) DEFAULT NULL COMMENT '商品单价',
 *   `num` int(10) DEFAULT NULL COMMENT '库存数量',
 *   `image` varchar(500) DEFAULT NULL COMMENT '图片路径',
 *   `status` int(1) DEFAULT '1' COMMENT '商品状态  1：上架   2：下架   3：删除',
 *   `priority` int(10) DEFAULT NULL COMMENT '显示优先级',
 *   `created_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
 *   `created_user` varchar(50) DEFAULT NULL COMMENT '创建人',
 *   `modified_user` varchar(50) DEFAULT NULL COMMENT '最后修改人',
 *   PRIMARY KEY (`id`) USING BTREE
 * ) ENGINE=InnoDB AUTO_INCREMENT=10000029 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT
 */
public class FarmProduct extends BaseEntity {
    private static final long serialVersionUID = 8730391880987963574L;
    /**产品id*/
    private Integer id;
    /**商家id*/
    private Integer bid;
    /**产品种类0：水果，1：蔬菜，2：其他*/
    private Integer itemType;
    /**产品名*/
    private String title;
    /**商品卖点*/
    private String sellPoint;
    /**产品价格*/
    private Double price;
     /**产品库存*/
    private Integer num;
    /**图片文件夹路径*/
    private String image;
    /**产品状态0：审核中，1：通过，2：未通过*/
    private Integer status;
    /**产品优先级*/
    private Integer priority;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "FarmProduct{" +
                "id=" + id +
                ", bid=" + bid +
                ", itemType=" + itemType +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }
}
