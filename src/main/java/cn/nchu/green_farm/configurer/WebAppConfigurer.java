package cn.nchu.green_farm.configurer;

import java.util.ArrayList;
import java.util.List;

import cn.nchu.green_farm.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
	// WebMvcConfigurer w;
	/**
	 * 拦截器的注册
	 * 添加拦截器 addInterceptors
	 * addPathPatterns 拦截的路径
	 * excludePathPatterns 拦截器意外的情况，拦截范围之外
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 黑名单
		List<String> pathPatterns = new ArrayList<String>();
		pathPatterns.add("/user/**");
		pathPatterns.add("/web/**");
//		pathPatterns.add("/address/**");
//		pathPatterns.add("/cart/**");
//		pathPatterns.add("/order/**");
		// 白名单
		List<String> excludePathPatterns = new ArrayList<String>();
		excludePathPatterns.add("/user/reg.do");
		excludePathPatterns.add("/user/login.do");
		excludePathPatterns.add("/web/user/register.html");
		excludePathPatterns.add("/web/user/login.html");
		excludePathPatterns.add("/web/user/index.html");
		excludePathPatterns.add("/web/user/product.html");
		excludePathPatterns.add("/web/admin/adminLogin.html");
		// 注册
		registry.addInterceptor(new LoginInterceptor())
		.addPathPatterns(pathPatterns)
		.excludePathPatterns(excludePathPatterns);

	}
}
