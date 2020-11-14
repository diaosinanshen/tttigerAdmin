package com.tttiger.admin.security;

import com.tttiger.admin.utils.AesUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/13 10:16
 * @description 封装加密请求参数
 */
public class EncryptHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public EncryptHttpInputMessage(HttpInputMessage inputMessage,String aesKey) throws Exception {
        this.headers = inputMessage.getHeaders();
        String decrypt = AesUtil.decrypt(easpString(IOUtils.toString(inputMessage.getBody(), "UTF-8")), aesKey);
        this.body = IOUtils.toInputStream(decrypt, "UTF-8");
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

    /**
     * @param requestData
     * @return
     */
    public String easpString(String requestData) {
        return requestData;
    }
}
