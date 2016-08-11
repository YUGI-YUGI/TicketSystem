package com.test.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
@Configuration 
@ComponentScan("com.test") 
@EnableWebMvc   
public class AppConfig extends WebMvcConfigurerAdapter  {  
	@Bean  
        public UrlBasedViewResolver setupViewResolver() {  
		System.out.println("ssss");
            UrlBasedViewResolver resolver = new UrlBasedViewResolver();  
            resolver.setPrefix("/views/");  
            resolver.setSuffix(".html");  
            resolver.setViewClass(JstlView.class);  
            return resolver;  
        }
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new SessionValidate()).addPathPatterns("*");
	}
}