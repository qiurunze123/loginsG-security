/**
 * 
 */
package com.loginsgeekq.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author qiurunze
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
	
}
