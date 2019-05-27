/**
 * 
 */
package com.loginsgeekq.core;


/**
 * @author qiurunze
 *
 */
public class BrowserProperties {

	private String loginPage = "/signIn.html" ;

	private LoginResponseType loginResponseType = LoginResponseType.JSON;
	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginResponseType() {
		return loginResponseType;
	}

	public void setLoginResponseType(LoginResponseType loginResponseType) {
		this.loginResponseType = loginResponseType;
	}
}
