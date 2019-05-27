package com.loginsgeekq.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loginsgeekq.core.LoginResponseType;
import com.loginsgeekq.core.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GeekqAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginResponseType())) {
            logger.info("登录成功!");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
