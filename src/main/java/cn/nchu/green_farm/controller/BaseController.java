package cn.nchu.green_farm.controller;

import javax.servlet.http.HttpSession;

import cn.nchu.green_farm.controller.exception.*;
import cn.nchu.green_farm.service.exception.*;
import cn.nchu.green_farm.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 当前项目中所有控制器类的基类
 */
public abstract class BaseController {
	/**
	 * 正确响应的代号
	 */
	public static final Integer SUCCESS = 200;
	public static final Integer SUCCESSFUL = 0;

	@ExceptionHandler({ServiceException.class, RequestException.class})// 异常的范围
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e) {

		Integer state = null;
		if (e instanceof DuplicateKeyException) {
			state = 400;
			// 400-违反了Unique约束的异常
			// return new ResponseResult<>(400, e);
		}else if (e instanceof UserNotFoundException) {
			// 401-用户数据不存在
			state = 401;
			// return new ResponseResult<>(401, e);
		} else if (e instanceof PasswordNotMatchException) {
			// 402-密码错误
			state = 402;
			// return new ResponseResult<>(402, e);
		} else if (e instanceof AddressNotFoundException) {
			// 403-收获地址不存在
			state = 403;
			// return new ResponseResult<>(402, e);
		} else if (e instanceof AccessDefinedException) {
			// 404-密码错误
			state = 404;
			// return new ResponseResult<>(402, e);
		} else if (e instanceof BusinessNotFoundException) {
			// 405-商家数据不存在
			state = 405;
			// return new ResponseResult<>(402, e);
		} else if (e instanceof InsertException) {
			// 500-插入数据异常
			state = 500;
			// return new ResponseResult<>(500, e);
		} else if (e instanceof UpdateException) { 
			// 501-更新数据异常
			state = 501;
			// return new ResponseResult<>(501,e);
		}else if (e instanceof DeleteException) { 
			// 502-删除数据异常
			state = 502;
			// return new ResponseResult<>(501,e);
		} else if(e instanceof FileEmptyException) {
			// 从这里开始，要先写子集，再写父集异常
			// 600-上传的文件为空的异常
			state =600;
		}else if(e instanceof FileSizeOutOfLimitException) {
			// 601-上传文件超出限制的异常
			state =601;
		}else if(e instanceof FileTypeNotSupportException) {
			// 602-上传的文件类型不支持异常
			state =602;
		}else if(e instanceof FileUploadException) {
			// 610-文件上传异常
			state =610;
		} 
		
		// return null;
		return new ResponseResult<>(state,e);
	}
	
	/**
	 * 从Session中获取uid
	 * @param session HttpSession对象
	 * @return 当前用户登录的id
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}

	/**
	 *
	 * @param session
	 * @return 当前商家登陆的id
	 */
	protected Integer getBidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("businessId").toString());
	}
}
