package com.loginsgeekq.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 邱润泽 bullock
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *  表单登陆  任何请求 需要认证
         */
        http.formLogin().and().authorizeRequests().anyRequest().authenticated();
    }
}
