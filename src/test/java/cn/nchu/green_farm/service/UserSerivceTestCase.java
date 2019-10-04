package cn.nchu.green_farm.service;

import cn.nchu.green_farm.entity.User;
import cn.nchu.green_farm.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Choococo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSerivceTestCase {

    @Resource
    private IUserService userService;

    @Test // 注册
    public void reg() {
        try {
            User user = new User();
            user.setUsername("root");
            user.setPassword("1234");
            user.setGender(1);
            user.setPhone("138001138001");
            user.setEmail("root@tedu.cn");
            User result  = userService.reg(user);
            System.err.println("result=" + result);
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }

    }

    @Test // 登录
    public void login() {
        try {
            String username = "spring";
            String password = "1234";
            User result  = userService.login(username, password);
            System.err.println("result=" + result);
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }

    @Test // 修改密码
    public void changePassword() {
        try {
            Integer uid = 7;
            String oldPassword = "12345";
            String newPassword = "1234";
            userService.changePassword(uid, oldPassword, newPassword);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }

    @Test
    public void changeInfo() {
        try {
            User user = new User();
            user.setId(3);
            user.setPhone("13977778888");
            user.setEmail("Sally@nchu.cn");
            user.setGender(0);
            user.setIsDelete(0);
            user.setModifiedUser(user.getUsername());
            user.setModifiedTime(new Date());
            userService.changeInfo(user);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }

    @Test
    public void getById() {
        try {
            Integer id = 3;
            User user = userService.getById(id);
            System.err.println("user=" + user);
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }

    @Test
    public void changeAvatar() {
        try {
            Integer uid = 2;
            String avatar = "d:/upload";
            userService.changeAvatar(uid, avatar);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println("错误类型:" + e.getClass().getName());
            System.err.println("错误描述:" + e.getMessage());
        }
    }


}
