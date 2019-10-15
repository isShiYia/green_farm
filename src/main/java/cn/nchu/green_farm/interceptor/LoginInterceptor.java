package cn.nchu.green_farm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器 
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			response.sendRedirect("../user/login.html");
			// 		拦截
			return false;
		} /*else if (session.getAttribute("bid") == null) {
			response.sendRedirect("../business/businessLogin.html");
			// 		拦截
			return false;
		}*/ else {
			// 非null，即存在uid，即已经登录
			// 放行
			return true;
		}
	}
}
