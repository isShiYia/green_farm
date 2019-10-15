package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.FarmProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmProductMapper {
    /**
     *
     * @param farmProduct :农产品信息
     * @return：数据库受影响的行数
     */
    Integer addnew(FarmProduct farmProduct);

    /**
     *
     * @return 返回新到好货数据List
     */
    List<FarmProduct> newList();

    /**
     *
     * @return 返回热销数据List
     */
    List<FarmProduct> hotList();
}
