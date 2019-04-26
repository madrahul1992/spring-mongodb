package com.journaldev.bootifulmongodb.interceptor;

import com.journaldev.bootifulmongodb.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

    @Autowired
    RateLimiter rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        String ipAddress = httpServletRequest.getRemoteAddr();
        String userId = "LoggedInUser"; // We can do autentication and set user in the header and use that id

        if (rateLimiter.allow(Constants.MAX_IP_REQ, ipAddress, Constants.TIME_WINDOW_FOR_IP_ADDRESS)
                && rateLimiter.allow(Constants.MAX_URL_REQ, url, Constants.TIME_WINDOW_FOR_REQ_URL)
                && rateLimiter.allow(Constants.MAX_USER_REQ, userId, Constants.TIME_WINDOW_FOR_LOGGED_IN_USER)){
            return true;
        } else {
            httpServletResponse.getWriter().write("Request limit has reached, Please try again later");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
