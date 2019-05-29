package com.loginsgeekq.reflecttest;

import org.springframework.stereotype.Component;

/**
 * @author 邱润泽 bullock
 */
@Component("TestImpl")
public class TestImpl implements testI {
    @Override
    public void create() {
        System.out.println("====  222222");
    }
}
