package org.zuel.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zuel.community.model.User;
import org.zuel.community.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，拦截器起到的效果可以自己设置
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;

    /**
     * 这个loginInterceptor拦截器，只是检查是否登陆，
     * 如果没登录就会将将session中的user设为null
     * 如果登陆了，cookies中就有值，就会查询到user类并放在session的user属性中
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0){
            for(Cookie cookie : cookies){
                if("herb_userName".equals(cookie.getName())){
                    String herb_userName = cookie.getValue();
                    User user = userService.selectByUserName(herb_userName);
                    if( user == null){
                        request.getSession().setAttribute("user",null);
                    } else {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }else {
            request.getSession().setAttribute("user",null);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
