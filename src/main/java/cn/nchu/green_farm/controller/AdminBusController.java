package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.service.IAddressService;
import cn.nchu.green_farm.service.IAdminBusService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 收货地址控制器层
 * @author Choococo
 */
@RestController
@RequestMapping("/admin/bus")
public class AdminBusController extends  BaseController {

    @Autowired
    private IAdminBusService adminBusService;

    // http://localhost:8080/admin/bus/list
    @RequestMapping("/list")
    public ResponseResult<List<Business>> getListByUid() {
        List<Business> list = adminBusService.getListOfBusiness();
        return new ResponseResult<>(SUCCESS, list);
    }

    // http://localhost:8080/admin/bus/pass/1
    @GetMapping("/pass/{id}")
    public ResponseResult<Void> handlePass(HttpSession session, @PathVariable("id") Integer id) {
//        adminBusService.changeStatusPass(id,session.getAttribute("adminName").toString());
        adminBusService.changeStatusPass(id,"admin07");
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/admin/bus/no_pass/1
    @GetMapping("no_pass/{id}")
    public ResponseResult<Void> handleNoPass(HttpSession session, @PathVariable("id") Integer id) {
        adminBusService.changeStatusNoPass(id, "admin07");
        return new ResponseResult<>(SUCCESS);
    }

}
