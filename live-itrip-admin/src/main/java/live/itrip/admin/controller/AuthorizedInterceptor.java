package live.itrip.admin.controller;

import live.itrip.admin.model.AdminUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Feng on 2016/6/29.
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        Subject currentSubject = SecurityUtils.getSubject();
        if (currentSubject != null) {

            AdminUser user = (AdminUser) currentSubject.getPrincipal();
            if (user == null) {

                // 不检测登录用户的 action
                if (action.equals("/apikeys.action")
                        || action.equals("/login.action")
                        || action.equals("/user.action")) {
                    return true;
                }

//                PrintWriter out;
//                out = response.getWriter();
//                out.print("<script>window.location.href='/login.action'</script>");
//                out.flush();
//                out.close();
                response.sendRedirect("/login.action");
                return false;
            }
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
