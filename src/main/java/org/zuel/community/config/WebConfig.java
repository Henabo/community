package org.zuel.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zuel.community.interceptor.LoginInterceptor;

/**
 * 拦截器注册类，使得拦截器生效。
 *
 * 这里loginInterceptor，只是将session中的user设置是否有值，然后在前段对user进行判断从而达到游客和用户对展示两种情况。
 */
@Controller
/**
 * @EnableWebConfig
 * 这里得将@EnableWebConfig注释掉，因为如果不注释的话，那么WebConfig类将会全部代理WebMvcConfigurer，
 * 但是我们只是想用其中的部分内容
 */
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器，添加loginInterceptor拦截器，对那些路径下面生效，除去那些路径。
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/admin/**");
    }
}
