package cn.nchu.green_farm.service.exception;

/**
 * 用户名不存在 
 */
public class AdminNotFoundException extends ServiceException{

	private static final long serialVersionUID = -2470715991347231448L;

	public AdminNotFoundException() {
		super();
	}

	public AdminNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdminNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdminNotFoundException(String message) {
		super(message);
	}

	public AdminNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
