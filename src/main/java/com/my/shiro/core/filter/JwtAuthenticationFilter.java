package com.my.shiro.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.my.shirospringboot.shiro.constant.ShiroConstant;
import com.my.shirospringboot.shiro.core.base.BaseResponse;
import com.my.shirospringboot.shiro.core.impl.JwtTokenManager;
import com.my.shirospringboot.utils.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author guzy
 * @version 1.0
 * @Description
 */
public class JwtAuthenticationFilter extends FormAuthenticationFilter {


    private JwtTokenManager jwtTokenManager;

    public JwtAuthenticationFilter() {

    }

    public JwtAuthenticationFilter(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    /**
     * @Description: 是否允许访问
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求头中是否包含jwtToken
        String jwtToken = WebUtils.toHttp(request).getHeader(ShiroConstant.JWT_TOKEN);
        if(StringUtils.isNotEmpty(jwtToken)){
            //存在则进行token验证 (验证token是否有效)
            boolean verifyToken = this.jwtTokenManager.isVerifyToken(jwtToken);
            if(verifyToken){
                //如果校验通过,则继续进行父层校验
                /*此处已经理解，先走jwt会话管理器(JwtSessionManager)进行token解析，
                    解析通过则将对应的request header写入request域
                    因此到此处时 其实只需要进行token的校验，实际认证信息已经在会话管理器中写入request
                */
                return super.isAccessAllowed(request, response, mappedValue);
            }else{
                return false;
            }
        }

        //不存在则进行常规登录验证
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * @Description: 是否发生拒绝请求(上面方法不允许的情况)
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String jwtToken = httpServletRequest.getHeader(ShiroConstant.JWT_TOKEN);
        if(StringUtils.isNotEmpty(jwtToken)){
            //如果存在jwtToken
            BaseResponse baseResponse = new BaseResponse(ShiroConstant.NO_AUTH_CODE,
                    ShiroConstant.NO_AUTH_MESSAGE);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(baseResponse));
        }
        //不存在jwtToken
        return super.onAccessDenied(request, response);
    }
}
