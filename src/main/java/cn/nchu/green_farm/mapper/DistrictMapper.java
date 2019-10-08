package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 省市区数据字典持久层接口
 */
@Mapper
public interface DistrictMapper {

    /**
     * 根据父级代号获取省/市/区列表
     * @param parent 父级代号
     * @return 省/市/区的列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据代号查询省/市/区的详情
     * @param code 省/市/区的代号
     * @return 省/市/区的详情，如果没有匹配的数据，返回详情
     */
    District findByCode(String code);

}
