/**
 * 
 */
package com.loginsgeekq.validate.code;

import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author qiurunze
 *
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = 7207451175263593487L;


	public ValidateCodeException(String msg) {
		super(msg);
	}
}
