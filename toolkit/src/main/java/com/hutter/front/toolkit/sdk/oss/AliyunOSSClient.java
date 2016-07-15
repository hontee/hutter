package com.hutter.front.toolkit.sdk.oss;

import java.util.List;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;

/**
 * 阿里云OSS，详细的使用文档参考：https://help.aliyun.com/document_detail/32008.html?spm=5176.doc32011.3.3.sJMZAY
 */
public class AliyunOSSClient {
	
	public static void main(String[] args) {
		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
		String accessKeyId = "qlGqJUXT4b8r5P3X";
		String accessKeySecret = "FaCUnpx2CIpSpNlrJ2lctTrG4gWvRl";
		
		ClientConfiguration conf = new ClientConfiguration();
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(200);
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(10000);
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(5);
		
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			System.out.println(bucket.getName());
		}
		client.shutdown();
	}

}
