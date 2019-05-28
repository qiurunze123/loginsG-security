/**
 * 
 */
package com.loginsgeekq.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qiurunze
 *
 */
@ConfigurationProperties(prefix = "geekq")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();

	private ImageCodeProperties imageCodeProperties = new ImageCodeProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

	public ImageCodeProperties getImageCodeProperties() {
		return imageCodeProperties;
	}

	public void setImageCodeProperties(ImageCodeProperties imageCodeProperties) {
		this.imageCodeProperties = imageCodeProperties;
	}
}

