package com.hutter.master.base.fetch;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hutter.master.base.properties.FetchProperties;

/**
 * 网页抓取工具
 * 
 * @author larry.qi
 */
@Configuration
public class FetchClient {

  private Logger logger = LoggerFactory.getLogger(FetchClient.class);
  private static final String META_KEYWORDS = "meta[name=keywords]";
  private static final String META_DESCRIPTION = "meta[name=description]";
  private static final String CONTENT = "content";

  @Autowired
  private FetchProperties props;

  /**
   * 获取页面信息
   * @param url
   * @return
   */
  public WebInfo fetch(String url) {
    long startTime = System.currentTimeMillis();
    WebInfo webInfo = new WebInfo(url);
    try {
      Document html = fetchHtml(url);
      webInfo.setTitle(html.title());
      webInfo.setSuccess(true);
  
      try {
        String keywords = html.select(META_KEYWORDS).first().attr(CONTENT);
        webInfo.setKeywords(keywords);
      } catch (Exception e) {
        logger.warn("keywords not found from url: {}", url);
      }
  
      try {
        String description = html.select(META_DESCRIPTION).first().attr(CONTENT);
        webInfo.setDescription(description);
      } catch (Exception e) {
        logger.warn("Description not found from url: {}", url);
      }
    } catch (IOException e) {
      logger.warn("Get document context error from url: {}", url);
    } finally {
      long endTime = System.currentTimeMillis();
      webInfo.setTime(endTime - startTime);
    }
  
    return webInfo;
  }

  /**
   * 获取网页，可进一步深入解析页面内容
   * @param url
   * @return
   * @throws IOException
   */
  public Document fetchHtml(String url) throws IOException {
    logger.info("Fetch url: {}", url);
    try {
      return Jsoup.connect(url).userAgent(props.getUserAgent()).timeout(props.getTimeout())
          .followRedirects(props.isFollowRedirects()).ignoreContentType(props.isIgnoreContentType())
          .ignoreHttpErrors(props.isIgnoreHttpErrors()).get();
    } catch (IOException e) {
      logger.warn("Jsoup connect error from url: {}", url);
      throw new IOException(e);
    }
  }

}
