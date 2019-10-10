package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.controller.exception.FileEmptyException;
import cn.nchu.green_farm.controller.exception.FileSizeOutOfLimitException;
import cn.nchu.green_farm.controller.exception.FileTypeNotSupportException;
import cn.nchu.green_farm.controller.exception.FileUploadException;
import cn.nchu.green_farm.entity.Business;
import cn.nchu.green_farm.service.impl.BusinessServiceImpl;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/business")
public class BusinessController extends BaseController {
    @Autowired
    private BusinessServiceImpl businessService;

    /**允许上传文件的名称*/
    public static final String UPLOAD_DIR_NAME = "upload";
    public static final String LICENSE_DIR_NAME = "license";

    /**上传文件的大小*/
    public static final long FILE_MAX_SIZE = 5 * 1024 * 1024;

    /**允许上传的文件类型*/
    public static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();
    public static final List<String> FILE_CONTENT_TYPES2 = new ArrayList<>();

    static {
        FILE_CONTENT_TYPES.add("image/jpeg");
        FILE_CONTENT_TYPES.add("image/png");
        FILE_CONTENT_TYPES2.add("application/pdf");
        FILE_CONTENT_TYPES2.add("image/png");


    }
    //图片路径  http://localhost:8080/license/157050174312689414258.PNG
    //商家注册控制器
    @PostMapping("/reg.do")

    public ResponseResult<Business> handleReg(Business business,HttpSession session,@RequestParam("theLicense") MultipartFile uploadFile) {
        //businessService.reg(business);
        System.out.println(business);
        System.out.println(uploadFile.getContentType());
        // 【1】检查操作
        // 检查是否存在上传文件
        if (uploadFile.isEmpty()) {
            // 上传完文件为空，抛出异常：FileEmptyException
            throw new FileEmptyException("没有选择上传的文件，或选中的文件为空！上传失败!");
        }
        // 检查文件大小
        if (uploadFile.getSize() > FILE_MAX_SIZE ) {
            // 上传文件大小超出限制抛出异常：FileSizeOutOfLimitException
            throw new FileSizeOutOfLimitException("上传文件大小超出限制!上传文件失败!");
        }
        // 检查文件类型
        if (!FILE_CONTENT_TYPES2.contains(uploadFile.getContentType())) {
            // 不包含->上传文件失败,抛出异常：FileTypeNotSupportException
            throw new FileTypeNotSupportException("上传文件的类型不允许!上传文件失败!");
        }
        // 【2】确定保存头像的文件夹是否存在
        // 确定上传文件路径：通过session,从服务器中取到这个值
        String pathname = session.getServletContext().getRealPath(LICENSE_DIR_NAME);
        // 确定文件(夹)名
        File parent = new File(pathname);
        if (!parent.exists()) {
            // 如果不存在upload这个文件夹，就创建一个这样的文件夹
            // 这个是在第一次的时候使用的，后面就不会用到这个操作
            parent.mkdirs();
        }

        // 【3】给上传文件重新起名字
        // 1. 将从客户端获取的文件名
        String originalFilename = uploadFile.getOriginalFilename();
        // 后缀-截取
        String suffix =  originalFilename.substring(originalFilename.lastIndexOf("."));
        // 因为系统ms值，可能会重复，所以加上一个随机数（）这里拼接字符串的时候要放在中间
        String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000)+ 10000000)  + suffix ;
        File dest = new File(parent, fileName);
        // 执行保存操作
        try {
            uploadFile.transferTo(dest);
            System.err.println("上传完成!");
        } catch (IOException e) {
            // 这种抛出异常的方法叫做：异常的再次封装
            // 这种IOException不适合统一异常处理，必须catch然后在处理
            // 抛出异常：上传失败
            throw new FileUploadException("上传失败!");
        }

        // 更新数据LICENSE_DIR_NAME
        String lisense = "/" + LICENSE_DIR_NAME + "/" + fileName;
        business.setLicense(lisense);
//        System.out.println(lisense);
//        System.out.println(business);
        Business reg = businessService.reg(business);
        System.out.println(reg);


        return new ResponseResult<>(SUCCESS,reg);
    }
    //商家登录控制器
    @PostMapping("/login.do")
    public ResponseResult<Business> handleLogin(
            @RequestParam("businessName") String businessName,
            @RequestParam("password") String password, HttpSession session){
        Business data = businessService.login(businessName, password);
        session.setAttribute("businessId",data.getId());
        session.setAttribute("businessName",data.getBusinessName());
        System.out.println(data);
        return new ResponseResult<>(SUCCESS,data);
    }
    //商家修改密码
    @PostMapping("/password.do")
    public ResponseResult<Void> handlePassword(
            @RequestParam("newPassword") String newPassword,
            @RequestParam("oldPassword") String oldPassword,
            HttpSession session){

        businessService.updataPassword(getBidFromSession(session),newPassword,oldPassword);
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
        Integer businessId = getBidFromSession(session);
        // 更新头像数据
        String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
        businessService.changeAvatar(businessId, avatar);
        // 返回
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setCode(SUCCESS);
        rr.setData(avatar); // 就是文件的路径
        System.out.println(avatar);
        return rr;
    }
}
