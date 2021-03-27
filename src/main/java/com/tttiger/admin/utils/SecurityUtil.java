package com.tttiger.admin.utils;

import com.alibaba.fastjson.JSON;
import com.tttiger.admin.bean.sys.security.Aes;
import com.tttiger.admin.bean.sys.security.Rsa;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/12/22 16:42
 * @Description
 */
public class SecurityUtil {

    /**
     * 获取当前登录的用户
     *
     * @return 当前登录的用户
     */
    public static Authentication getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 设置服务端Rsa
     * @return 公钥
     * @throws Exception
     */
    public static String setServerRsa() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Rsa rsa = RSAUtil.generateKey();
        // 设置当前会话加密通信Rsa加密实例
        session.setAttribute("serverPublicKey",rsa.getPublicKeyString());
        session.setAttribute("serverPrivateKey",rsa.getPrivateKeyString());
        return rsa.getPublicKeyString();
    }

    /**
     *
     * @param clientRsaPublicKey 客户端公钥
     * @return 使用客户端公钥加密后的aes
     * @throws Exception
     */
    public static String setTransportAes(String clientRsaPublicKey) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        // 服务端公钥
        String serverPrivateKey = session.getAttribute("serverPrivateKey").toString();
        // 解密客户端公钥
        String clientPublicKey = RSAUtil.decryptByPriKey(clientRsaPublicKey, serverPrivateKey);
        session.setAttribute("clientPublicKey",clientPublicKey);
        Aes aes = AesUtil.generateAes();
        session.setAttribute("transportAesKey",aes.getKey());
        session.setAttribute("transportAesIv",aes.getIv());
        // 客户端公钥加密aes
        return RSAUtil.encryptByPubKey(JSON.toJSON(aes).toString(), clientPublicKey);
    }

    /**
     * 通过当前session中的aes进行加密
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String EncryptBySessionAes(String data) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String aesKey = request.getSession().getAttribute("transportAesKey").toString();
        String aesIv = request.getSession().getAttribute("transportAesIv").toString();
        return AesUtil.encrypt(data, aesKey,aesIv);
    }

    /**
     * 通过当前session中的aes进行解密
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String DecryptBySessionAes(String data) throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String aesKey = request.getSession().getAttribute("transportAesKey").toString();
        String aesIv = request.getSession().getAttribute("transportAesIv").toString();
        return AesUtil.decrypt(data, aesKey,aesIv);
    }

    /**
     * 解析当前请求ip地址
     * @param request 请求request
     * @return 地址地址
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    private SecurityUtil(){}
}
