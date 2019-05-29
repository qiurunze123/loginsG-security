package com.loginsgeekq.reflecttest;

import org.springframework.stereotype.Component;

/**
 * @author 邱润泽 bullock
 */
@Component("Test1Impl2")
public class Test1Impl2 implements testI {
    @Override
    public void create() {
        System.out.println("==== nihao");
    }
}
