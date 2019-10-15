package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.IFarmProductListService;
import cn.nchu.green_farm.util.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//     @GetMapping("/list")
     @PostMapping("/list")
    public ResponseResult<List<FarmProduct>> handleList(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit) {
        List<FarmProduct> list =  farmProductListService.getListOfPage(page, limit);
        Integer count = farmProductListService.getListOfCount();
        return new ResponseResult<>(count,SUCCESS,list);
    }

    @GetMapping("/details/{id}")
    public ResponseResult<FarmProduct> getById(@PathVariable("id") Integer id) {
        FarmProduct farmProduct = farmProductListService.getById(id);
        return new ResponseResult<>(SUCCESS,farmProduct);
    }



}
