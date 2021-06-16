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
        // 生成aes加密密钥
        Aes aes = AesUtil.generateAes();
        session.setAttribute("transportAesKey",aes.getKey());
        session.setAttribute("transportAesIv",aes.getIv());
        // 客户端公钥加密aes
        return RSAUtil.encryptByPubKey(JSON.toJSON(aes).toString(), clientPublicKey);
    }

    /**
     * 获取当前与客户端加密通信的aes密钥
     *
     * @return 加密通信的aes密钥
     */
    public static Aes getTransportAes(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String aesKey = request.getSession().getAttribute("transportAesKey").toString();
        String aesIv = request.getSession().getAttribute("transportAesIv").toString();
        return new Aes(aesKey,aesIv);
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


    private SecurityUtil(){}
}
