package com.hutter.front.core.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hutter.front.core.domain.User;

/**
 * 用户数据仓库
 * @author Administrator
 * @date 2016-06-08
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
	
	User findByEmail(String email);
	
}
