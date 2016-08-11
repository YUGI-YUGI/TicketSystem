package com.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.entity.User;

public class SessionValidate implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
System.out.println(request.getServletPath() );
        // ignore login page
System.out.println(request.getServletPath());
        if (request.getServletPath() == "/home") { // BEWARE : to be adapted to your actual login page
            return true;
        }

        User user=(User) request.getSession().getAttribute("user");
        if(user == null)
        {
            System.err.println("Request Path : ");
            response.sendRedirect("/");
            return false;
        }
        else
        {
            return true;
        }
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}