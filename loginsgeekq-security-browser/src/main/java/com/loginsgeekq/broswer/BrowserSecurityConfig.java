package com.loginsgeekq.broswer;

import com.loginsgeekq.core.SecurityProperties;
import com.loginsgeekq.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 邱润泽 bullock
 * <p>
 * 接收到 html请求 --  是否需要身份认证 -- 跳转到一个自定义的controller方法
 * -- 请求是html请求引发的跳转
 * <p>
 * -- 返回登录页面  -- 返回状态401状态吗和错误信息
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;


    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(failureHandler);
        /**
         *  表单登陆  任何请求 需要认证
         */
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin().loginPage("/authentication/require").loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(failureHandler)
                .and().authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()
                ,"/code/image")
                .permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }
}
