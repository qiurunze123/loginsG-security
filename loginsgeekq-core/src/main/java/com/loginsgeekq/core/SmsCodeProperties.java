/**
 * 
 */
package com.loginsgeekq.core;

/**
 * @author qiurunze
 *
 */
public class SmsCodeProperties {
	
	private String length = "6";
	private String expireIn = "60";
	
	private String url;

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(String expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
