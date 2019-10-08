package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.service.IAddressService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 收货地址控制器层
 * @author Choococo
 */
@RestController
@RequestMapping("/address")
public class AddressController extends  BaseController {

    @Autowired
    private IAddressService addressService;

    // http://localhost:8080/address/create?name=Jack&province=440000&city=440300&area=440305&phone=13800138666&tag=Company

//    @GetMapping("/create")
    @PostMapping("/create")
    public ResponseResult<Address> handleCreate(Address address, HttpSession session) {
        // 首先需要将用户的id封装到收货地址中，因为在业务层操作中没哟将uid封装到收货地址的表中
        // 【注意】 uid只有在控制器层在session中才可以拿到uid
        address.setUid(getUidFromSession(session));
        addressService.create(address, session.getAttribute("username").toString());
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/address/list
    @RequestMapping("/list")
    public ResponseResult<List<Address>> getListByUid(HttpSession session) {
        List<Address> list = addressService.getListByUid(getUidFromSession(session));
        return new ResponseResult<>(SUCCESS, list);
    }



}
