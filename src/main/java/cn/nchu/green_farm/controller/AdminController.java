package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.Admin;
import cn.nchu.green_farm.service.IAdminService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}