package com.hsh.controller.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class MySavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    private String LOCAL_SERVER_URL = "/";

//    protected final Log logger = LogFactory.getLog(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        String platform = request.getParameter("platform");
        if (org.apache.commons.lang.StringUtils.isNotBlank(platform) && platform.equals("mobile")) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            Map <String, Object> result = new HashMap <String, Object>();
            result.put("authentication", authentication);
            result.put("success", true);
            result.put("platform", platform);
            result.put("message", "登陆成功");
            out.write(JSONObject.toJSONString(result));
            out.flush();
            return;
        }

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null) {
            // 用户判断是否要使用上次通过session里缓存的回调URL地址
            int flag = 0;
            // 通过提交登录请求传递需要回调的URL callCustomRediretUrl
            if (request.getSession().getAttribute("callCustomRediretUrl") != null && !"".equals(request.getSession().getAttribute("callCustomRediretUrl"))) {
                String url = String.valueOf(request.getSession().getAttribute("callCustomRediretUrl"));
                // 若session 存在则需要使用自定义回调的URL 而不是缓存的URL
                super.setDefaultTargetUrl(url);
                super.setAlwaysUseDefaultTargetUrl(true);
                flag = 1;
                request.getSession().setAttribute("callCustomRediretUrl", "");
            }
            // 重设置默认URL为主页地址
//            if (flag == 0) {
//                super.setDefaultTargetUrl(LOCAL_SERVER_URL);
//            }
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        } else {
            // targetUrlParameter 是否存在
            String targetUrlParameter = getTargetUrlParameter();
            if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
                requestCache.removeRequest(request, response);
                super.setAlwaysUseDefaultTargetUrl(false);
//                super.setDefaultTargetUrl("/");
                super.onAuthenticationSuccess(request, response, authentication);
                return;
            }
            // 清除属性
            clearAuthenticationAttributes(request);
            // Use the DefaultSavedRequest URL
            String targetUrl = savedRequest.getRedirectUrl();
            logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
//            if (targetUrl != null && "".equals(targetUrl)) {
//                targetUrl = LOCAL_SERVER_URL;
//            }
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }

    public void setRequestCache(RequestCache requestCache) {

        this.requestCache = requestCache;
    }
}
