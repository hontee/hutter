package com.hutter.front.toolkit.spider;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class SpiderInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private String url;
  
  private String host;
  
  private String schema;
  
  private String title;
  
  private String keywords;
  
  private String description;
  
  private boolean success = false;
  
  private long time;

  public SpiderInfo() {
    super();
  }

  public SpiderInfo(String url) {
    setUrl(url);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
    
    if (url != null) {
      try {
        URL context = new URL(url);
        setHost(context.getHost());
        setSchema(context.getProtocol());
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

}
