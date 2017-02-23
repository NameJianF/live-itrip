package live.itrip.admin.controller;

import live.itrip.admin.common.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import live.itrip.admin.api.sso.bean.User;


/**
 * Created by Feng on 2016/6/29.
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if (user == null) {

            // 不检测登录用户的 action
            if (action.equals("/apikeys.action")
                    || action.equals("/system/login.action")
                    || action.equals("/user.action")) {
                return true;
            }

            response.sendRedirect("/system/login.action");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
