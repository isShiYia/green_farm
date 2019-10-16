package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.IAdminService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Jianhzhong
 */

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("login.do")
    public ResponseResult<Admin> handleLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        // 执行业务端的业务
        Admin admin = adminService.login(username, password);
        // 将用户id和用户名发到session中，保存到服务端
        session.setAttribute("adminId", admin.getId());
        session.setAttribute("adminName", admin.getName());
        return new ResponseResult<>(SUCCESS,admin);
    }
    // http://localhost:8080/admin/farm_product/list
    @RequestMapping("/list") // 农产品列表
    public ResponseResult<List<FarmProduct>> getListByUid(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        List<FarmProduct> list = adminService.getListOfFarmProduct(page,limit);
        Integer count = adminService.getListOfFarmProductCount();
        return new ResponseResult<>(count,SUCCESS, list);
    }

    // http://localhost:8080/admin/farm_product/pass/1
    @GetMapping("/pass/{id}") // 农产品审核通过
    public ResponseResult<Void> handlePass(HttpSession session, @PathVariable("id") Integer id) {
        adminService.changeStatusPass(id,session.getAttribute("adminName").toString());
//        adminBusService.changeStatusPass(id,"admin07");
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/admin/farm_product/no_pass/1
    @GetMapping("no_pass/{id}") // 农产品审核不通过
    public ResponseResult<Void> handleNoPass(HttpSession session, @PathVariable("id") Integer id) {
        adminService.changeStatusNoPass(id, session.getAttribute("adminName").toString());
//        adminBusService.changeStatusNoPass(id, "admin07");
        return new ResponseResult<>(SUCCESS);
    }
}