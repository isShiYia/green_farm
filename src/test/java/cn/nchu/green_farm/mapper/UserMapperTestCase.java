package cn.nchu.green_farm.mapper;

import cn.nchu.green_farm.entity.User;
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
public class UserMapperTestCase {

    @Resource
    private UserMapper userMapper;

    @Test // 增加用户数据
    public void addnew() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        user.setSalt("1234");
        user.setEmail("admin@nchu.cn");
        user.setPhone("13988889999");
        Integer rows = userMapper.addnew(user);
        System.err.println("rows=" + rows);

    }

    @Test // 根据用户名查询数据
    public void findByUsername() {
        String username = "admin";
//        String username = "admin1";
        User user = userMapper.findByUsername(username);
        System.err.println("user=" + user);
    }

    @Test // 根据用户id查询用户数据，为了查询密码和盐值
    public void findById() {
        Integer uid = 8;
        User user = userMapper.findById(uid);
        System.err.println("user=" + user);

    }

    @Test
    public void updatePassword() {
        Integer uid = 8;
        String password = "1234";
        String modifiedUser = "root";
        Date modifiedTime = new Date();
        Integer result = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
        System.err.println("result=" +result);
    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setId(7);
        user.setPhone("13900008888");
        user.setEmail("spring@nchu.cn");
        user.setGender(1);
        Integer rows = userMapper.updateInfo(user);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateAvatar() {
        Integer uid = 1;
        String avatar = "d:/upload";
        String modifiedUser = "admin";
        Date modifiedTime = new Date();
        Integer  rows = userMapper.updateAvatar(uid, avatar,modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }




}
