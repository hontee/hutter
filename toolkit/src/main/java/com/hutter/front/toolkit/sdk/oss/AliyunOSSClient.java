package com.hutter.front.toolkit.sdk.oss;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.google.common.collect.Lists;

@Component
public class AliyunOSSClient {

	@Autowired
	private OSSClient client;
	
	public List<String> listBuckets() {
		List<String> list = Lists.newArrayList();
		
		try {
			List<Bucket> buckets = client.listBuckets();
			Assert.notNull(buckets, "Buckets is not null.");
			buckets.forEach((bucket) -> {
				list.add(bucket.getName());
			});
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		
		return list;
	}
	
	public boolean exists(String bucketName) {
		Assert.notNull(bucketName);
		try {
			return client.doesBucketExist(bucketName);
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		
		return false;
	}
	
	public PutObjectResult putObject(PutObjectRequest putObjectRequest) {
		return client.putObject(putObjectRequest);
	}
	
	public PutObjectResult putObject(String bucketName, String key, InputStream input) {
		return client.putObject(bucketName, key, input);
	}
	
	public PutObjectResult putObject(String bucketName, String key, File file) {
		return client.putObject(bucketName, key, file);
	}
	
}
