package com.hutter.front.site.aware;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.hutter.front.core.domain.User;

@Configuration
@EnableJpaAuditing
public class ShiroAuditorAware {
	
	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAware<User>() {

			@Override
			public User getCurrentAuditor() {
				User currentUser = new User(1L);
				
				if (SecurityUtils.getSubject().isAuthenticated()) {
					currentUser = (User)SecurityUtils.getSubject().getSession().getAttribute("oauth");
				}
				
				return currentUser;
			}
		};
	}

}
