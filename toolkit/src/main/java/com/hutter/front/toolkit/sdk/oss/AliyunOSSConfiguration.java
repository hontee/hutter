package com.hutter.front.toolkit.sdk.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;

/**
 * 阿里云OSS，详细的使用文档参考：https://help.aliyun.com/document_detail/32008.html?spm=5176.doc32011.3.3.sJMZAY
 */
@Configuration
public class AliyunOSSConfiguration {
	
	@Autowired
	private AliyunOSSProperties props;
	
	@Bean
	public OSSClient createOSSClient() {
		String endpoint = props.getEndpoint();
		String accessKeyId = props.getAccessKeyId();
		String accessKeySecret = props.getAccessKeySecret();
		
		Assert.notNull(endpoint);
		Assert.notNull(endpoint);
		Assert.notNull(endpoint);
		
		ClientConfiguration conf = new ClientConfiguration();
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(200);
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(10000);
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(5);
		
		return new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
	}

}
