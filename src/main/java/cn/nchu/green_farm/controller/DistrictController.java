package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.District;
import cn.nchu.green_farm.service.IDistrictService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 省市区数据控制器层
 * @author Choococo
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @RequestMapping("list/{parent}")
    public ResponseResult<List<District>>  getListByParent(@PathVariable("parent") String parent) {
        List<District> list = districtService.getListByParent(parent);
        return  new ResponseResult<>(SUCCESS,list);
    }
}
