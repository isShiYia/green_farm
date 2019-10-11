package cn.nchu.green_farm.util;

import java.io.Serializable;
/**
 * 服务器端向客户端响应结果的类型
 * @param <E> 服务器端向客户端响应数据的类型
 */
public class ResponseResult<E> implements Serializable{

	private static final long serialVersionUID = -1626793180717240861L;

	/** 状态码 */
	private Integer state;
	/** 异常信息 */
	private String message;
	/** 数据 */
	private E data;
	/** 数量 */
	private Integer count;

	public ResponseResult() {
		super();
	}
	
	// 表示操作成功，后面传递的参数为SUCCESS
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}

	// 表示数据获取成功，后面传递的参数为SUCCESS和数据总数
	public ResponseResult(Integer state,Integer count) {
		super();
		setState(state);
		setCount(count);
	}

	// 表示操作失败时，返回的状态码和信息
	public ResponseResult(Integer state, String message) {
		this(state);// this是调自己的构造方法，super是调父类的构造方法
		setMessage(message);
	}

	public ResponseResult(Integer state, Exception e) {
		this(state,e.getMessage());
	}
	
	public ResponseResult(Integer state, E data) {
		this(state);
		setData(data);
	}

	public ResponseResult(Integer count,Integer state, E data) {
		this(state,count);
		setData(data);
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
	public String toString() {
		return "ResponseResult{" +
				"state=" + state +
				", message='" + message + '\'' +
				", data=" + data +
				", count=" + count +
				'}';
	}
}
