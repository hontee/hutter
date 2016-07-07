package com.hutter.master;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 自动化测试基类
 * @author Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class ApplicationTests {
	
	protected Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

}
