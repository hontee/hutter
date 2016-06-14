package com.hutter.master.base.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * EncryptHelper: 系统加密与安全工具类
 * 
 * @author larry.qi
 */
public class EncryptHelper {

  /**
   * MD5加密处理<br>
   * salt必须经过ByteSource进行加密处理，配合SHIRO密码匹配规则
   * 
   * @param password 密码
   * @param salt = acccessKey
   * @return
   */
  public static String encrypt(String password, String salt) {
    return encrypt(password, ByteSource.Util.bytes(salt));
  }

  /**
   * MD5加密处理
   * 
   * @param password
   * @param byteSource
   * @return
   */
  public static String encrypt(String password, ByteSource byteSource) {
    return new Md5Hash(password, byteSource).toString().toUpperCase();
  }

}
