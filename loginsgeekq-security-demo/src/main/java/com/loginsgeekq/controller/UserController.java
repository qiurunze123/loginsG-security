/**
 * 
 */
package com.loginsgeekq.controller;

import com.loginsgeekq.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author 邱润泽 bullock
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping
	@ApiOperation(value = "用户查询服务")
	public List<User> query() {
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

}
