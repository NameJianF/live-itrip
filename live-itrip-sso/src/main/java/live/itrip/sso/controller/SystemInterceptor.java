package live.itrip.sso.controller;

import com.alibaba.fastjson.JSON;
import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.common.Config;
import live.itrip.sso.common.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Feng on 2016/6/29.
 */
public class SystemInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 检测 Config 是否已经加载完成
        if (!Config.inited()) {
            BaseResult result = new BaseResult();
            result.setError(ErrorCode.SERVICE_INITING);
            this.writeResponse(response, result);
            return false;
        }

        String action = request.getServletPath();

//        if (action.equals("/user/login.htm")) {
//            return true;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void writeResponse(HttpServletResponse response, Object obj) {
        try {
            AtomicReference<String> json = new AtomicReference<String>(JSON.toJSONString(obj));
            response.setCharacterEncoding(Encoding.UTF8);
            PrintWriter out;
            out = response.getWriter();
            out.print(json.get());
            out.flush();
            out.close();
        } catch (IOException e) {
            Logger.error(Constants.LOG_TAG, e);
        }
    }
}
