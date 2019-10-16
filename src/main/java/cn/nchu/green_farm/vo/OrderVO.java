package cn.nchu.green_farm.vo;

import cn.nchu.green_farm.entity.OrderItem;

import java.io.Serializable;
import java.util.List;

/**
 * 订单VO类
 * @author Choococo
 */
public class OrderVO implements Serializable {

    private Integer id;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvDistrict;
    private String recvAddress;
    private Double pay;
    private Integer status;
    private List<OrderItem> items;

}
