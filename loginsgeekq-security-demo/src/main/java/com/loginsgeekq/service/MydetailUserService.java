package com.loginsgeekq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 邱润泽 bullock
 */
@Component
public class MydetailUserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("用户验证开始 ! username :{}",username);

        String password = passwordEncoder.encode("123456");
        logger.info(password);
        return new User(username,passwordEncoder.encode("123456"),true,
                true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


}
