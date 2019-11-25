package com.tttiger.admin;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Test
    public void contextLoads() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4FqwZ9S2i6q9C7bmbjWz";
        String accessKeySecret = "f18GiumDWu7thlH1D6kF6PqlXoXnxx";
        String fileName = "C:/Users/Administrator/Pictures/bb955b348b9debca1b34ab43d5334ea1.png";
        String bucketName = "kaxitu";
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
        File file = new File(fileName);
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, file);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        ossClient.shutdown();
        System.out.println(endpoint+"/"+objectName);
        System.out.println(url.toString());
        System.out.println(url.toString().length());

    }

}
