package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.FarmProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmProductMapper {
    /**
     *
     * @param farmProduct :农产品信息
     * @return：数据库受影响的行数
     */
    Integer addnew(FarmProduct farmProduct);
}
