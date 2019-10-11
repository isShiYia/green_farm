package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.IFarmProductListService;
import cn.nchu.green_farm.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Choococo
 */
@RestController
@RequestMapping("/product_list")
public class FarmProductListController extends BaseController {

    @Autowired
    private IFarmProductListService farmProductListService;

    @GetMapping("/list")
    public ResponseResult<List<FarmProduct>> handleList(HttpSession session) {
        List<FarmProduct> list =  farmProductListService.getList();
        return new ResponseResult<>(SUCCESS,list);
    }



}
