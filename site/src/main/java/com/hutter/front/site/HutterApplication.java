package com.hutter.front.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.hutter.front.site", "com.hutter.front.core" , "com.hutter.front.toolkit.spider"})
public class HutterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HutterApplication.class, args);
	}
}
