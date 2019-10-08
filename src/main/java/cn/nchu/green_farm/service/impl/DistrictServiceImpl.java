package cn.nchu.green_farm.service.impl;

import cn.nchu.green_farm.entity.District;
import cn.nchu.green_farm.mapper.DistrictMapper;
import cn.nchu.green_farm.service.IDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 省市区数据处理业务层接口
 * @author Choococo
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<District> getListByParent(String parent) {
        return findByParent(parent);
    }

    @Override
    public District getByCode(String code) {
        return findByCode(code);
    }



    /**
     * 根据父级代号获取省/市/区列表
     * @param parent 父级代号
     * @return 省/市/区的列表
     */
    private List<District> findByParent(String parent) {
        return districtMapper.findByParent(parent);
    }

    /**
     * 根据代号查询省/市/区的详情
     * @param code 省/市/区的代号
     * @return 省/市/区的详情，如果没有匹配的数据，返回详情
     */
    private District findByCode(String code) {
        return districtMapper.findByCode(code);
    }


}
