package com.clumsycoder.odinservice.config;

import com.clumsycoder.odinservice.decoders.NucleusServiceDecoder;
import feign.Client;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class NucleusServiceFeignConfig {
    private NucleusServiceDecoder customErrorDecoder;

    @Bean
    public Client feignClient() {
        return new OkHttpClient();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return customErrorDecoder;
    }
}