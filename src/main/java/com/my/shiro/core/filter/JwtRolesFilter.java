package com.my.shiro.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.my.shiro.constant.ShiroConstant;
import com.my.shiro.core.base.BaseResponse;
import com.my.shiro.utils.StringUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Gzy
 * @version 1.0
 * @Description
 */
public class JwtRolesFilter extends RolesAuthorizationFilter {


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String jwtToken = httpServletRequest.getHeader(ShiroConstant.JWT_TOKEN);
        if(StringUtils.isNotEmpty(jwtToken)){
            //如果存在jwtToken
            BaseResponse baseResponse = new BaseResponse(ShiroConstant.NO_ROLES_CODE,
                    ShiroConstant.NO_ROLES_MESSAGE);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(baseResponse));
        }
        //不存在jwtToken
        return super.onAccessDenied(request, response);
    }
}
