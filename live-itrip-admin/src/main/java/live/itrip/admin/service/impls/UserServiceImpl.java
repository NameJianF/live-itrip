package live.itrip.admin.service.impls;

import com.alibaba.fastjson.JSON;
import live.itrip.admin.common.Constants;
import live.itrip.admin.dao.UserMapper;
import live.itrip.admin.model.User;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IUserService;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.common.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 */
@Service
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        LoginData reqModel = JSON.parseObject(decodeJson, LoginData.class);
        BaseResult result = new BaseResult();

        // 校验验证码

        // 校验账号密码
        User user = this.userMapper.selectByUserName(reqModel.getUserName());
        if (user == null) {
            // 用户名错误
            result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
            result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
        } else {
            // 验证密码
            String password = Md5Utils.getStringMD5(user.getSalt() + reqModel.getPwd());
            try {
                if (password.equals(user.getPassword())) {
                    // 密码正确，保存session信息
                    request.getSession().setAttribute(Constants.SESSION_USER, user);
                    // 写入 cookie

                    result.setCode(ErrorCode.SUCCESS.getCode());
                } else {
                    // 密码错误
                    result.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                    result.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
                }
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
                result.setCode(ErrorCode.UNKNOWN.getCode());
            }
        }

        this.writeResponse(response, result);
    }

    /**
     * login datas
     *
     * @author JianF
     */
    public static class LoginData {
        private String userName;
        private String pwd;
        private String captcha = "";


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCaptcha() {
            return captcha;
        }

        public void setCaptcha(String captcha) {
            this.captcha = captcha;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

    }
}
