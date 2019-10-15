package cn.nchu.green_farm.controller;

import cn.nchu.green_farm.controller.exception.FileEmptyException;
import cn.nchu.green_farm.controller.exception.FileSizeOutOfLimitException;
import cn.nchu.green_farm.controller.exception.FileTypeNotSupportException;
import cn.nchu.green_farm.controller.exception.FileUploadException;
import cn.nchu.green_farm.entity.FarmProduct;
import cn.nchu.green_farm.service.IFarmProductService;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/farmProduct")
public class FarmProductController extends BaseController{


    @Resource
    private IFarmProductService farmProductService;
    /**允许上传文件名称*/
    public static final String UPLOAD_DIR_NAME = "portal";
    /**上传文件的大小*/
    public static final long FILE_MAX_SIZE = 5 * 1024 * 1024;
    /**允许上传的文件类型*/
    public static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();

    static {
        FILE_CONTENT_TYPES.add("image/jpeg");
        FILE_CONTENT_TYPES.add("image/png");
    }

    @PostMapping("/create.do")
    public  ResponseResult<Void> handleCreate(
            FarmProduct farmProduct,
            HttpSession session,
            @RequestParam("goodsPhoto[]") MultipartFile[] uploadFile) {

        if (uploadFile.length!=5) {
            // 不包含->上传文件失败,抛出异常：FileTypeNotSupportException
            throw new FileTypeNotSupportException("上传文件的数量不为五!上传文件失败!");
        }

        System.out.println(farmProduct);
        System.out.println(uploadFile.length);
        System.out.println(uploadFile.toString());
        //五张样图的统一命名数组
        String[] chirdName={"1.png","2.png","3.png","4.png","5.png"};
        //该产品id的文件夹名
        String goodsFileName =farmProduct.getTitle()+ UUID.randomUUID().toString();
        // 确定上传文件路径：通过session,从服务器中取到这个值
        String pathname = session.getServletContext().getRealPath(UPLOAD_DIR_NAME)+"\\"+goodsFileName+"\\";
        // 存放产品图片的文件夹的路径
        String image = "/" + UPLOAD_DIR_NAME + "/" +goodsFileName+"/";
        //依次保存样图至服务器中
        for (int i=0;i<uploadFile.length;i++){
            System.out.println(uploadFile[i].getContentType());

            saveFile(pathname,uploadFile[i],chirdName[i]);
        }
        //存入商家id
        farmProduct.setBid(getBidFromSession(session));
        //存入商品图片文件夹路径
        farmProduct.setImage(image);
        farmProductService.create(farmProduct);
        System.out.println(farmProduct);
        return new ResponseResult<>(SUCCESS);
    }

    /**
     * 保存图片
     * @param pathname：上传文件路径
     * @param uploadFile：上传的图片
     * @param chirdName：图片名
     */
    private void saveFile(String pathname,MultipartFile uploadFile,String chirdName){
        // 检查是否存在上传文件
        if (uploadFile.isEmpty()) {
            throw new FileEmptyException("没有选择上传的文件，或选中的文件为空！上传失败!");
        }
        // 检查文件大小
        if (uploadFile.getSize() > FILE_MAX_SIZE ) {
            throw new FileSizeOutOfLimitException("上传文件大小超出限制!上传文件失败!");
        }
        // 检查文件类型
        if (!FILE_CONTENT_TYPES.contains(uploadFile.getContentType())) {
            throw new FileTypeNotSupportException("上传文件的类型不允许!上传文件失败!");
        }
        // 【2】确定保存头像的文件夹是否存在
        System.out.println(pathname);
        // 确定文件(夹)名
        File parent = new File(pathname);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 【3】给上传文件重新起名字
        File dest = new File(parent, chirdName);
        try {
            uploadFile.transferTo(dest);
            System.err.println("上传完成!");
        } catch (IOException e) {
            throw new FileUploadException("上传失败!");
        }

    }
    @PostMapping("/new_list")
    public ResponseResult<List<FarmProduct>> newList() {
        List<FarmProduct> list =  farmProductService.getNewList();
        return new ResponseResult<>(SUCCESS,list);
    }

    @PostMapping("/hot_list")
    public ResponseResult<List<FarmProduct>> hotList() {
        List<FarmProduct> list =  farmProductService.getHotList();
        return new ResponseResult<>(SUCCESS,list);
    }
}
