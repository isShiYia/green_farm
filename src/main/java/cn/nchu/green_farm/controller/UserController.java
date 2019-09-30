package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.controller.exception.FileEmptyException;
import cn.nchu.green_farm.controller.exception.FileSizeOutOfLimitException;
import cn.nchu.green_farm.controller.exception.FileTypeNotSupportException;
import cn.nchu.green_farm.controller.exception.FileUploadException;
import cn.nchu.green_farm.entity.User;
import cn.nchu.green_farm.service.IUserService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Choococo
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**允许上传文件的名称*/
    public static final String UPLOAD_DIR_NAME = "upload";

    /**上传文件的大小*/
    public static final long FILE_MAX_SIZE = 5 * 1024 * 1024;

    /**允许上传的文件类型*/
    public static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();

    static {
        FILE_CONTENT_TYPES.add("image/jpeg");
        FILE_CONTENT_TYPES.add("image/png");
    }



    @Autowired
    private IUserService userService;

    @PostMapping("/reg.do")
    public ResponseResult<Void> handleReg(User user) {
        userService.reg(user);
        return new ResponseResult<>(SUCCESS);
    }

//    @GetMapping("login.do")
    @PostMapping("login.do")
    public ResponseResult<User> handleLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        // 执行业务端的业务
        User user = userService.login(username, password);
        // 将用户id和用户名发到session中，保存到服务端
        session.setAttribute("uid", user.getId());
        session.setAttribute("username", user.getUsername());
        return new ResponseResult<>(SUCCESS,user);
    }

    // @GetMapping("/password.do") // 注意：这里需要对拦截器进行注册
    @PostMapping("/password.do") // 注意：这里需要对拦截器进行注册
    public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,@RequestParam("new_password") String newPassword, HttpSession session) {
        userService.changePassword(getUidFromSession(session), oldPassword, newPassword);
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("/info.do")
    public ResponseResult<User> getInfo(HttpSession session) {
        User user = userService.getById(getUidFromSession(session));
        return new ResponseResult<>(SUCCESS,user);
    }

    @PostMapping("/change_info.do")
    public ResponseResult<Void> changeInfo(User user, HttpSession session) {
        user.setId(getUidFromSession(session));
        userService.changeInfo(user);
        return new ResponseResult<>(SUCCESS);
    }

    /*
        文件上传
     */
    @PostMapping("/upload.do")
    public ResponseResult<String> handleUpload(HttpSession session, @RequestParam("file") MultipartFile file) {
        // 【1】检查操作
        // 检查是否存在上传文件
        if (file.isEmpty()) {
            // 上传完文件为空，抛出异常：FileEmptyException
            throw new FileEmptyException("没有选择上传的文件，或选中的文件为空！上传失败!");
        }
        // 检查文件大小
        if (file.getSize() > FILE_MAX_SIZE ) {
            // 上传文件大小超出限制抛出异常：FileSizeOutOfLimitException
            throw new FileSizeOutOfLimitException("上传文件大小超出限制!上传文件失败!");
        }
        // 检查文件类型
        if (!FILE_CONTENT_TYPES.contains(file.getContentType())) {
            // 不包含->上传文件失败,抛出异常：FileTypeNotSupportException
            throw new FileTypeNotSupportException("上传文件的类型不允许!上传文件失败!");
        }
        // 【2】确定保存头像的文件夹是否存在
        // 确定上传文件路径：通过session,从服务器中取到这个值
        String pathname = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
        // 确定文件(夹)名
        File parent = new File(pathname);
        if (!parent.exists()) {
            // 如果不存在upload这个文件夹，就创建一个这样的文件夹
            // 这个是在第一次的时候使用的，后面就不会用到这个操作
            parent.mkdirs();
        }

        // 【3】给上传文件重新起名字
        // 1. 将从客户端获取的文件名
        String originalFilename = file.getOriginalFilename();
        // 后缀-截取
        String suffix =  originalFilename.substring(originalFilename.lastIndexOf("."));
        // 因为系统ms值，可能会重复，所以加上一个随机数（）这里拼接字符串的时候要放在中间
        String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000)+ 10000000)  + suffix ;
        File dest = new File(parent, fileName);
        // 执行保存操作
        try {
            file.transferTo(dest);
            System.err.println("上传完成!");
        } catch (IOException e) {
            // 这种抛出异常的方法叫做：异常的再次封装
            // 这种IOException不适合统一异常处理，必须catch然后在处理
            // 抛出异常：上传失败
            throw new FileUploadException("上传失败!");
        }
        // 获取当前用户的id
        Integer uid = getUidFromSession(session);
        // 更新头像数据
        String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
        userService.changeAvatar(uid, avatar);
        // 返回
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setState(SUCCESS);
        rr.setData(avatar); // 就是文件的路径
        return rr;
    }
}
