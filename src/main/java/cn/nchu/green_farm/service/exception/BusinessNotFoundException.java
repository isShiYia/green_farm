package cn.nchu.green_farm.service.exception;

/**
 * 商家数据不存在异常
 * @author Choococo
 */
public class BusinessNotFoundException extends ServiceException {

    private static final long serialVersionUID = -5249821282831340851L;

    public BusinessNotFoundException() {
    }

    public BusinessNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessNotFoundException(String message) {
        super(message);
    }

    public BusinessNotFoundException(Throwable cause) {
        super(cause);
    }
}
