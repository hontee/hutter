package com.hutter.front.toolkit.test.oss;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.hutter.front.toolkit.sdk.oss.AliyunOSSClient;
import com.hutter.front.toolkit.test.ToolkitApplicationTests;

public class OSSBucketTests extends ToolkitApplicationTests{

	Logger logger = LoggerFactory.getLogger(OSSBucketTests.class);
	
	@Autowired
	private OSSClient client;
	
	@Autowired
	private AliyunOSSClient aliyunClient;
	
	@Test
	public void testListBuckets() {
		logger.info("Print OSS bucket list.");
		List<Bucket> list = client.listBuckets();
		
		Assert.notNull(list);
		
		list.forEach((bucket) -> {
			logger.info(JSON.toJSONString(bucket));
		});
		
		client.shutdown();
	}
	
	@Test
	public void testOSSBuckets() {
		Object[] buckets = aliyunClient.listBuckets().toArray();
		org.junit.Assert.assertArrayEquals(buckets, new String[]{"hutter-avater", "hutter-images"});
	}
	
	@Test
	public void testPutObject() {
		File file = new File("D:/Pictures/61610.png");
		PutObjectResult putObjectResult = aliyunClient.putObject("hutter-images", "users/61610.png", file);
		System.out.println(JSON.toJSONString(putObjectResult, true));
	}
	
}
