package org.example.zentriotesting.configuration;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("http://localhost:9000")
    private String url;

    @Value("jFUj8joRXEXd2KLLSB2t")
    private String accessKey;

    @Value("3RomSlxsdqL9jVCkwyuqYqHgpdXa1yZhnYQH6anB")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey,secretKey)
                .build();
    }
}
