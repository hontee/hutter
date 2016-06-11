package com.hutter.master.base.aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.hutter.master.data.domain.User;

@Configuration
@EnableJpaAuditing
public class ShiroAuditorAware {
	
	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAware<User>() {

			@Override
			public User getCurrentAuditor() {
				return new User(1L);
			}
		};
	}

}
