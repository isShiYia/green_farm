package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.entity.Cart;
import cn.nchu.green_farm.service.ICartService;
import cn.nchu.green_farm.util.ResponseResult;
import cn.nchu.green_farm.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 购物车控制器类
 * @author Choococo
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    // http://localhost:8080/cart/add?pid=1&count=10&price=1024
//     @GetMapping("/add")
    @PostMapping("/add_to_cart")
    public ResponseResult<Void> addToCart(HttpSession session, Cart cart) {
        // 从session中获取username
        String username = session.getAttribute("username").toString();
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 将uid封装到cart中
        cart.setUid(uid);
        // 执行业务方法
        cartService.addToCart(username, cart);
        // 返回
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/cart/list
    @GetMapping("/list")
    public ResponseResult<List<CartVO>> getByUid(HttpSession session) {
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 执行查询结果
        List<CartVO> list = cartService.getByUid(uid);
        // 返回
        return new ResponseResult<List<CartVO>>(SUCCESS,list);

    }

    @GetMapping("/add_count") // 点击按钮
    public ResponseResult<Void> addCount(@RequestParam("id") Integer id, HttpSession session) {
        // 从session中获取用户名
        String username = session.getAttribute("username").toString();
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 执行业务
        cartService.addCount(username, id, uid);
        // 返回
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/cart/reduce_count?id=7
     @GetMapping("/reduce_count") // 点击按钮
//     @PostMapping("/reduce_count") // 点击按钮
    public ResponseResult<Void> reduceCount(@RequestParam("id") Integer id, HttpSession session) {
        // 从session中获取用户名
        String username = session.getAttribute("username").toString();
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 执行业务
        cartService.reduceCount(username, id, uid);
        // 返回
        return new ResponseResult<>(SUCCESS);
    }

    // http://localhost:8080/cart/get_by_ids?cart_id=7,8
    @GetMapping("/get_by_ids")
    public ResponseResult<List<CartVO>> getByIds(@RequestParam("cart_id") Integer[] ids) {
        List<CartVO> list = cartService.getByIds(ids);
        return new ResponseResult<>(SUCCESS, list);
    }


}
