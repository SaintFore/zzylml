package com.zzylml.oss;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class TencentCOSOperator {

    @Autowired
    private TencentCOSProperties cosProperties;

    public String upload(byte[] content, String originalFilename) {
        // 1. 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量 SECRET_ID 和 SECRET_KEY。
        // 强烈建议不要把访问凭证直接写在代码里。
        String secretId = System.getenv("SECRET_ID");
        String secretKey = System.getenv("SECRET_KEY");
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // ---- 添加下面这两行来进行调试 ----
        System.out.println("--- 正在尝试使用的SECRET_ID: [" + secretId + "]");
        System.out.println("--- 正在尝试使用的SECRET_KEY: [" + (secretKey != null ? "已获取" : "未获取") + "]");

        // 2. 设置 bucket 的地域（Region）
        Region region = new Region(cosProperties.getRegion());
        ClientConfig clientConfig = new ClientConfig(region);

        // 3. 生成 COS 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = cosProperties.getBucketName();

        // 4. 保证文件路径唯一性，格式为 yyyy/MM/uuid.ext
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = dir + "/" + newFileName;

        // 5. 文件上传
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(content.length);
            cosClient.putObject(bucketName, objectName, new ByteArrayInputStream(content), objectMetadata);
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }

        // 6. 拼接返回文件的URL
        // 格式为：https://<BucketName>.cos.<Region>.myqcloud.com/<ObjectName>
        return "https://" + bucketName + ".cos." + cosProperties.getRegion() + ".myqcloud.com/" + objectName;
    }
}