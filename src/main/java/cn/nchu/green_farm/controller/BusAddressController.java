package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.Address;
import cn.nchu.green_farm.entity.BusinessAddress;
import cn.nchu.green_farm.service.IAddressService;
import cn.nchu.green_farm.service.IBusinessAddressService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 商家地址控制器层
 * @author Jianhzhong
 */
@RestController
@RequestMapping("/bus_address")
public class BusAddressController extends  BaseController {

    @Autowired
    private IBusinessAddressService businessAddressService;

    // http://localhost:8080/bus_address/create?name=Jack&province=440000&city=440300&area=440305&phone=13800138666&tag=Company

//    @GetMapping("/create")
    @PostMapping("/create")
    public ResponseResult<Address> handleCreate(BusinessAddress businessAddress, HttpSession session) {
        // 首先需要将用户的id封装到收货地址中，因为在业务层操作中没有将bid封装到收货地址的表中
        // 【注意】 bid只有在控制器层在session中才可以拿到bid
        businessAddress.setBid(getBidFromSession(session));
        businessAddressService.create(businessAddress, session.getAttribute("username").toString());
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/bus_address/list
    @RequestMapping("/list")
    public ResponseResult<List<BusinessAddress>> getListByUid(HttpSession session) {
        List<BusinessAddress> list = businessAddressService.getListByBid(getBidFromSession(session));
        return new ResponseResult<>(SUCCESS, list);
    }

    // http://localhost:8080/bus_address/default/10
    @GetMapping("/default/{id}") // 这里的id是需要显示在地址栏中的
    public ResponseResult<Void> setDefault(HttpSession session, @PathVariable("id") Integer id) {
        businessAddressService.setDefault(getBidFromSession(session), id);
        return new ResponseResult<>(SUCCESS);
    }

    @GetMapping("/delete/{id}")
    public ResponseResult<Void> deleteById(HttpSession session, @PathVariable("id") Integer id) {
        businessAddressService.delete(getBidFromSession(session), id);
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/bus_address/info/13
    @GetMapping("/info/{id}")
    public ResponseResult<BusinessAddress> handleInfo(HttpSession session, @PathVariable("id") Integer id) {
        BusinessAddress businessAddress = businessAddressService.getById(id, getUidFromSession(session));
        return new ResponseResult<>(SUCCESS, businessAddress);
    }

    // http://localhost:8080/bus_address/change_info/19
    @GetMapping("/change_info/{id}")
    public ResponseResult<Void> handleChangeInfo(BusinessAddress businessAddress, HttpSession session, @PathVariable("id") Integer id) {
        businessAddress.setId(id);
        businessAddressService.changeInfo(businessAddress, session.getAttribute("username").toString());
        return new ResponseResult<>(SUCCESS);
    }
}
