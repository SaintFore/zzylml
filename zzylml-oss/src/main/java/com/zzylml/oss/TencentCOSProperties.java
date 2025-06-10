package com.zzylml.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tencent.cos")
public class TencentCOSProperties {
    /**
     * 地域, 例如 ap-guangzhou, ap-beijing
     */
    private String region;

    /**
     * 存储桶名称
     */
    private String bucketName;
}