package nju.wjw.interceptor;

import nju.wjw.vo.OrganizationVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Jerry Wang on 2017/3/8.
 */
public class OrganizationIntercepor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("/organization/Login")>=0||url.indexOf("/organization/organizationRegister")>=0){
            return true;
        }
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        OrganizationVO studentVO = (OrganizationVO)session.getAttribute("organizationVO");

        if(studentVO != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
        httpServletRequest.getRequestDispatcher("/pages/organizationlogin.jsp").forward(httpServletRequest, httpServletResponse);

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
