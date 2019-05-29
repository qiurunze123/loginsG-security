package com.loginsgeekq.reflecttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 邱润泽 bullock
 */
@Component
public class TestCollect {

    @Autowired
    private Map<String, testI> validateCodeProcessors;




    public  void  test(){
        validateCodeProcessors.get("test1");
    }
}
